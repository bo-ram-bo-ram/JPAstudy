package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(name ="order_id")
    private Long id;


    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();


    public void addOrderItems(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    private LocalDateTime orderDate;    //스프링부트에선 order_Date로 날려줌

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Member getMember() {return member;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMember(Member member) {this.member = member;}

    public List<OrderItem> getOrderItems() {return orderItems;}

    public void setOrderItems(List<OrderItem> orderItems) {this.orderItems = orderItems;}

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
