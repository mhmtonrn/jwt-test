package com.softengine.jwttest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "U411515" ,name = "USER_TBL")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class User {
    @Id
    private int id;
    private String userName;
    private String password;
    private String email;


    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private List<Privilege> privileges;

    @Override
    public String toString() {
        return "User{}";
    }
}
