package com.hzh.app.flow;

import java.util.ArrayList;
import java.util.List;

public class FlowBuilder<Context, Response> {

    private String mainStep;
    private List<Step<Context, Response>> steps = new ArrayList<>();

    public FlowBuilder(String mainStep) {
        this.mainStep = mainStep;
    }

    public StepBuilder<Context, Response> step(String step) {
        return new StepBuilder<>(this, step);
    }

    public void addStep(Step<Context, Response> step) {
        steps.add(step);
    }

    public FlowTemplate<Context, Response> build() {
        return new FlowTemplate<>(this.mainStep, this.steps);
    }
}
