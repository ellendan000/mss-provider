package cn.shadow.mss.provider.interfaces.api.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello/say")
    public String sayHello() {
        return "hello i am robot";
    }
}
