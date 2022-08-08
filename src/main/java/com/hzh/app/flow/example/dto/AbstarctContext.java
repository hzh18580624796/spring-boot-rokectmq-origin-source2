package com.hzh.app.flow.example.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class AbstarctContext {

    private String flowName = "";
    /**
     * fixme 如果要取值的话for循环来拿
     */
    private List<AbstarctContext> childContexts = new ArrayList<>();
}
