package com.example.doan.Enitty.Controller.API;

import com.example.doan.Enitty.Dto.SanPhamDto;
import com.example.doan.Enitty.Dto.UserDto;
import com.example.doan.Enitty.Entity.SanPham;
import com.example.doan.Enitty.Entity.User;
import com.example.doan.Enitty.Services.QuyenServices;
import com.example.doan.Enitty.Services.UserServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v2/users")
public class UserAPI {
    @Autowired
    private UserServices userServices;

    @Autowired
    private QuyenServices quyenServices;

    private UserDto convertToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setMaUser(user.getMaUser());
        userDto.setTenUser(user.getTenUser());
        userDto.setSdt(user.getSdt());
        userDto.setDiaChi(user.getDiaChi());
        userDto.setEmail(user.getEmail());
        userDto.setTenQuyen(quyenServices.getQuyenById(user.getQuyen().getMaQuyen()).getTenQuyen());
        return userDto;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserDto getUserById(@PathVariable Long id){
        User user = userServices.getUserById(id);
        return  convertToUserDto(user);
    }

    @GetMapping
    @ResponseBody
    public List<UserDto> getAllUser(){
        List<User> users = userServices.getAllUsers();
        List<UserDto> userDtos = new ArrayList<>();
        for(User user : users){
           userDtos.add(convertToUserDto(user));
        }
        return  userDtos;
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<UserDto> addUserApi(@RequestBody UserDto userDto) {
        User user = new User();
        user.setMaUser(userDto.getMaUser());
        user.setTenUser(userDto.getTenUser());
        user.setSdt(userDto.getSdt());
        user.setDiaChi(userDto.getDiaChi());
        user.setEmail(userDto.getEmail());
        user.setQuyen(quyenServices.getQuyenByName(userDto.getTenQuyen()));
        User savedUser = userServices.addUser(user);
        UserDto savedUserDto = convertToUserDto(savedUser);
        return ResponseEntity.ok(savedUserDto);
    }

    @PutMapping("/edit")
    @ResponseBody
    public ResponseEntity<UserDto> editUserApi(@RequestBody UserDto userDto) {
        User user = userServices.getUserById(userDto.getMaUser());
        //user.setMaUser(userDto.getMaUser());
        user.setTenUser(userDto.getTenUser());
        user.setSdt(userDto.getSdt());
        user.setDiaChi(userDto.getDiaChi());
        user.setEmail(userDto.getEmail());
        user.setQuyen(quyenServices.getQuyenByName(userDto.getTenQuyen()));
        User updatedUser = userServices.editUser(user);
        UserDto updatedUserDto = convertToUserDto(updatedUser);
        return ResponseEntity.ok(updatedUserDto);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public void  deleteUserById(@PathVariable Long id) {
        if (userServices.getUserById(id) != null) {
            userServices.deleteUser(id);
        }
    }
}
