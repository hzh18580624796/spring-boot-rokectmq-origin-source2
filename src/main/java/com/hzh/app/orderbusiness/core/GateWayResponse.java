package com.hzh.app.orderbusiness.core;

import lombok.Data;

@Data
public class GateWayResponse<Response> {

    private boolean success;

    private Response response;

    public boolean isSuccess(){
        return success;
    }
}
