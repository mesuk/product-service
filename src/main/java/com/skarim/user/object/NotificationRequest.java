package com.skarim.user.object;

public class NotificationRequest {
    private String userServer;
    private String userId;
    private String userName;
    private String mobileNumber;
    private String email;
    private String message;

    public String getUserServer() {
        return userServer;
    }

    public void setUserServer(String userServer) {
        this.userServer = userServer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
