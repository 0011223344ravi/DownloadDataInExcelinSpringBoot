package com.example.exceldownload.service;

import com.example.exceldownload.entity.Product;
import com.example.exceldownload.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

import static com.example.exceldownload.util.ExcelUtil.listToExcel;

@Service
public class ProductService {
     @Autowired
    private ProductRepo productRepo;
     public List<Product> getProducts(){
      return   productRepo.findAll();



     }
    public ByteArrayInputStream download(){
       return listToExcel(productRepo.findAll());
    }
}
