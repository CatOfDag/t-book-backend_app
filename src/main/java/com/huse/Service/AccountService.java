package com.huse.Service;

import com.huse.pojo.Account;

public interface AccountService {
    int deleteByPrimaryKey(String account);

    int insert(Account record);

    int insertSelective(Account record);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    boolean selectByAccountPassword(String account, String password);
}
