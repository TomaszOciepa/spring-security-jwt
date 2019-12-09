package pl.tom.springsecurityjwt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {

    @GetMapping("/hello1")
    public String hello1(){
        return "hello1";
    }

    @GetMapping("/hello2")
    public String hello2(){
        return "hello2";
    }

    @GetMapping("/hello3")
    public String hello3(){
        return "hello3";
    }
}
