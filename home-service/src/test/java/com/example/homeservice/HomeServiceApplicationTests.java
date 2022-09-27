package com.example.homeservice;


import com.example.homeservice.dao.es.po.LeinftyLove;
import com.example.homeservice.dao.es.LeinftyLoveRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


@SpringBootTest
@Transactional
class HomeServiceApplicationTests {
    @Resource
    private LeinftyLoveRepository leinftyLoveRepository;

    @Test
    void contextLoads() {
        Iterable<LeinftyLove> all = leinftyLoveRepository.findAll();
        for (LeinftyLove leinftyLove : all) {
            System.out.println("是我!!" + leinftyLove.getMessage());
        }
    }




//    @Test
//    void contextLoads03() {
//        Pageable pageable = PageRequest.of(0, 2);
//        List<LeinftyLove> all = leinftyLoveRepository.findList(pageable);
//        for (LeinftyLove leinftyLove : all) {
//            System.out.println(leinftyLove);
//        }
//    }


}
