package com.woniu.filter;
import com.woniu.anon.IdempotentToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 功能:不可重复验证
 * 在方法上@IdempotentToken
 */
@Component
public class IdempotentTokenInterceptor  implements HandlerInterceptor {


    @Autowired
    private StringRedisTemplate template;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

        System.out.println("进入IdempotentTokenInterceptor");

        // 当前请求要访问的不是 Controller 中的 "方法"，放行。
        if (!(object instanceof HandlerMethod))
            return true;

        // 1. 判断当前请求所要访问的那个方法的脑袋上面有没有 @IdempotentToken 注解
        //    如果没有，放行
        HandlerMethod handle = (HandlerMethod) object;
        Method method = handle.getMethod();

        IdempotentToken annotation = method.getAnnotation(IdempotentToken.class);
        if (annotation == null)
            return true;

        System.out.println(method.getName());
        // 2. 如果有。从请求头中获取幂等token
        //     如果请求头中没有，拒绝访问。
//        request.getParameter()
        String idempotentToken = request.getHeader("x-idempotent-token");
        System.out.println(idempotentToken);
        if (StringUtils.isEmpty(idempotentToken)){
//            throw new NullPointerException("请求头中没有idempotentToken,不可重复执行");
            return false;
        }

        // 3. 如果有幂等token，去 redis 中删
        Boolean deleteResult = template.delete(idempotentToken);
        //     如果删除返回结果是 0/false，决绝访问
        if (!deleteResult){
//            throw new NullPointerException("请求头中没有idempotentToken,不可重复执行");
            return false;
        }
        // 4. 又有又能删，放行。
        return true;
    }

}