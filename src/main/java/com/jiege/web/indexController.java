package com.jiege.web;


import com.jiege.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class indexController {
    @GetMapping("/")
    public String index(){
       //int i=0/0;
        //String blog=null;
       // if (blog==null){
       //     throw new NotFoundException("博客不存在");
       // }
        //System.out.println("---------index----------");
        return "index";
    }
}
