package com.hzh.app.gateway;

import lombok.Data;

@Data
public class GateWayResponse<Response> {

    private Response response;

    private GateWayStatus status;

    private String failCode;

    private String failMessage;

    public boolean isSuccess() {
        return GateWayStatus.Success == status;
    }
}
