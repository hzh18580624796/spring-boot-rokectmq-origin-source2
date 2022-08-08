package com.hzh.app.flow;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

//fixme 简单是简单，但是不符合面向对象逻辑
public class StepBuilder<Context, Response> {

    private String step;
    private Predicate<Context> predicate;
    private FlowBuilder<Context, Response> flow;

    public StepBuilder(FlowBuilder<Context, Response> flow, String step) {
        this.flow = flow;
        this.step = step;
    }

    public StepBuilder<Context, Response> predicate(Predicate<Context> predicate) {
        this.predicate = predicate;
        return this;
    }

    public <SubContext, SubResponse> SubStepBuilder<Context, Response, SubContext, SubResponse> subFlow(Function<Context, SubContext> function,
                                                                                                        BiConsumer<SubResponse, Context> biConsumer) {
        return new SubStepBuilder<>(flow, step, predicate, function, biConsumer);
    }

    public FlowBuilder<Context, Response> invoke(Consumer<Context> consumer) {
        flow.addStep(new DefaultStep<>(this.step, this.predicate, consumer));
        return flow;
    }

    public FlowBuilder<Context, Response> end(Function<Context, Response> function) {
        flow.addStep(new EndStep<>(this.step, this.predicate, function));
        return flow;
    }
}
