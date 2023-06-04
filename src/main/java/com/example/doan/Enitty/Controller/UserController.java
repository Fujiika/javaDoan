package com.example.doan.Enitty.Controller;


import com.example.doan.Enitty.Entity.Quyen;
import com.example.doan.Enitty.Entity.User;
import com.example.doan.Enitty.Services.QuyenServices;
import com.example.doan.Enitty.Services.UserServices;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private QuyenServices quyenServices;

    @Autowired
    private HttpSession session;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("quyens", quyenServices.getAllQuyen());
        return "Product/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Product/register";
        }


        // Đăng kí người dùng mới
        User registeredUser = userServices.registerUser(user);
        Quyen quyen = quyenServices.getQuyenByName(user.getQuyen().getTenQuyen());
        user.setQuyen(quyen);
        if (registeredUser == null) {
            model.addAttribute("errorMessage", "Tên đăng nhập đã tồn tại");
            return "Product/register";
        }

        // Hiển thị thông báo đăng kí thành công
        model.addAttribute("successMessage", "Đăng kí thành công");
        return "Product/register";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

}
