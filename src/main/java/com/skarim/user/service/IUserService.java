package com.skarim.user.service;

import com.skarim.user.domain.User;
import com.skarim.user.object.UserSaveResponse;

import java.util.List;

public interface IUserService {
    public UserSaveResponse saveUpdateUser(User request,String userServer);
    public boolean deleteUser(User request);
    public List<User> fetchAllUser();
    public List<User> findUserByMobileNumber(String mobileNumber);
}
