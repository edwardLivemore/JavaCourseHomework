package service.impl;

import service.ISmsService;

public class SmsServiceImpl implements ISmsService {
    @Override
    public void sendMsg() {
        System.out.println("sending sms msg...");
    }
}
