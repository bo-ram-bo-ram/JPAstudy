package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 조회에 더 최적화된 트랜젝션 , 읽기용 모드로 적당히st
// 데이터 변경 안됨
// jpa의 모든 변화는 트랜젝션 안에서 일어나야함
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /*
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    장점 : 테스트코드 작성시 직접 주입 가능
    단점 : 런타임동작때 변경될 수 있음

    @Autowired
    private MemberRepository memberRepository; 와 동일
    >>단점 : MemberRepository를못바꿈
     */

    //회원가입
    @Transactional  //얜 회원가입이라 변경이 일어나야하니까 어노테이션 따로 붙여줌
    public Long join(Member member){

        validateDuplicateMember(member);//회원중복확인 로직추가
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //예외 발생
        List<Member> findMembers = memberRepository.findByName(member.getName());
        // 동시가입시 문제될 수 있어서 이름을 유니크 제약조건으로 잡아주는게 좋음

        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 하나 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }



}
