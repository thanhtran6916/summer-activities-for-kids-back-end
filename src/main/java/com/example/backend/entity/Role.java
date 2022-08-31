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
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;
}
