package com.example.doan.Enitty.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "hoaDon")
public class HoaDon {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "MaHD")
    private Long maHD;

    @Column(name = "TongTien")
    private double tongTien;

    @ManyToOne
    @JoinColumn(name = "MaUser")
    private User user;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ChiTietHoaDon> chiTietHoaDons = new HashSet<>();
}
