package com.messenger.productlist.Reposytories;

import com.messenger.productlist.Entity.serservice.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends JpaRepository<Account, Integer> {

}
