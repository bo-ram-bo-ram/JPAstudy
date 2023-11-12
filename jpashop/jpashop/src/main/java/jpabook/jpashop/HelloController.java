package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){   //model : 데이터 실어서 뷰로 넘길 수 있음
    model.addAttribute("data", "hello!!");  //이름이 data라는 키의 값을 hello라는 걸 넘김
    return "hello"; // 화면 이름, hello.html부름
    }
}
