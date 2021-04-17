package com.skarim.user.object;

public class UserSaveResponse {
    private Object userServiceResponse;
    private Object notificationServerResponse;

    public Object getUserServiceResponse() {
        return userServiceResponse;
    }

    public void setUserServiceResponse(Object userServiceResponse) {
        this.userServiceResponse = userServiceResponse;
    }

    public Object getNotificationServerResponse() {
        return notificationServerResponse;
    }

    public void setNotificationServerResponse(Object notificationServerResponse) {
        this.notificationServerResponse = notificationServerResponse;
    }
}
