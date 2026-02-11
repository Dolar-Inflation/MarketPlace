package com.messenger.serservice.Services;

import com.messenger.serservice.DTO.AccountDTO;
import com.messenger.serservice.DTO.TokenResponse;
import com.messenger.serservice.Entity.Account;
import com.messenger.serservice.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final Mapper mapper;

    private final PasswordEncoder passwordEncoder;


    public UserService(AccountRepository accountRepository, JwtService jwtService, Mapper mapper, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.jwtService = jwtService;
        this.mapper = mapper;

        this.passwordEncoder = passwordEncoder;
    }

    public TokenResponse register(AccountDTO accountDTO) {


        Account account = mapper.dtoToEntity(accountDTO);
        accountRepository.save(account);

        return new TokenResponse(jwtService.generateToken(account));



    }


    public TokenResponse login(AccountDTO accountDTO) {


        Account name = accountRepository.findAccountByAccountname(accountDTO.getAccountName()).orElseThrow(() -> new RuntimeException("Username not found"));

//        Account account = accountRepository.findById(accountDTO.getAccountId()).orElseThrow(() -> new RuntimeException("Account not found"));


        if (!passwordEncoder.matches(accountDTO.getAccountPassword(), name.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }
        return new TokenResponse(jwtService.generateToken(name));
    }


}
