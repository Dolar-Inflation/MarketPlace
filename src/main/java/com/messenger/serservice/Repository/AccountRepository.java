package com.messenger.serservice.Repository;

import com.messenger.serservice.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Optional<Account> findById(long id);

    Optional<Account> findAccountByAccountname(String accountname);



}
