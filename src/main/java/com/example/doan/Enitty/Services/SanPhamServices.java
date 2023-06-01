package com.example.doan.Enitty.Services;

import com.example.doan.Enitty.Entity.SanPham;
import com.example.doan.Enitty.Repository.ISanPhamReposioty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamServices {
    @Autowired
    private ISanPhamReposioty sanPhamReposioty;

    public List<SanPham> getAllSP() {
        return sanPhamReposioty.findAll();
    }

    public SanPham getSpById(Long id) {
        Optional<SanPham> optional = sanPhamReposioty.findById(id);
        return optional.orElse(null);
    }

    public SanPham addSp(SanPham sp) {
        sanPhamReposioty.save(sp);
        return sp;
    }
    public SanPham editSp(SanPham sp) {
        sanPhamReposioty.save(sp);
        return sp;
    }
    public void deleteSp(Long MaSp) {
        sanPhamReposioty.deleteById(MaSp);
    }
}
