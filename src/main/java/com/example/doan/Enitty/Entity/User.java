package com.example.doan.Enitty.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "MaUser")
    private Long maUser;

    @Column(name = "TenUser")
    private String tenUser;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "MatKhau")
    private String matKhau;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "Email")
    private String email;

    @ManyToOne
    @JoinColumn(name="maQuyen")
    private Quyen quyen;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<GioHang> gioHangs = new HashSet<>();
}
