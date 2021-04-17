package com.skarim.user.object;

public class NotificationResponse {
    private String notificationServer;
    private String userId;
    private String message;

    public String getNotificationServer() {
        return notificationServer;
    }

    public void setNotificationServer(String notificationServer) {
        this.notificationServer = notificationServer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
