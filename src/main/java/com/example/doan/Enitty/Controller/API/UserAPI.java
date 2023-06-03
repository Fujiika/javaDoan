package com.example.doan.Enitty.Controller.API;

import com.example.doan.Enitty.Dto.SanPhamDto;
import com.example.doan.Enitty.Dto.UserDto;
import com.example.doan.Enitty.Entity.Quyen;
import com.example.doan.Enitty.Entity.SanPham;
import com.example.doan.Enitty.Entity.User;
import com.example.doan.Enitty.Repository.IQuyenRepository;
import com.example.doan.Enitty.Services.QuyenServices;
import com.example.doan.Enitty.Services.UserServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private IQuyenRepository quyenRepository;

    private UserDto convertToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setMaUser(user.getMaUser());
        userDto.setTenUser(user.getTenUser());
        userDto.setUserName(user.getUserName());
        userDto.setMatKhau(user.getMatKhau());
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
        user.setUserName(userDto.getUserName());
        user.setMatKhau(userDto.getMatKhau());
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
        user.setUserName(userDto.getUserName());
        user.setMatKhau(userDto.getMatKhau());
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

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            // Kiểm tra xem tên người dùng đã được sử dụng chưa
            if (userServices.isUsernameTaken(user.getUserName())) {
                throw new Exception("Username đã được sử dụng");
            }

            User newUser = new User();
            newUser.setTenUser(user.getTenUser());
            newUser.setUserName(user.getUserName());
            newUser.setMatKhau(user.getMatKhau());
            newUser.setSdt(user.getSdt());
            newUser.setDiaChi(user.getDiaChi());
            newUser.setEmail(user.getEmail());

            // Lấy quyền theo tên quyền
            Quyen quyen = quyenServices.getQuyenByName(user.getQuyen().getTenQuyen());
                if (quyen == null) {
                    throw new Exception("Không tìm thấy quyền");
                }
            newUser.setQuyen(quyen);

            // Lưu người dùng mới vào cơ sở dữ liệu
            userServices.addUser(newUser);

            return new ResponseEntity<>("Đăng ký thành công", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            User existingUser = userServices.loginUser(user.getUserName(), user.getMatKhau());
            if (existingUser == null) {
                throw new Exception("Tên người dùng hoặc mật khẩu không đúng");
            }
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
