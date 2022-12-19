package hello.core.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
 //   @ResponseBody
    // 이거는 바디에 넣어서 보내주는 어노테이션
        public String home() {
            return "home";
            // 이렇게만 할 경우 서버 잘못 error 500
            // view를 가르킨다
            // resource의 템플릿을 가르키고 있는데 현재는 없기 때문에

        }

}
