package com.hzh.app.gateway;

import lombok.Data;

@Data
public class GateWayRequest<Request> {

    private Request request;
}
