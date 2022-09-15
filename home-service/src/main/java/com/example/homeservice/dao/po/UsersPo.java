package com.example.homeservice.dao.po;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users", schema = "erp")
public class UsersPo {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String tel;
    private String email;

    @OneToOne
    @JoinColumn(name = "role_id")
    private RolePo role;

    private String status;
    private Date createtime;

    private Integer version;
    private String avatar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsersPo usersPo = (UsersPo) o;
        return id != null && Objects.equals(id, usersPo.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
