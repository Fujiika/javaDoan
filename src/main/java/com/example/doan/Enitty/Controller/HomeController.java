package com.example.doan.Enitty.Controller;


import com.example.doan.Enitty.Entity.SanPham;
import com.example.doan.Enitty.Entity.LoaiSP;
import com.example.doan.Enitty.Services.CartService;
import com.example.doan.Enitty.Services.LoaiSpServices;
import com.example.doan.Enitty.Services.SanPhamServices;
import com.example.doan.Enitty.daos.Item;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private SanPhamServices sanPhamServices;

    @Autowired
    private LoaiSpServices loaiSpServices;

    @Autowired
    private CartService cartService;
    @GetMapping
    public String showAllSp(Model model) {
        List<SanPham> sanphams = sanPhamServices.getAllSP();
        List<LoaiSP> loaiSps = loaiSpServices.getAllLoaiSP();
        model.addAttribute("sanphams", sanphams);
        model.addAttribute("loaiSps", loaiSps);
        return "Home/index";
    }


}
