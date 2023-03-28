package com.example.exceldownload.controller;

import com.example.exceldownload.entity.Product;
import com.example.exceldownload.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/findAll")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
    @GetMapping("/downloadProduct")
    public ResponseEntity<InputStreamResource> download() {
     String fileName = "products.xlsx";
        ByteArrayInputStream  byteArrayInputStream  = productService.download();
        InputStreamResource  inputStreamResource  = new InputStreamResource(byteArrayInputStream);

        ResponseEntity<InputStreamResource>  responseEntity =ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(inputStreamResource);
        return responseEntity;

    }
}
