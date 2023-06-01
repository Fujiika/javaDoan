package com.example.doan.Enitty.Repository;


import com.example.doan.Enitty.Entity.LoaiSP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoaiSPRepository extends JpaRepository<LoaiSP, Long> {
    @Query("SELECT l FROM LoaiSP l WHERE l.tenLoai = :tenLoai")
    LoaiSP findByTenLoai(@Param("tenLoai") String tenLoai);

}
