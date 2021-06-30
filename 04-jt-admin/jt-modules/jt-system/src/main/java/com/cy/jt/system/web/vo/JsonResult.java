package com.cy.jt.system.web.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.management.ObjectName;
import java.io.Serializable;

@Data
@Accessors
public class JsonResult implements Serializable {
    private static final long serialVersionUID = -8150847504641468689L;
    //响应状态码
    private int code=1;
    //具体消息
    private String message;
    //一般对应查询结果
    private Object data;
    public JsonResult(String message){
        this.message=message;
    }
    public JsonResult(Object data){
        this.data=data;
    }
    public JsonResult(Throwable e){
        this.code=0;
        this.message=e.getMessage();
    }


}
