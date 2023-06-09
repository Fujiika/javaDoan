package com.example.doan.Enitty.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@Table(name ="LoaiSP")
public class LoaiSP {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "MaLoai")
    private Long maLoai;

    @Column(name = "TenLoai")
    private String tenLoai;

    @OneToMany(mappedBy = "loaiSP", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SanPham> sanPhams = new HashSet<>();
}
