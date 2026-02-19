package com.messenger.productlist.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    private final AccountDTO account;
    private final String token;

    public AuthRequest(AccountDTO account, String token) {
        this.account = account;
        this.token = token;
    }
}
