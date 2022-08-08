package com.hzh.app.flow.example.dto.main;

import com.hzh.app.flow.example.dto.AbstarctContext;
import com.hzh.app.flow.example.dto.Hzh2Response;
import com.hzh.app.flow.example.dto.HzhResponse;
import lombok.Data;

@Data
public class HzhMainContext extends AbstarctContext {

    private HzhMainRequest mainRequest;
    private HzhResponse hzhResponse;
    private Hzh2Response hzh2Response;
}
