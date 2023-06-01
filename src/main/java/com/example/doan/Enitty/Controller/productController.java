package com.example.doan.Enitty.Controller;


import com.example.doan.Enitty.Entity.SanPham;
import com.example.doan.Enitty.Services.SanPhamServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class productController {
    @Autowired
    private SanPhamServices sanPhamServices;

    @GetMapping
    public String showSp(Model model) {
        List<SanPham> sanphams = sanPhamServices.getAllSP();
        model.addAttribute("sanphams", sanphams);
        return "Product/index";
    }
}
