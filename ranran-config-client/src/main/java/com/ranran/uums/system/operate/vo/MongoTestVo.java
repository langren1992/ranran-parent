package com.ranran.uums.system.operate.vo;

import com.ranran.core.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

/*
 * 部门条件查询视图
 * gen model 2018-01-20
 */
@Document(collection = "mongoTestVo")
public class MongoTestVo{

    @Id
    private String id;
    private Integer code;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
