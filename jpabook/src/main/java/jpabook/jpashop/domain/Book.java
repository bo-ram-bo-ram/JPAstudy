package jpabook.jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Book extends Item {
    private String author;
    private String etc;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }
}
