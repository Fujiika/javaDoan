package com.example.doan.Enitty.Repository;

import com.example.doan.Enitty.Entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISanPhamReposioty extends JpaRepository<SanPham, Long> {
}