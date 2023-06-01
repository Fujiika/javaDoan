package com.example.doan.Enitty.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name ="LoaiSP")
public class LoaiSP {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "MaLoai")
    private Long maLoai;

    @Column(name = "TenLoai")
    private String tenLoai;

    @OneToMany(mappedBy = "loaiSP", cascade = CascadeType.ALL)
    private Set<SanPham> sanPhams = new HashSet<>();
}
