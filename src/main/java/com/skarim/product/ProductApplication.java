package com.skarim.product;

import com.netflix.appinfo.AmazonInfo;
import com.skarim.product.config.EurekaInstanceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;


@EnableDiscoveryClient
@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }


    @Bean
    @Profile({"dev", "dc"})
    public EurekaInstanceConfig eurekaInstanceConfig() {
        return new EurekaInstanceConfig();
    }

}
