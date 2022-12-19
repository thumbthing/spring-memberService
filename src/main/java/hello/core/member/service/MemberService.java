package hello.core.member.service;

import hello.core.member.domain.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


// 실제 비즈니스 로직을 구현
// 로직들을 조합해주는 서비스
//

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        // findByName에 리턴값이 있으면 ifPresent가 실행
        //              리턴값이 없으면 ifPresent 실행 안됨
        //                  m 이게 functionalInterface
        //                      기능을 람다로 추가해 줘야함

    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //Optional 관련해서 좀 봐야할듯
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
