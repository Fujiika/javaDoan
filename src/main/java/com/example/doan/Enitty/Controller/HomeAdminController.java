package com.example.doan.Enitty.Controller;

import com.example.doan.Enitty.Entity.SanPham;
import com.example.doan.Enitty.Repository.ISanPhamReposioty;
import com.example.doan.Enitty.Services.LoaiSpServices;
import com.example.doan.Enitty.Services.SanPhamServices;
import com.example.doan.Enitty.Ultil.FileUploadUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class HomeAdminController {
    @Autowired
    SanPhamServices sanPhamServices;

    @Autowired
    LoaiSpServices loaiSpServices;

    @Autowired
    private ISanPhamReposioty sanPhamReposioty;


    @GetMapping("/index")
    public String showSp(Model model) {
        List<SanPham> sanphams = sanPhamServices.getAllSP();
        model.addAttribute("sanphams", sanphams);
        return "HomeAdmin/index";
    }

    @GetMapping("/add")
    public String addSP(Model model) {
        SanPham sp = new SanPham();
        model.addAttribute("sp", sp);
        return "HomeAdmin/add";
    }
    @PostMapping("/add")
    public String addSP(SanPham sanPham, @RequestParam("image")MultipartFile multipartFile) throws IOException {
        if(!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            sanPham.setHinhAnh(fileName);
            SanPham saveSp = sanPhamServices.addSp(sanPham);
            String upload = "img/"+sanPham.getMaSp();

            FileUploadUtil.saveFile(upload, fileName, multipartFile);
        } else {
            if(sanPham.getHinhAnh().isEmpty()) {
                sanPham.setHinhAnh(null);
                sanPhamServices.addSp(sanPham);
            }
        }
        sanPhamServices.addSp(sanPham);
        return "redirect:/admin/index";
    }

    @GetMapping("/edit/{id}")
    public String editSanpham(@PathVariable("id") Long id, Model model) {
        SanPham sanpham = sanPhamServices.getSpById(id);
        if (sanpham != null) {
            model.addAttribute("sanpham", sanpham);
            model.addAttribute("currentCategory", sanpham.getLoaiSP().getMaLoai());
            model.addAttribute("loaisp", loaiSpServices.getAllLoaiSP());
            return "HomeAdmin/edit";
        } else {
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String editSP(@ModelAttribute("sanpham") SanPham sanPham) {
        sanPhamServices.editSp(sanPham);
        return "redirect:/admin/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteSp(@PathVariable("id") Long id) {
        sanPhamServices.deleteSp(id);
        return "redirect:/admin/index";
    }
}
