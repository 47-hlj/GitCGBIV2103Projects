package com.cy.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 公领域对象，与表中字段有对应关系，可以基于此对象
 * 存储从数据库查询到的数据
 * 记住：Java中所以用于存储数据对象都上它实现Serializable接口，
 * 并且添加序列化id
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysNotice implements Serializable {
    private static final long serialVersionUID = 1874564668744770311L;
    private Integer id;
    private String title;
    private String type;
    private String content;
    private String status;
    private String remark;
    private String createdUser;
    private String modifiedUser;
    private Date createdTime;
    private Date modifiedTime;

}
