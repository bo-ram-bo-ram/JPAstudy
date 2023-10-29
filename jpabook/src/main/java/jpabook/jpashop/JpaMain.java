package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

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

//            //양방향
//            Order order = new Order();
//            order.addOrderItems(new OrderItem());

            //단방향으로해도 문제는 없음
            Order order = new Order();
            em.persist(order);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);

            em.persist(orderItem);

            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }


        //code

        em.close();

        emf.close();
    }
}