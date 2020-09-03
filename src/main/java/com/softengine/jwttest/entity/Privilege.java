package com.softengine.jwttest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "U411515" ,name = "PRV_TBL")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    private int roleId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id",insertable = false,updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "roleId",referencedColumnName = "id",insertable = false,updatable = false)
    private Role role;

    @Override
    public String toString() {
        return "Privilege{}";
    }
}
