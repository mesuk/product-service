package com.skarim.product;


import com.skarim.product.object.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @RequestMapping(method = RequestMethod.GET, value="/")
    public ResponseEntity<List<Product>> hello(){

        List<Product> productList=new ArrayList<>();

        Product product=new Product("1001","Mobile");
        productList.add(product);
        product=new Product("1002","Fan");
        productList.add(product);

        product=new Product("1003","Computer");
        productList.add(product);


        return new ResponseEntity<>(
                productList,
                HttpStatus.OK);
    }

}
