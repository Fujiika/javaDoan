package com.example.doan.Enitty.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="GioHang")
public class GioHang {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "MaGh")
    private Long maGh;

    @Column(name = "SoLuong")
    private int soLuong;

    @ManyToOne
    @JoinColumn(name = "MaSp")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "MaUser")
    private User user;
}
