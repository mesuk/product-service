package com.skarim.product;


import com.skarim.product.object.MainResponse;
import com.skarim.product.object.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private Environment environment;

    @RequestMapping(method = RequestMethod.GET, value="/")
    public ResponseEntity<MainResponse> hello(){

        MainResponse mainResponse=new MainResponse();

        String serverName=environment.getProperty("my_message");

        String ipInfo=getIpInfo();

        mainResponse.setServerName(serverName);
        mainResponse.setIpInfo(ipInfo);
        mainResponse.setDateTime(new Date().toString());


        List<Product> productList=new ArrayList<>();

        Product product=new Product("1001","Mobile");
        productList.add(product);
        product=new Product("1002","Fan");
        productList.add(product);

        product=new Product("1003","Computer");
        productList.add(product);

        mainResponse.setProducts(productList);


        return new ResponseEntity<>(
                mainResponse,
                HttpStatus.OK);
    }

    private String getIpInfo(){

        String ipInfo="";

        InetAddress ip;
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            ipInfo+=("Your current IP address : " + ip)+"\n";
            ipInfo+=("Your current Hostname : " + hostname);

        } catch (UnknownHostException e) {

            e.printStackTrace();

            ipInfo+=e.getMessage();

        }

        return ipInfo;

    }

}
