package com.bysj.wywx.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("material")
public class Material implements Serializable {

    @TableId("material_id")
    private String materialID;
    private String name;
    private int number;
    @TableField("unit_of_measurement")
    private String unitOfMeasurement;
}
