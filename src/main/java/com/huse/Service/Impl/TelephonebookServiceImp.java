package com.huse.Service.Impl;

import com.huse.Service.TelephonebookService;
import com.huse.mapper.TelephonebookMapper;
import com.huse.pojo.Telephonebook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelephonebookServiceImp implements TelephonebookService {

    @Autowired
    TelephonebookMapper telephonebookMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return telephonebookMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Telephonebook record) {
        return telephonebookMapper.insert(record);
    }

    @Override
    public int insertSelective(Telephonebook record) {
        return telephonebookMapper.insertSelective(record);
    }

    @Override
    @Cacheable(value = "tbook")
    public Telephonebook selectByPrimaryKey(Integer id) {
        return telephonebookMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Telephonebook record) {
        return telephonebookMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Telephonebook record) {
        return telephonebookMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<String> selectById(Integer id) {
        return telephonebookMapper.selectById(id);
    }

    @Override
    public List<Telephonebook> selectAll() {
        return telephonebookMapper.selectAll();
    }

    @Override
    public int insertToiphones(Integer id, String iphonenum) {
        return telephonebookMapper.insertTotelephones(id,iphonenum);
    }

    @Override
    public List<Telephonebook> selectUnKnownField(String fieldname, String value) {
        return telephonebookMapper.selectUnKnownField(fieldname,value);
    }
}
