package com.example.springgateway.filter;


import com.example.util.JwtUtils;
import com.example.util.MapUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
@Order(-1)
@Slf4j
public class JwtGlobalFilter implements GlobalFilter {
    @Resource
    private ReactiveStringRedisTemplate reactiveStringRedisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //获取jwt-token
        List<String> arrToken = request.getHeaders().get("x-jwt-token");


        //获取请求URI集合
        LinkedHashSet<URI> urlSet = exchange.getAttribute("org.springframework.cloud.gateway.support.ServerWebExchangeUtils.gatewayOriginalRequestUrl");
        URI[] uris = urlSet.toArray(new URI[0]);

        String[] urlSplit = StringUtils.delimitedListToStringArray(uris[0].getPath(), "/");

        //获取请求方式
        HttpMethod method = request.getMethod();

        //将请求中的路径替换成*
        String uri = Arrays.stream(urlSplit).map(s -> {
            try {
                Long.parseLong(s);
            } catch (NumberFormatException e) {
                return s;
            }
            return "*";
        }).collect(Collectors.joining("/"));


        String methodUri = method + ":" + uri;
        System.out.println(methodUri);

        //登录请求,无条件放行
        if (methodUri.endsWith("/login")) {
            System.out.println("当前为认证请求,放行");
            return chain.filter(exchange);
        }


        Mono<Boolean> booleanMono = reactiveStringRedisTemplate.hasKey(methodUri);

        return booleanMono.flatMap(aBoolean -> {
            //redis中没有uri这个key,说明当前请求无权限可访问, 放行
            if (!aBoolean) {
                System.out.println("当前为不需要权限,放行");
                return chain.filter(exchange);
            }

            if (ObjectUtils.isEmpty(arrToken)) {
                System.out.println("请求需要权限,但x-jwt-token为空,拦截");
                return responseErorr("401", "请先登录", response);
            }

            if (arrToken.size() == 0) {
                System.out.println("请求需要权限,但x-jwt-token为空,拦截");
                return responseErorr("401", "请先登录", response);
            }

            String jwtToken = arrToken.get(0);

            if (!JwtUtils.verify(jwtToken)) {
                System.out.println("x-jwt-token是校验失败,拦截");
                return responseErorr("401", "请重新登录", response);
            }

            //当前登录的用户名
            String usernameFromJWT = JwtUtils.getUsernameFromJWT(jwtToken);
            //当前用户的权限
            String authoritiesFromJwt = JwtUtils.getAuthoritiesFromJwt(jwtToken);
            Set<String> authoritiesFromJwtSet = StringUtils.commaDelimitedListToSet(authoritiesFromJwt);


            Mono<String> monoAuthorities = reactiveStringRedisTemplate.opsForValue().get(methodUri);

            return monoAuthorities.flatMap(requiredAuthorities -> {
                //访问请求所需要的权限
                Set<String> requiredAuthoritiesSet = StringUtils.commaDelimitedListToSet(requiredAuthorities);

                for (String myAuthorities : authoritiesFromJwtSet) {
                    if (requiredAuthoritiesSet.contains(myAuthorities)) {
                        System.out.println("有权限,放行");
                        return chain.filter(exchange);
                    }
                }
                System.out.println("迭代结束,当前无权限访问");
                return responseErorr("401", "当前无权限访问", response);

            });
        });

    }

    @SneakyThrows
    private Mono<Void> responseErorr(String code, String msg, ServerHttpResponse response) {

        Map<String, String> map = MapUtils.of(
                "code", code,
                "msg", msg);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(map);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        Mono<DataBuffer> mono = stringToDataBuffer(jsonStr, response);
        return response.writeWith(mono);
    }


    private Mono<DataBuffer> stringToDataBuffer(String input, ServerHttpResponse response) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        return Mono.just(response.bufferFactory().wrap(bytes));
    }

}
