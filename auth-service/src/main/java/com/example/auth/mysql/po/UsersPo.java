package com.example.auth.mysql.po;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", schema = "erp")
public class UsersPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Integer tel;
    private String email;
    private Long roleId;
    private String status;


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
