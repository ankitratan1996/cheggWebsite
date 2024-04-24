package com.CheggWebsite.model.request;

import lombok.*;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateNotificationDto {

    String idempotencyKey;
    String source;
    String channelValue;
    String channelType;
    String templateId;
    Map<String, String> dynamicFields;

}
