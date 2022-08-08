package com.hzh.app.flow;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SubStepBuilder<Context, Response, SubContext, SubResponse> {

    private String step;
    private Predicate<Context> predicate;
    private Function<Context, SubContext> function;
    private BiConsumer<SubResponse, Context> biConsumer;
    private Flow<SubContext, SubResponse> subFlow;
    private FlowBuilder<Context, Response> flow;

    public SubStepBuilder(FlowBuilder<Context, Response> flow,
                          String step,
                          Predicate<Context> predicate,
                          Function<Context, SubContext> function,
                          BiConsumer<SubResponse, Context> biConsumer) {
        this.flow = flow;
        this.step = step;
        this.predicate = predicate;
        this.function = function;
        this.biConsumer = biConsumer;
    }

    public SubStepBuilder<Context, Response, SubContext, SubResponse> flow(Flow<SubContext, SubResponse> subFlow) {
        this.subFlow = subFlow;
        return this;
    }

    public FlowBuilder<Context, Response> subEnd() {
        flow.addStep(new SubFlowStep<>(this.step, this.predicate, function, biConsumer, subFlow));
        return flow;
    }
}
