package com.huse.controller;

import com.google.gson.Gson;
import com.huse.Service.TelephonebookService;
import com.huse.pojo.Telephonebook;
import com.huse.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/get")
public class TelephonebookController {

    @Autowired
    private TelephonebookService telephonebookService;
    @Autowired
    private JsonResult jsonResult;

    Gson gson = new Gson();

    enum fieldNames {Name,Department,Duty,Telephone};

    @RequestMapping("/data")
    @ResponseBody
    public String selectTelephoneNums(){
        List<Telephonebook> telephonebooks = telephonebookService.selectAll();
        if (telephonebooks.size() != 0){
            for (Telephonebook telephonebook : telephonebooks){
                telephonebook.setTelephonenum(telephonebookService.selectById(telephonebook.getId()));
            }
            jsonResult.setCode("201");//code ==> 201 data get success
            jsonResult.setInfo(gson.toJson(telephonebooks));
        }
        else {
            jsonResult.setCode("240");//code ==> 240 data get false
            jsonResult.setInfo("数据获取失败");
        }
        return gson.toJson(jsonResult);
    }

    @RequestMapping("/search")
    @ResponseBody
    public String searchData(@RequestBody String value){
        List<Telephonebook> telephonebooks = new ArrayList<>();
        for (fieldNames fieldName : fieldNames.values()) {
            telephonebooks = telephonebookService.selectUnKnownField(fieldName.toString(),value);
            if (telephonebooks.size() != 0){
                if (fieldName.toString().equals("Telephone")){
                    for (Telephonebook telephonebook : telephonebooks) {
                        telephonebook.setTelephonenum(telephonebookService.selectById(telephonebook.getId()));
                    }
                }
                break;
            }
        }
        if (telephonebooks.size() != 0){
            jsonResult.setCode("201");//code ==> 201 data get success
            jsonResult.setInfo(gson.toJson(telephonebooks));
        } else {
            jsonResult.setCode("161");//code ==> 201 data no search
            jsonResult.setInfo("未找到数据");
        }
        return gson.toJson(jsonResult);
    }

    @RequestMapping("/select")
    @ResponseBody
    public String select(int id){
        telephonebookService.selectByPrimaryKey(id);
        return gson.toJson(telephonebookService.selectByPrimaryKey(id));
    }
}
