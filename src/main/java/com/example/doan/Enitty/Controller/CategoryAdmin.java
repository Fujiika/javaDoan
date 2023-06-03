package com.example.doan.Enitty.Controller;

import com.example.doan.Enitty.Entity.LoaiSP;
import com.example.doan.Enitty.Entity.SanPham;
import com.example.doan.Enitty.Services.LoaiSpServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryAdmin  {
    @Autowired
    LoaiSpServices loaiSpServices;

    @GetMapping("/loaisp")
    public String showLoaiSP(Model model) {
        List<LoaiSP> loaiSPS = loaiSpServices.getAllLoaiSP();
        model.addAttribute("loaisp", loaiSPS);
        return "CategoryAdmin/index";
    }
}
