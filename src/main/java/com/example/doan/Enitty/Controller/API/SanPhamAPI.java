package com.example.doan.Enitty.Controller.API;

import com.example.doan.Enitty.Dto.SanPhamDto;
import com.example.doan.Enitty.Entity.LoaiSP;
import com.example.doan.Enitty.Entity.SanPham;
import com.example.doan.Enitty.Services.LoaiSpServices;
import com.example.doan.Enitty.Services.SanPhamServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/sanphams")
public class SanPhamAPI {
    @Autowired
    private SanPhamServices sanPhamServices;

    @Autowired
    private LoaiSpServices loaiSpServices;

    private SanPhamDto convertToSanPhamDto(SanPham sanPham){
        SanPhamDto sanPhamDto = new SanPhamDto();
        sanPhamDto.setMaSp(sanPham.getMaSp());
        sanPhamDto.setTenSP(sanPham.getTenSP());
        sanPhamDto.setDonGia(sanPham.getDonGia());
        sanPhamDto.setHinhAnh(sanPham.getHinhAnh());
        sanPhamDto.setTenLoai(loaiSpServices.getCategoryById(sanPham.getLoaiSP().getMaLoai()).getTenLoai());
        return sanPhamDto;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public SanPhamDto getSPById(@PathVariable Long id){
        SanPham sanPham = sanPhamServices.getSpById(id);
        return  convertToSanPhamDto(sanPham);
    }

    @GetMapping
    @ResponseBody
    public List<SanPhamDto> getAllSanPham(){
        List<SanPham> sanPhams = sanPhamServices.getAllSP();
        List<SanPhamDto> sanPhamDtos = new ArrayList<>();
        for(SanPham sp : sanPhams){
            sanPhamDtos.add(convertToSanPhamDto(sp));
        }
        return  sanPhamDtos;
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<SanPhamDto> addSanPham(@RequestBody SanPhamDto sanPhamDto) {
        SanPham sanPham = new SanPham();
        sanPham.setMaSp(sanPhamDto.getMaSp());
        sanPham.setTenSP(sanPhamDto.getTenSP());
        sanPham.setDonGia(sanPhamDto.getDonGia());
        sanPham.setHinhAnh(sanPhamDto.getHinhAnh());
        sanPham.setLoaiSP(loaiSpServices.getLoaiSPByName(sanPhamDto.getTenLoai()));
        SanPham savedSanPham = sanPhamServices.addSp(sanPham);
        SanPhamDto savedSanPhamDto = convertToSanPhamDto(savedSanPham);
        return ResponseEntity.ok(savedSanPhamDto);
    }

    @PutMapping("/edit")
    @ResponseBody
    public ResponseEntity<SanPhamDto> editSanPham(@RequestBody SanPhamDto sanPhamDto) {
        SanPham sanPham = sanPhamServices.getSpById(sanPhamDto.getMaSp());
        //sanPham.setMaSp(sanPhamDto.getMaSp());
        sanPham.setTenSP(sanPhamDto.getTenSP());
        sanPham.setDonGia(sanPhamDto.getDonGia());
        sanPham.setHinhAnh(sanPhamDto.getHinhAnh());
        sanPham.setLoaiSP(loaiSpServices.getLoaiSPByName(sanPhamDto.getTenLoai()));
        SanPham updatedSanPham = sanPhamServices.editSp(sanPham);
        SanPhamDto updatedSanPhamDto = convertToSanPhamDto(updatedSanPham);
        return ResponseEntity.ok(updatedSanPhamDto);
    }



    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteSPById(@PathVariable Long id){
        if(sanPhamServices.getSpById(id) != null){
            sanPhamServices.deleteSp(id);
        }
    }
//    @GetMapping("/sanphams")
//    public List<SanPham> getAllSP() {
//        return sanPhamServices.getAllSP();
//    }
//
//    @GetMapping("/sanphams/{id}")
//    public ResponseEntity<SanPham> getSpById(@PathVariable(value = "id") Long SpId)
//            throws ResourceNotFoundException {
//        SanPham sanPham = sanPhamServices.getSpById(SpId);
//        return ResponseEntity.ok().body(sanPham);
//    }
//
//    @PostMapping("/add")
//    public SanPham createSp(@Valid @RequestBody SanPham sp) {
//        return sanPhamServices.addSp(sp);
//    }
//
//    @PutMapping("/edit/{id}")
//    public ResponseEntity<SanPham> updateSp(@PathVariable(value = "id") Long SpId, @Valid @RequestBody SanPham SpDetails) throws ResourceNotFoundException {
//        SanPham sp = sanPhamServices.getSpById(SpId);
//        sp.setTenSP(SpDetails.getTenSP());
//        sp.setDonGia(SpDetails.getDonGia());
//        sp.setHinhAnh(SpDetails.getHinhAnh());
//        sp.setLoaiSP(SpDetails.getLoaiSP());
//        final SanPham updatedSp = sanPhamServices.editSp(sp);
//        return ResponseEntity.ok(updatedSp);
//    }
//
//    @DeleteMapping("/delelte/{id}")
//    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long SpId)
//            throws ResourceNotFoundException {
//        SanPham sp = sanPhamServices.getSpById(SpId);
//        sanPhamServices.deleteSp(SpId);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }
}
