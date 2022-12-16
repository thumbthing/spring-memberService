package hello.core.member.repository;

import hello.core.member.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트 돌리고 나서 repository를 비워줘야함
    // 그렇기 때문에 @AfterEach를 이어줘야함
    // 테스트 하나가 끝날때 마다 이걸 실행 해줌
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    // test 는 메소드 단위로 만들어줘야한다.

    @Test
    public void save() {
        //given
        // 테스트 할 맴버가 먼저 필요할꺼다
        Member member = new Member();
        member.setName("spring");
        //when
        repository.save(member);
        //then
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
        // 아닐때의 값을 원한다면 isNotEqual
    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        Member result = repository.findByName("spring1").get();
        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }

}