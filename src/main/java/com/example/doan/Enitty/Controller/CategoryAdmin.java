package com.example.doan.Enitty.Controller;

import com.example.doan.Enitty.Entity.LoaiSP;
import com.example.doan.Enitty.Entity.SanPham;
import com.example.doan.Enitty.Services.LoaiSpServices;
import com.example.doan.Enitty.Services.SanPhamServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/adminLSP")
public class CategoryAdmin  {
    @Autowired
    LoaiSpServices loaiSpServices;

    @Autowired
    SanPhamServices sanPhamServices;

    @GetMapping("/loaisp")
    public String showLoaiSP(Model model) {
        List<LoaiSP> loaiSPS = loaiSpServices.getAllLoaiSP();
        model.addAttribute("loaisp", loaiSPS);
        return "CategoryAdmin/index";
    }

    @GetMapping("/add")
    public String addLoaiSP(Model model) {
        model.addAttribute("loaiSP", new LoaiSP());
        return "CategoryAdmin/add";
    }

    @PostMapping("/add")
    public String addLoaiSPForm(@Valid LoaiSP loaiSP, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "CategoryAdmin/add";
        } else {
            loaiSpServices.saveCategory(loaiSP);
            return "redirect:/adminLSP/loaisp";
        }
    }

    @GetMapping("/edit/{id}")
    public String editLoaiSP(@PathVariable("id") Long id, Model model) {
        LoaiSP sp = loaiSpServices.getCategoryById(id);
        if (sp != null) {
            model.addAttribute("sp", sp);
            model.addAttribute("loaisp", loaiSpServices.getAllLoaiSP());
            return "CategoryAdmin/edit";
        } else {
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String editLSP(@ModelAttribute("book") LoaiSP sp) {
        loaiSpServices.editCategory(sp);
        return "redirect:/admin/index";
    }

    @GetMapping("/delete/{id}")
    public String delelteSP(@PathVariable("id") Long id) {
        sanPhamServices.deleteSp(id);
        return "redirect:/admin/index";
    }
}
