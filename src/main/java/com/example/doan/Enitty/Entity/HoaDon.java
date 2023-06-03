package com.example.doan.Enitty.Entity;


import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hoaDon")
public class HoaDon {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "MaHD")
    private Long maHD;

    @Column(name = "TongTien")
    private double tongTien;

    @ManyToOne
    @JsonValue
    @JoinColumn(name = "MaUser")
    private User user;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL)
    private Set<ChiTietHoaDon> chiTietHoaDons = new HashSet<>();
}
