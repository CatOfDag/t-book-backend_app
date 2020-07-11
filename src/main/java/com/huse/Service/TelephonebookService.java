package com.huse.Service;

import com.huse.pojo.Telephonebook;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TelephonebookService {
    int deleteByPrimaryKey(Integer id);

    int insert(Telephonebook record);

    int insertSelective(Telephonebook record);

    Telephonebook selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Telephonebook record);

    int updateByPrimaryKey(Telephonebook record);

    List<String> selectById(Integer id);

    List<Telephonebook> selectAll();

    int insertToiphones(@Param("id")Integer id, @Param("iphonenum")String iphonenum);

    List<Telephonebook> selectUnKnownField(@Param("fieldname")String fieldname,@Param("value") String value);
}
