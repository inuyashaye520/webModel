package com.cn.idataitech.inuyasha.model.example;

import com.alibaba.fastjson.JSONObject;
import com.cn.idataitech.inuyasha.model.support.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class ExampleApi {

    /**
     * 测试获取信息
     */
    @RequestMapping(method = RequestMethod.GET)
    public JsonResult<JSONObject> listUserInfo() {
        JSONObject json = new JSONObject();
        json.put("username", "yegen520");
        json.put("password", "666666");
        return JsonResult.success(json);
    }


}