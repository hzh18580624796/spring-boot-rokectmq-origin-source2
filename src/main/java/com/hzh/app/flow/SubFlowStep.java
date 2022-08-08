package com.hzh.app.flow;

import lombok.Data;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

@Data
public class SubFlowStep<Context, Response, SubContext, SubResponse> implements Step<Context, Response> {

    private String step;
    private Predicate<Context> predicate;
    private Function<Context, SubContext> function;
    private Flow<SubContext, SubResponse> subFlow;
    private BiConsumer<SubResponse, Context> biConsumer;

    public SubFlowStep(String step, Predicate<Context> predicate, Function<Context, SubContext> function, BiConsumer<SubResponse, Context> biConsumer, Flow<SubContext, SubResponse> subFlow) {
        this.step = step;
        this.predicate = predicate;
        this.function = function;
        this.biConsumer = biConsumer;
        this.subFlow = subFlow;
    }

    @Override
    public Response runStep(Context context) {

        SubContext subContext = function.apply(context);
        SubResponse subResponse = subFlow.flowApply(subContext);
        biConsumer.accept(subResponse, context);
        return null;
    }
}
