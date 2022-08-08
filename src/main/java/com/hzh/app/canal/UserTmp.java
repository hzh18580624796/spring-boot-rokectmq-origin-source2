package com.hzh.app.canal;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UserTmp implements Serializable {
    private String id;
    private String name;
    private BigDecimal big;
}
