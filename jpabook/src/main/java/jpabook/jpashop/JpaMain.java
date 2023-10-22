package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code

        try{
            //객체지향적이지 못해보임
            Order order = em.find(Order.class,1l);
            long memberId = order.getMemberId();
            Member member =em.find(Member.class, memberId);

            //좋은방법
            Member member1 = order.getMember();

            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }


        //code

        em.close();

        emf.close();
    }
}