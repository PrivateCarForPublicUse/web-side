package com.training.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//消息反馈表
@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    //对应的表名
    private String tableName;
    //对应的表内的id
    private Long idInTable;

    public Message(){}
    public Message(String tableName, Long idInTable) {
        this.tableName = tableName;
        this.idInTable = idInTable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getIdInTable() {
        return idInTable;
    }

    public void setIdInTable(Long idInTable) {
        this.idInTable = idInTable;
    }
}
