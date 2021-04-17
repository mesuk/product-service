package com.skarim.user.service;

import com.skarim.user.domain.User;
import com.skarim.user.object.NotificationRequest;
import com.skarim.user.object.NotificationResponse;
import com.skarim.user.object.UserSaveResponse;
import com.skarim.user.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService implements IUserService {
    private static Log log = LogFactory.getLog(UserService.class);

    private UserRepository userRepository;
    private RestTemplate restTemplate;

    public UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public UserSaveResponse saveUpdateUser(User request,String userServer) {

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

        NotificationResponse notificationResponse = notifyUser(user,userServer);

        UserSaveResponse userSaveResponse = new UserSaveResponse();
        userSaveResponse.setNotificationServerResponse(notificationResponse);
        userSaveResponse.setUserServiceResponse(user);

        return userSaveResponse;
    }

    private NotificationResponse notifyUser(User user, String userServer) {
        String url = "http://NOTIFICATION-SERVICE/notification/saveNotification";

        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setEmail(user.getEmail());
        notificationRequest.setUserServer(userServer);
        notificationRequest.setMobileNumber(user.getMobileNumber());
        notificationRequest.setUserId(user.getId().toString());
        notificationRequest.setUserName(user.getUserName());
        notificationRequest.setMessage("Congratulations, your registration successful.");

        NotificationResponse responseEntity = restTemplate.postForObject(
                url,
                notificationRequest,
                NotificationResponse.class
        );

        return responseEntity;
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
