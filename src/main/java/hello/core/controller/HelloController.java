package hello.core.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import hello.core.Product;
import org.apache.tomcat.util.json.JSONFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class HelloController {

    @GetMapping("hello")
    // 유저가 보내준 url 정보를 읽어서 그걸 받아준다
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        // model은 html에게 전달 해주는 것(아직 정확하게는 모르겠다)
        return "hello";
        // return 은 templates에 있는 hello를 리턴한다
        //templates/hello
    }

    //localhost:8080/hello-string?name="이민구"
    //api 보내기
    @GetMapping("hello-string")
    @ResponseBody
    // http body에 문자 내용을 (HTML 이 아니다 이거는 좀 확실히 알아두자
                                // 클라이언트가 보낸 request
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }
    //이 url을 치면
    //RequestParam 에

    //localhost:8080/hello-api?name=스파클&price=2000&stock=1
    @GetMapping("hello-api")
    @ResponseBody
    public Product helloApi(@RequestParam("name") String name,
                           @RequestParam("price") int price,
                           @RequestParam("stock") int stock) {
        Product water = new Product();
        water.setName(name);
        water.setPrice(price);
        water.setStock(stock);

        return water;
    }
}
