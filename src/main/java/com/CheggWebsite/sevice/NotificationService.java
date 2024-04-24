package com.CheggWebsite.sevice;

import com.fasterxml.jackson.databind.ObjectMapper;

public class NotificationService {

    private static ObjectMapper mapper;
    static {
        mapper =new ObjectMapper();
        mapper.findAndRegisterModules();

    }
    public void sendNotification(String Source)
    {

    }


}
