package com.sk02.sk02_notification_service.dto.notification;

import javax.validation.constraints.NotEmpty;

public class NotificationCreateDto {

    @NotEmpty
    private String type;

    @NotEmpty
    private String subject;

    @NotEmpty
    private String message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
