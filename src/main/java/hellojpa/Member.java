package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Table(name="MBR")//매핑할때 이름 바꾸고싶으면
public class Member {
    @Id
    private Long id;
    @Column(name = "name",nullable = false)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    //ordinal하면 enum이 수정됐을때 문제발생함 무조건 string쓰기!

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private int temp;   //db저장x, 메모리에서만 쓰겟다
    //Getter, Setter…
    public Member() {
    }
}
