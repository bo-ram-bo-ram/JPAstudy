package jpabook.jpashop.domain.Item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.execption.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("dtype")
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQantity;

    @ManyToMany
    @JoinTable(name = "category_item")
    private List<Category> catagories = new ArrayList<>();


    //비즈니스 로직 : 재고 늘고 줄고
    //stock증가
    public void addStock(int quantity){
        this.stockQantity += quantity;
    }
    //stock 감소
    public void removeStock(int quantity){
        int restStock = this.stockQantity - quantity;
        if(restStock<0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQantity=restStock;
    }

    //상품 등록

    //상품 목록 조회

    //상품 수정


}
