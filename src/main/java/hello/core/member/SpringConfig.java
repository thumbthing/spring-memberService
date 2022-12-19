package hello.core.member;

import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;
import hello.core.member.repository.jpaMemberRepository;
import hello.core.member.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

// 수동으로 빈을 등록하는것
@Configuration
public class SpringConfig {

    // jpaMemberRepository 를 이니셜 라이즈 여기서 해줘야한다.

//    private final DataSource dataSource;
//    private final EntityManager em;
//
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }






//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository() );
//    }


    // 밑에가 먼저 생성되서 위에 있는걸 만들수 있다 이렇게 의존 관계를 형성할 수 있다
//    @Bean
//    public MemberRepository memberRepository() {
//        return new jpaMemberRepository(em);
//    }

    // 이렇게 수동으로 주면 관계에 대한 직관성이 좀더 좋고
    // 처음에 한거는 관계에 대한 정확한 이해가 되어있다고 한다면 처음것이 더 좋긴하다
    // 수정도 교체가 빈번할 경우 후자가 좀더 편하긴 하다



    // 스프링 데이터 JPA 리포지토리
    // 이거 하면 사실 CRUD 그냥 구현

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
}
