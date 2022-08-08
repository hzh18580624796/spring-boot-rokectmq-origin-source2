package com.hzh.app.flow.example.dto;

import lombok.Data;

@Data
public class HzhContext extends AbstarctContext{

    private HzhRequest request;
    private HzhSonResponse hzhSonResponse;
}
