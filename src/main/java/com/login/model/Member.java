package com.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // 해당 클래스는 entity 임을 선언
@Table(name = "MEMBER") // name 속성으로 table 이름을 MEMBER로 지정. 테이블명은 MEMBER 이고, 데이터담을 entity는 Member 로 명시함
//@Getter -- lombok 라이브러리 추가시 에러가 발생해 직접 Setter, Getter 입력함
//@Setter
public class Member {

    @Id // 해당컬럼은 기본키임을 나타내는 어노테이션
    @Column(length = 20)
    private String id;

    @Column(length = 20)
    private String password;

    @Column(length = 20)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
