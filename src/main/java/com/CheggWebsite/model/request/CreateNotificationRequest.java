package com.CheggWebsite.model.request;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Data
@Builder
public class CreateNotificationRequest {

    String idempotencyKey;
    String source;
    String channelValue;
    String channelType;
    String templateId;
    Map<String, String> dynamicFields;
}
