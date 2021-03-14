package com.scorsaro.cataloguehorae.controller;

import com.scorsaro.cataloguehorae.dao.ProductCategoryRepository;
import com.scorsaro.cataloguehorae.dao.ProductRepository;
import com.scorsaro.cataloguehorae.dto.ProductDTO;
import com.scorsaro.cataloguehorae.entity.Product;
import com.scorsaro.cataloguehorae.entity.ProductCategory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogController {

    ProductRepository productRepository;
    ProductCategoryRepository productCategoryRepository;

    public CatalogController(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository){
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    @PostMapping("/api/products/post")
    boolean AddNewProduct(@RequestBody ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSku(productDTO.getSku());
        product.setActive(true);
        product.setImageUrl(productDTO.getImageUrl());
        product.setUnitsInStock(100);
        product.setUnitPrice(productDTO.getUnitPrice());
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        product.setCategory(productCategories.get(0));
        productRepository.save(product);
        return true;
    }


    @PostMapping("/api/products/delete")
    boolean DeleteProduct(@RequestBody String sku){
        List<Product> tProducts = productRepository.findBySku(sku);
        if(tProducts.size() == 0 ) return false;
        else productRepository.delete(tProducts.get(0));
        return true;
    }

    @PostMapping("/api/products/update/{sku}")
    boolean UpdateProductName(@PathVariable String sku,@RequestBody String name){
        List<Product> tProducts = productRepository.findBySku(sku);
        if(tProducts.size() == 0 ) return false;
        else {
            tProducts.get(0).setName(name);
            productRepository.save(tProducts.get(0));
        }
        return true;
    }
}
