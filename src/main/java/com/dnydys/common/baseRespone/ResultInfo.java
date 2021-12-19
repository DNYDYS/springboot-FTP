package com.dnydys.common.baseRespone;

/**
 * @Classname ResultInfo
 * @Description TODO
 * @Date 2021/12/17 20:50
 * @Created by hasee
 */
public class ResultInfo<T> {

    public Enum resultCode;

    public T data;

    public Enum getResultCode() {
        return resultCode;
    }

    public void setResultCode(Enum resultCode) {
        this.resultCode = resultCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
