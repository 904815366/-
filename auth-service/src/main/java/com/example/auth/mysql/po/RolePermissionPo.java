package com.example.auth.mysql.po;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "role_permission", schema = "scott")
public class RolePermissionPo  implements Serializable {

    @Id
    private long id;

    @Column(name = "role_name")
    private String roleName;
    private String permission;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePermissionPo that = (RolePermissionPo) o;
        return id == that.id && Objects.equals(roleName, that.roleName) && Objects.equals(permission, that.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, permission);
    }
}
