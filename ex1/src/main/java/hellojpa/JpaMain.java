package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code

        try{

            Member member1 = new Member();
            member1.setUsername("A");


            Member member2 = new Member();
            member2.setUsername("B");


            Member member3 = new Member();
            member3.setUsername("C");

            System.out.println("===========");

            em.persist(member1);    //1, 51
            em.persist(member2);    //mem
            em.persist(member3);    //mem

            System.out.println("member1 = "+member1.getId());
            System.out.println("member2 = "+member2.getId());
            System.out.println("member3 = "+member3.getId());
            System.out.println("===========");


            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }


        //code

        em.close();

        emf.close();
    }
}
