package com.example.doan.Enitty.Repository;

import com.example.doan.Enitty.Entity.LoaiSP;
import com.example.doan.Enitty.Entity.Quyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuyenRepository extends JpaRepository<Quyen, Long> {
    @Query("SELECT l FROM Quyen l WHERE l.tenQuyen = :tenQuyen")
    Quyen findByTenQuyen(@Param("tenQuyen") String tenQuyen);
}
