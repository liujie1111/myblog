package com.jiege.myblog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootTest
class MyblogApplicationTests {

    @Test
    void contextLoads() {
    }





        @Test
        @GetMapping("/index")
         String index(){
             int i=0/0;
            return "text";
        }



}
