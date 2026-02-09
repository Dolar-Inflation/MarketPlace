package com.messenger.serservice.Services;

import com.messenger.serservice.DTO.AccountDTO;
import com.messenger.serservice.Entity.Account;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
//TODO написать итератор который проходится по всем полям обьекта чтобы сделать метод универсальным
public class Mapper {

    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper mapper = new ObjectMapper();

    public Mapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Account dtoToEntity(AccountDTO dto ) {
        Account account = new Account();
        account.setAccountname(dto.getAccountName());
        account.setPassword(passwordEncoder.encode(dto.getAccountPassword()));
        account.setId(dto.getAccountId());
        return account;

    }



}
