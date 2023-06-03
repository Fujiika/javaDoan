package com.example.doan.Enitty.Controller;

import com.example.doan.Enitty.Entity.User;
import com.example.doan.Enitty.Services.UserServices;
import com.example.doan.Enitty.Utils.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices userServices;


}
