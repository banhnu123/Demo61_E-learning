package com.data.service;

import com.data.entity.Account;
import com.data.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUsername(username);
        if ((account == null)) {
            throw new UsernameNotFoundException("Account is correct");
        }

        // spring security dung ham nay ,nhan gia tri va
        //kiem tra username,password
        // co dung nhu trong db khong

        //Collection.emptyList() : tra ve mot list rong
//        return new User(username,account.getPassword(), Collections.emptyList());
        return new User(username,account.getPassword(),
                AuthorityUtils.createAuthorityList(account.getRole()));
    }
}
