package com.hzh.app.orderbusiness.core;

import lombok.Data;

@Data
public class GateWayRequest<Request> {
    private Request request;
}
