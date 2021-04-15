package com.skarim.user.service;

import com.skarim.user.domain.User;

import java.util.List;

public interface IUserService {
    public User saveUpdateUser(User request);
    public boolean deleteUser(User request);
    public List<User> fetchAllUser();
    public List<User> findUserByMobileNumber(String mobileNumber);
}
