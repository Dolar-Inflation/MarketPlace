package com.messenger.productlist.Reposytories;

import com.messenger.productlist.Entity.serservice.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    Account findIdByAccountname(String accountname);

//    @Query("SELECT a.id FROM Account a WHERE a.accountname = :accountname")
//    Long findIdByAccountname(@Param("accountname") String accountname);
}
