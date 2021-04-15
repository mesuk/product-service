package com.skarim.user.object;

import java.net.InetAddress;

public class BaseResponse {
    private InetAddress ip;
    private Object data;

    public BaseResponse() {
    }

    public BaseResponse(InetAddress ip) {
        this.setIp(ip);
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
