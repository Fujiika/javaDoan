package com.example.doan.Enitty.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="SanPham")
public class SanPham {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "MaSp")
    private Long maSp;

    @Column(name = "TenSP")
    private String tenSP;

    @Column(name = "DonGia")
    private double donGia;

    @Column(name = "HinhAnh")
    private String hinhAnh;

    @ManyToOne
    @JoinColumn(name = "MaLoai")
    private LoaiSP loaiSP;
}
