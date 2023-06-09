package com.example.doan.Enitty.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Quyen")
public class Quyen {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "MaQuyen")
    private Long maQuyen;

    @Column(name = "TenQuyen")
    private String tenQuyen;

    @OneToMany(mappedBy = "quyen", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

}
