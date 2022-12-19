package hello.core.member.controller;

import hello.core.member.domain.Member;
import hello.core.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;


    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    // 생성자에서 초기화 안해주거나 final 선언했을때 초기화 안해주고 코어 돌리면 에러 뜸
    // 생성자에서 memberService를 초기화하면 위에서 에러가 안뜬다
    //Action:
    //
    //Consider defining a bean of type 'hello.core.member.service.MemberService' in your configuration.
    //autowired를 쓰면 스프링에서 알아서 찾아준다
    // 이걸 DI dependency injection


    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // post요청의 메소드를 만들어줘야함
    @PostMapping("/members/new")
    //                      client로부터 객체를 받았다
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName((form.getName()));

        memberService.join(member);
        // member Service에 구현한 걸 생성
        return "redirect:/";
        //              : 여기 뒤에 적힌 경로로 돌아감
        // 보낸 폼대로 다시 반환 해주는것
        //  redirect를 지정한 곳으로 다시 돌아감
    }

    @GetMapping("/members")
   // @ResponseBody
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        // template 엔진 방식
        // model 데이터를 담는 박스 같은거
        model.addAttribute("members", members);
        return "members/memberList";
        // view 호출
    }
    // 회원 정보 수

//    @GetMapping("/members/new")
//    public String updateForm() {
//        return "members/updateMemberForm";
//    }
//
//    @GetMapping("/members/update")
//    public String update(Model model) {
//
//        List<Member> members = memberService.findOne();
//
//
//    };
}
