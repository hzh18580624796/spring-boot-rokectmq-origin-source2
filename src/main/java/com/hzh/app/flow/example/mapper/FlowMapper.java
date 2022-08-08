package com.hzh.app.flow.example.mapper;

import com.hzh.app.flow.Flow;
import com.hzh.app.flow.FlowWrapper;
import com.hzh.app.flow.example.dto.AbstarctContext;

public interface FlowMapper<Context extends AbstarctContext, SubContext extends AbstarctContext, SubResponse> {

    /**
     * 初始化动作
     */
    default void initAction(Context parent, SubContext child) {
        child.setFlowName(parent.getFlowName() + FlowWrapper.Flow_Wrapper_Name);
        parent.getChildContexts().add(child);
    }

    /**
     * 构建子上下文
     */
    SubContext buildSubContext(Context context);

    /**
     * 接受子响应
     */
    void acceptSubResponse(SubResponse subResponse, Context context);

    /**
     * 处理业务编码
     */
    String handleBusinessCode();

    /**
     * 获取子流程
     */
    Flow<SubContext, SubResponse> getSubFlow();

}
