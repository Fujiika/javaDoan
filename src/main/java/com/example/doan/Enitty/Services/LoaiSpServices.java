package com.example.doan.Enitty.Services;

import com.example.doan.Enitty.Entity.LoaiSP;
import com.example.doan.Enitty.Repository.ILoaiSPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiSpServices {
    @Autowired
    private ILoaiSPRepository loaiSPRepository;

    public List<LoaiSP> getAllLoaiSP() {
        return loaiSPRepository.findAll();
    }

    public LoaiSP getLoaiSPByName(String tenLoai) {
        return loaiSPRepository.findByTenLoai(tenLoai);
    }
    public LoaiSP getCategoryById(Long id) {
        Optional<LoaiSP> optionalCategory = loaiSPRepository.findById(id);
        if(optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            throw new RuntimeException("category not found");
        }
    }

    public LoaiSP saveCategory(LoaiSP category) { return loaiSPRepository.save(category); }

    public LoaiSP editCategory(LoaiSP category) {
        return loaiSPRepository.save(category); }

    public void deleteCategory(Long id) {
        loaiSPRepository.deleteById(id); }
}
