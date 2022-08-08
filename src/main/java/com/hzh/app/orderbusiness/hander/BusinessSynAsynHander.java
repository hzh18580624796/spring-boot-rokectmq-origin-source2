package com.hzh.app.orderbusiness.hander;

import com.hzh.app.orderbusiness.core.BusinessResponse;
import com.hzh.app.orderbusiness.core.GateWayResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 业务同步 异步处理器
 */
@Slf4j
public abstract class BusinessSynAsynHander<Context, Response> implements Serializable {

    /**
     * fixme 同步处理
     */
    public BusinessResponse syn(Context context, GateWayResponse<Response> gateWayResponse) {

        log.info(this.getClass().getSimpleName() + "开始同步处理-合并");
        accumulate(context, gateWayResponse);

        log.info(this.getClass().getSimpleName() + "开始handling");
        BusinessResponse handleResult = handle(context);

        log.info("handleResult={},identityHashCode={}", handleResult, System.identityHashCode(handleResult));

        return handleResult;
    }

    /**
     * fixme 异步处理
     */
    public BusinessResponse asyn(GateWayResponse<Response> gateWayResponse) {

        log.info(this.getClass().getSimpleName() + "开始异步处理-幂等 或 判重");
        check(gateWayResponse);

        log.info(this.getClass().getSimpleName() + "开始异步处理-异步上下文准备");
        Context context = asynContextPrepare(gateWayResponse);

        log.info(this.getClass().getSimpleName() + "异步上下文准备完毕，准备拉起同步处理");
        return syn(context, gateWayResponse);
    }

    /**
     * fixme 幂等 或 判重
     */
    public boolean check(GateWayResponse<Response> gateWayResponse) {
        return false;
    }

    abstract Context asynContextPrepare(GateWayResponse<Response> gateWayResponse);

    abstract void accumulate(Context context, GateWayResponse<Response> gateWayResponse);

    abstract BusinessResponse handle(Context context);
}
