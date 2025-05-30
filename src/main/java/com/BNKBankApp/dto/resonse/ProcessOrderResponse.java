package com.BNKBankApp.dto.resonse;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProcessOrderResponse {

    private LocalDateTime dateProcessed;
    private String message;

}
