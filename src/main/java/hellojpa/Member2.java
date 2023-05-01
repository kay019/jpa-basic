package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

//@Entity //(name="Member") //name : 동일한 이름으로 매핑이 되어 있는 경우 구분을 위한 구분자
//@Table(name = "Member2") //table 명으로 연관된 테이블로 매핑됨
public class Member2 {

    @Id //pk
    private Long id;
    @Column (name = "name", insertable = true, updatable = true, nullable = true, columnDefinition = "varchar(100) default 'EMPTY' ") //(unique = true :unique 제약 조건, 잘 안씀:이름이 랜덤하게 뜸, length = 10) insertable - 등록 할 건지, updatable - 업데이트 할건지, nullabel - null 허용 조건
    private String name;
    //@Column(precision = 20)// 소수점 자리수 같은 것들
    private int age;
   @Enumerated (EnumType.STRING) //ordinal 사용 x string을 default라 생각해야함
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP) //날짜 관련 디비 설정
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob    //varchar 큰 것
    private String description;
    @Transient //메모리에서만 사용
    private int temp;

    private LocalDate testLocalDate;
    private LocalDateTime testLocaDateTime;

    public Member2() {
    }
    public Member2(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
