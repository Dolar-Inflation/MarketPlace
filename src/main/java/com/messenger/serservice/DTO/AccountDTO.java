package com.messenger.serservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    private Long accountId;
    private String accountName;
    private String accountPassword;
}
