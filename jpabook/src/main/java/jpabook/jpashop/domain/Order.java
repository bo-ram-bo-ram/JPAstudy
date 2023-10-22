package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;


@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(name ="order_id")
    private Long id;

    @Column(name ="member_id")
    private Long memberId;
    private LocalDateTime orderDate;    //스프링부트에선 order_Date로 날려줌

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Member member;

    public Member getMember() {
        return member;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
