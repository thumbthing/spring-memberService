package hello.core.member.service;

import hello.core.member.domain.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // dependency injection
        // 객체 지향 설계를 깔끔하게 하기 위한 기술이다 이렇게 생성하는 것이
        //
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    //
    @Test
    void join() {
        //Given

        Member member = new Member();
        member.setName("구구구");

        //When
        // 검증하고싶은게 뭐냐(회원가입정보가 잘 들어갔는지 repository)
        Long saveID = memberService.join(member);


        //Then
        // 이제 있는지 확인 해보면 된다
        //                      Optional에 있는지 알려면           겟 써줘야함
        Member findMember = memberRepository.findById(saveID).get();
        assertEquals(member.getName(), findMember.getName());


    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //When
        memberService.join(member1);
        //                          검증하는 메소드
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        // join 으로 발생한 exception이 던져지고
        // 여기 밑에 있는 메세지 검증까지는 사실 할 필요가 없다
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }


}