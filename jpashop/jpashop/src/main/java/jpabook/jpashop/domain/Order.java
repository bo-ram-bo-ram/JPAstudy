package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)  //orderItem에 있는 order
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;    //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태


    //연관관계 편의 메소드
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //생성 메서드 >> order이 생성되자마자 호출되면서 값을 쭉쭉 넣어줌
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);

        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }


    //비즈니스 로직 : 주문 취소

    //주문 취소
    public void cancel(){
        if(delivery.getStatus()==DeliveryStatus.COMP){  // 배송 완료된 경우 >> 취소불가, 예외처리
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능 합니다");
        }

        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems){
            orderItem.cancel(); // 주문 여러개 했을 수도 있으니 각각 취소 날려줌 >> orderItem에 cancel 추가
        }
    }

    //조회 로직 : 전체 주문 가격 조회

    public int getTotalPrice(){

        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }

        //위에꺼 축약ver
        // int totalPrice = orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();

        return totalPrice;
    }

    //상품 주문

    //주문 내역 조회


}
    /*
    - ... = 가변인자
    - 가변인자란? 메서드의 매개변수 개수를 동적으로 지정해주는 기능
    - 형식 : 타입... 변수명
    - 원리 : 내부적으로 배열 이용
    - 주의
        1. 가변인자 외에도 매개변수가 더 있다면, 가변인자를 매개변수 중에서 제일 마지막에 선언
            매개변수 앞에 선언시 컴파일 에러 발생 >> 가변인자인지 아닌지 구분 불가
        2. 신중히 사용하자
           가변인자는 호출때마다 배열을 새로 생성하기 때문에 비효울 주의
        3. 오버로딩 시 주의하자
           https://velog.io/@minseojo/Java-%EA%B0%80%EB%B3%80%EC%9D%B8%EC%9E%90-varargs
    - 매개변수 타입을 배열로 한 것 vs 가변인자
      매개변수를 배열로하면 반드시 인자를 지정해줘야함 >> null이나 길이가 0인 배열을 인자로 지정해주는 불편함 존재
    */