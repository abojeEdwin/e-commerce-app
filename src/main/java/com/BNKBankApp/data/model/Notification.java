package com.BNKBankApp.data.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;



@Data
@Document(collection="Notification")
public class Notification {

    @Id
    private String id;
    private String userId;
    private String message;
    private LocalDateTime timestamp;
    private NotificationStatus notificationStatus;
}
