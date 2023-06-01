package com.example.doan.Enitty.Services;

import com.example.doan.Enitty.Entity.Quyen;
import com.example.doan.Enitty.Repository.IQuyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuyenServices {
    @Autowired
    private IQuyenRepository quyenRepository;

    public List<Quyen> getAllQuyen(){
        return quyenRepository.findAll();
    }

    public Quyen getQuyenByName(String tenQuyen){
        return quyenRepository.findByTenQuyen(tenQuyen);
    }

    public Quyen getQuyenById(Long id){
        Optional<Quyen> optionalQuyen = quyenRepository.findById(id);
        if(optionalQuyen.isPresent()){
            return optionalQuyen.get();
        }else{
            throw new RuntimeException("Couldn't find");
        }
    }

    public Quyen saveQuyen(Quyen quyen){
        return quyenRepository.save(quyen);
    }

    public Quyen editQuyen(Quyen quyen){
        return  quyenRepository.save(quyen);
    }

    public void deleteQuyen(Long id){
        quyenRepository.deleteById(id);
    }
}
