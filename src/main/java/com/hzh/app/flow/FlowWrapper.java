package com.hzh.app.flow;

import com.hzh.app.flow.example.dto.AbstarctContext;
import lombok.Data;

import java.util.function.BiConsumer;
import java.util.function.Function;

@Data
public class FlowWrapper<Context extends AbstarctContext, Response, SubContext extends AbstarctContext, SubResponse> implements Flow<Context, Response> {

    public static final String Flow_Wrapper_Name = "-wrapper";

    private Flow<SubContext, SubResponse> subFlow;
    private Function<Context, SubContext> function;
    private BiConsumer<SubResponse, Context> biConsumer;
    private Response Empty = null;

    public String flowName(Context context) {
        return context.getFlowName() + Flow_Wrapper_Name;
    }

    @Override
    public Response flowApply(Context context) {
        return startStep(flowName(context))
                //c = context
                //subr = subResponse
                .step("发起子流程").subFlow(c -> function.apply(c), (BiConsumer<SubResponse, Context>) (subr, c) -> biConsumer.accept(subr, c)).flow(subFlow).subEnd()
                .step("结束子流程").end((x) -> Empty)
                .build()
                .apply(context);
    }
}
