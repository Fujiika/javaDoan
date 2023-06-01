package com.example.doan.Enitty.Controller;

import com.example.doan.Enitty.Entity.SanPham;
import com.example.doan.Enitty.Services.LoaiSpServices;
import com.example.doan.Enitty.Services.SanPhamServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class HomeAdminController {
    @Autowired
    SanPhamServices sanPhamServices;

    @Autowired
    LoaiSpServices loaiSpServices;

    @GetMapping("/index")
    public String AdminHome() {
        return "HomeAdmin/index";
    }
    @GetMapping
    public String showSp(Model model) {
        List<SanPham> sanphams = sanPhamServices.getAllSP();
        model.addAttribute("sanphams", sanphams);
        return "Product/index";
    }

    @PostMapping("/add")
    public String addSp(@Valid SanPham sp, Errors error, Model model) {
        if (null != error && error.getErrorCount() > 0)  {
            model.addAttribute("loaisp", loaiSpServices.getAllLoaiSP());
            return "Product/add";
        } else {
            sanPhamServices.addSp(sp);
            return "redirect:/admin";
        }
    }
    @GetMapping("/add")
    public String addBook( SanPham sp, Model model) {
        model.addAttribute("sp", new SanPham());
        model.addAttribute("loaisp", loaiSpServices.getAllLoaiSP());
        return "Product/add";
    }
}
