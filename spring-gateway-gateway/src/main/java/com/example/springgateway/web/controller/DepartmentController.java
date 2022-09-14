//package com.example.springgateway.web.controller;
//
//import com.example.springgateway.entity.DepartmentPo;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.Resource;
//import java.util.function.Function;
//
//@RestController
//public class DepartmentController {
//
//    @Resource
//    private ReactiveStringRedisTemplate reactiveStringRedisTemplate;
//
//    @GetMapping("/department/{id}")
//    public Mono<DepartmentPo> getDepartment(@PathVariable("id") Long id) {
////        DepartmentPo departmentPo = new DepartmentPo(id, "你好", "xxx");
////        return Flux.just(departmentPo);
//        Mono<String> stringMono = reactiveStringRedisTemplate.opsForValue().get("department:" + id);
//        return stringMono.map(jsonStr -> {
//            try {
//                DepartmentPo departmentPo = new ObjectMapper().readValue(jsonStr, DepartmentPo.class);
//                System.out.println(departmentPo);
//                return departmentPo;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        });
//    }
//
//
//    @GetMapping("/aaa/{id}")
//    public void aaa(@PathVariable("id") Long id)  {
//        DepartmentPo departmentPo = new DepartmentPo(id, "哈哈", "xxx");
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            String jsonStr = objectMapper.writeValueAsString(departmentPo);
//            reactiveStringRedisTemplate.opsForValue().set("department:"+id,jsonStr).block();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//}
