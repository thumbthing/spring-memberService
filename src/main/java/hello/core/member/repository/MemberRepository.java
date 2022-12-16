package hello.core.member.repository;

import hello.core.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    //Optional을 쓰면 값이 없어도 null이 반환 되지 않아서 exception 발생하지 않는다
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
