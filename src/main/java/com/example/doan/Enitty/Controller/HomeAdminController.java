package com.example.doan.Enitty.Controller;

import com.example.doan.Enitty.Entity.SanPham;
import com.example.doan.Enitty.Repository.ISanPhamReposioty;
import com.example.doan.Enitty.Services.LoaiSpServices;
import com.example.doan.Enitty.Services.SanPhamServices;
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

//    @GetMapping("/add")
//    public String addBook( SanPham sp, Model model) {
//        model.addAttribute("sp", new SanPham());
//        model.addAttribute("loaisp", loaiSpServices.getAllLoaiSP());
//        return "HomeAdmin/add";
//    }
//    @PostMapping("/add")
//    public String addSp(@Valid SanPham sp,
//                        Errors error,
//                        @RequestParam("hinhAnh") MultipartFile hinhAnh,
//                        Model model) {
//        if (error.hasErrors()) {
//            model.addAttribute("loaisp", loaiSpServices.getAllLoaiSP());
//            return "HomeAdmin/add";
//        } else {
//            try {
//                // Đọc dữ liệu của tệp hình ảnh vào một mảng byte
//                byte[] imageData = hinhAnh.getBytes();
//
//                // Lưu mảng byte vào cột 'hinh_anh' kiểu dữ liệu 'BLOB' trong bảng 'san_pham'
//                sp.setHinhAnh(imageData);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            sanPhamServices.addSp(sp);
//            return "redirect:/admin/index";
//        }
//    }
//    @PostMapping("/add")
//    public String addSp(@Valid SanPham sp, Errors error, Model model) {
//        if (null != error && error.getErrorCount() > 0)  {
//            model.addAttribute("loaisp", loaiSpServices.getAllLoaiSP());
//            return "HomeAdmin/add";
//        } else {
//            sanPhamServices.addSp(sp);
//            return "redirect:/admin/index";
//        }
//    }

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
