package com.messenger.serservice.Repository;

import com.messenger.serservice.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {


    Optional<Account> findAccountByAccountname(String accountname);



}
