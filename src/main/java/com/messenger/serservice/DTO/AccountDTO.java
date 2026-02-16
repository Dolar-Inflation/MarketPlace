package com.messenger.serservice.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AccountDTO {
    private Long accountId;
    private String accountName;
    private String accountPassword;
}
