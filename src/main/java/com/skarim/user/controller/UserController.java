package com.skarim.user.controller;


import com.skarim.user.domain.User;
import com.skarim.user.object.BaseResponse;
import com.skarim.user.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/user")
@Api(tags = {"UserController"})
public class UserController {

    private Environment environment;

    private UserService userService;

    public UserController(Environment environment, UserService userService) {
        this.environment = environment;
        this.userService = userService;
    }

    @PostMapping(value = "/saveUser")
    public ResponseEntity<BaseResponse> saveUser(HttpServletRequest requestHeader, @RequestBody User request) throws RuntimeException {

        User user = userService.saveUpdateUser(request);

        BaseResponse baseResponse = new BaseResponse(getServerInfo());

        if(user != null){
            baseResponse.setData(user);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);

        }else{
            baseResponse.setData("Error occurred");
            return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping(value = "/fetchAllCustomer")
    public ResponseEntity<BaseResponse> fetchAllCustomer() throws RuntimeException {
        BaseResponse baseResponse = new BaseResponse(getServerInfo());
        baseResponse.setData(userService.fetchAllUser());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/fetchCustomerByMobileNumber")
    public ResponseEntity<BaseResponse> fetchCustomerByMobileNumber(@RequestParam String mobileNumber) throws RuntimeException {
        BaseResponse baseResponse = new BaseResponse(getServerInfo());
        baseResponse.setData(userService.findUserByMobileNumber(mobileNumber));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/deleteCustomer")
    public ResponseEntity<BaseResponse> deleteCustomer(HttpServletRequest requestHeader, @RequestBody User request) throws RuntimeException {

        BaseResponse baseResponse = new BaseResponse(getServerInfo());
        baseResponse.setData(userService.deleteUser(request));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    private InetAddress getServerInfo(){
        try {
            InetAddress ip = InetAddress.getLocalHost();
            return ip;
        } catch (UnknownHostException e) {

            e.printStackTrace();
            return null;
        }
    }

}
