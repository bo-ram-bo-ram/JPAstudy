package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcodes;

    protected Address(){}   //jpa를 쓰기위한 생성자 , 이후론 생성하지말자!

    public Address(String city, String street, String zipcodes) {
        this.city = city;
        this.street = street;
        this.zipcodes = zipcodes;
    }
}
