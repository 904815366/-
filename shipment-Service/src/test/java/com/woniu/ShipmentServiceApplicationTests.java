package com.woniu;
 
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class ShipmentServiceApplicationTests {

    @Test
    void contextLoads() {
        String jsonStr="{\"sid\":\""+10+"\",\"time\":\"123456789\",\"goods\":["+3+"-"+4+"]}";
        System.out.println(jsonStr);
    }

    @Test
    void dom2() {
    }
    

}
