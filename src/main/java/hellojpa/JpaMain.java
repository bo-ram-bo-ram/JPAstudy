package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //뒤에 hello는 persistence.xml에 있는것. 변수형으로 만든건 너무 길어서
        //이건 딱 한번만 만들어야하고 트랜젝션(한행동>)할때마다 하나씩
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code

        try{
            //등록
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");

            //조회
            Member findmember = em.find(Member.class, 1L);
            System.out.println("MemberId = "+findmember.getId());
            System.out.println("MemberName = "+findmember.getName());

            //전체회원 조회
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();

            //삭제
            //em.remove(findmember);

            //수정
            findmember.setName("HelloJPA");
            System.out.println("MemberName = "+findmember.getName());

            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }


        //code

        em.close();

        emf.close();
    }
}
