package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Item.Item;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private MemberRepository memberRepository;
    private ItemRepository itemRepository;

    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        //엔티티조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(),count);
        /*
        order에서 @NoArgsConstructor(access = AccessLevel.PROTECTED) 해줘서
        생성메소드 벗어나는거 방지( new Order(); )
         */

        //주문 생성
        Order order = Order.createOrder(member,delivery,orderItem);

        //주문 저장
        orderRepository.save(order);
        /*
        이거 하나로 끝나는 이유!!(orderItemRepository + deliveryRepository 어쩌구 안하는이유)
        order-orderItems가 cascade해줘서(+delivery)
        (order persist될때 orderItems랑 delivery 강제로 persist됨
         */

        return order.getId();
    }

    //주문 취소
    @Transactional
    public void cancelOrder(Long orderId){
        //엔티티 조회
        Order order = orderRepository.findOne(orderId);

        //주문 취소
        order.cancel();
        /*
        앞에 비즈니스 로직 짜놔서 이렇게 편함
        jpa라서 상태변경 후 sql 안날려줘도 됨 (jpa가 변경점 감지-더티 체킹)
         */
    }

    //검색
/*    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch);
    }*/
}
//도메인 모델 패턴
