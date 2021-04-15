package com.skarim.user.service;

import com.skarim.user.domain.User;
import com.skarim.user.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private static Log log = LogFactory.getLog(UserService.class);

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUpdateUser(User request) {

        User user;

        if (request.getId() == null || request.getId() == 0) {
            if (userRepository.countAllByUserName(request.getUserName()) > 0) {
                log.error("User already exists");
                throw new RuntimeException("User already exists");
            }

            user = new User();
            user.setUserName(request.getUserName());
            user.setFullName(request.getFullName());
            user.setEmail(request.getEmail());
            user.setMobileNumber(request.getMobileNumber());
            user.setGender(request.getGender());
        } else {
            user = request;
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public boolean deleteUser(User request) {
        userRepository.deleteById(request.getId());
        return true;
    }

    @Override
    public List<User> fetchAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findUserByMobileNumber(String mobileNumber) {
        return userRepository.findAllByMobileNumber(mobileNumber);
    }
}
