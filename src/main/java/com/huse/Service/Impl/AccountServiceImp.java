package com.huse.Service.Impl;

import com.huse.Service.AccountService;
import com.huse.mapper.AccountMapper;
import com.huse.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public int deleteByPrimaryKey(String account) {
        return accountMapper.deleteByPrimaryKey(account);
    }

    @Override
    public int insert(Account record) {
        return accountMapper.insert(record);
    }

    @Override
    public int insertSelective(Account record) {
        return accountMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Account record) {
        return accountMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Account record) {
        return accountMapper.updateByPrimaryKey(record);
    }

    @Override
    public boolean selectByAccountPassword(String account, String password) {
        return accountMapper.selectByAccountPassword(account,password) != null;
    }
}
