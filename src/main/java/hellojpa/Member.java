package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@SequenceGenerator( name = "MEMBER_SEQ_GENERATOR", sequenceName = "MEMBER_SEQ", //sequenceName : 매핑할 데이터베이스 시퀀스 이름
                    initialValue = 1, allocationSize = 50)//50개 db에 미리 올려놓고 하나씩
//@TableGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        table = "MY_SEQUENCES",
//        pkColumnValue = "MEMBER_SEQ")//50개 db에 미리 올려놓고 하나씩
        public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR")
    private Long id;
    @Column(name = "name",nullable = false)
    private String username;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Member() {
    }
}
