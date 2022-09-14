package com.example.auth.mysql.po;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "user", schema = "scott")
public class UserPo implements Serializable {
    @Id
    private long id;
    private String username;
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ToString.Exclude
    private Set<UserRolePo> roleSet;

    public Set<String> getRoleName() {
        return roleSet.stream()
                .map(UserRolePo::getRoleName)
                .collect(Collectors.toSet());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPo userPo = (UserPo) o;
        return id == userPo.id && Objects.equals(username, userPo.username) && Objects.equals(password, userPo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
