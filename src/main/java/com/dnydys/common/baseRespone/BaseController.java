package com.dnydys.common.baseRespone;

import com.dnydys.common.code.ResponseCode;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname BaseController
 * @Description TODO
 * @Date 2021/12/17 21:04
 * @Created by hasee
 */
public class BaseController {

    //返回码
    protected Map<String, Object> response(ResponseCode code) {
        Map<String, Object> rspData = new HashMap<>();
        rspData.put("respCode", code.getCode());
        rspData.put("respDesc", code.getDesc());
        return rspData;
    }

    //返回码及不分页数据
    protected Map<String, Object> response(Object data, ResponseCode code) {
        Map<String, Object> rspData = new HashMap<>();
        rspData.put("rows", data);
        rspData.put("respCode", code.getCode());
        rspData.put("respDesc", code.getDesc());
        return rspData;
    }
}
