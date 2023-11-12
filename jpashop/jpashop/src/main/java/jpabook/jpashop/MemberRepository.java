package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();  //멤버반환x, 커맨드랑 쿼리를 분리하라
        // 리턴값을 거의 안만들지만 아이디 있으면 나중에  다시 조회할 수 있으니까
    }
    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
