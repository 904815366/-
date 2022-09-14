package com.example.auth.mysql.po;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "user_role", schema = "scott")
public class UserRolePo implements Serializable {

    @Id
    private long id;
    private String username;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_name",referencedColumnName = "role_name")
    @ToString.Exclude
    private Set<RolePermissionPo> PermissionSet;


    public Set<String> getRolePermissionName() {
        return PermissionSet.stream()
                .map(RolePermissionPo::getPermission)
                .collect(Collectors.toSet());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRolePo that = (UserRolePo) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, roleName);
    }
}
