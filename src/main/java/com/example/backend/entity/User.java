package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "USER_MANAGER")
public class User {

    @Id
    @GeneratedValue(generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "USER_MANAGER_ID_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLES")
    private String roles;
}
