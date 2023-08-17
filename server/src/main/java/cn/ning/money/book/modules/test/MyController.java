package cn.ning.money.book.modules.test;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class MyController {

    @GetMapping("/index")
    public String hello(Model model) {
        model.addAttribute("message", "Hello, Spring Boot with Thymeleaf!");
        model.addAttribute("data", ModelObject.builder().name("ning").sex("man").build());
        return "index";
    }

}


@Getter
@Setter
@Builder
class ModelObject {
    String message;
    String name;
    String sex;

}
