package com.messenger.serservice.Services;

import com.messenger.serservice.DTO.AccountDTO;
import com.messenger.serservice.DTO.TokenResponse;
import com.messenger.serservice.Entity.Account;
import com.messenger.serservice.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.security.NoSuchAlgorithmException;

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
    @Transactional
    public String register(AccountDTO accountDTO) throws AccountNotFoundException, NoSuchAlgorithmException {

        if (accountRepository.findAccountByAccountname(accountDTO.getAccountName()).isPresent()) {

            throw new AccountNotFoundException("Account with given account name already exists");
        }
        else {
            String token = jwtService.generateToken(accountDTO);
            jwtService.validateToken(token,accountDTO);
            Account account = mapper.dtoToEntity(accountDTO);
            account.setAccountname(accountDTO.getAccountName());
            accountRepository.save(account);


            return token;
        }
    }

@Transactional
    public String login(AccountDTO accountDTO) throws NoSuchAlgorithmException {
        String username =  accountDTO.getAccountName();
        Account account = accountRepository.findAccountByAccountname(username).orElseThrow(() -> new RuntimeException("Username not found"));
        String token = jwtService.generateToken(accountDTO);

//        Account account = accountRepository.findById(accountDTO.getAccountId()).orElseThrow(() -> new RuntimeException("Account not found"));


        if (!passwordEncoder.matches(accountDTO.getAccountPassword(), account.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        jwtService.validateToken(token,accountDTO);
        return token;
    }


}
