package com.woniu.dao.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xk
 * @since 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cusorder")
public class Cusorder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Date ordertime;

    private String sitename;

    private String phone;

    private String site;

    private Double money;

    private String busibess;

    private Long cusId;

    private String statu;

    private Long uId;

    private String prepar;

    private String auditor;

    private String version;

    private String name;


}
