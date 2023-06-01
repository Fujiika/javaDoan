package com.example.doan.Enitty.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ChiTietHoaDon")
public class ChiTietHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaCTHD")
    private Long maCTHD;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaSp")
    private SanPham sanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaHD")
    private HoaDon hoaDon;

    @Column(name = "SoLuong")
    private int soLuong;
}
