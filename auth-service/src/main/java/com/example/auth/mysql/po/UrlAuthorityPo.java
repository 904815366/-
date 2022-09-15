package com.example.auth.mysql.po;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "url_authority", schema = "erp")
public class UrlAuthorityPo {
    @Id
    private Long id;
    private String authority;
    private String comment;
    private String method;
    private String uri;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlAuthorityPo that = (UrlAuthorityPo) o;
        return id == that.id && Objects.equals(authority, that.authority) && Objects.equals(comment, that.comment) && Objects.equals(method, that.method) && Objects.equals(uri, that.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority, comment, method, uri);
    }
}
