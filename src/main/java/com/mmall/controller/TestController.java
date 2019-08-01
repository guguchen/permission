package com.mmall.controller;

import com.mmall.common.JsonData;
import com.mmall.exception.PermissionException;
import com.mmall.param.TestVo;
import com.mmall.util.BeanValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {
    @RequestMapping("hello.json")
    @ResponseBody
    public JsonData hello(){
        log.info("hello");
        throw new PermissionException("test exception");
    }
    @RequestMapping("validation.json")
    @ResponseBody
    public JsonData hello(TestVo vo){
        log.info("validate");
        Map<String,String> map= BeanValidator.validateObject(vo);
        if(MapUtils.isNotEmpty(map)){
            for(Map.Entry<String,String> entry:map.entrySet()){
                log.info("{}->{}",entry.getKey(),entry.getValue());
            }
        }
        return JsonData.success("test validate");
    }




}
