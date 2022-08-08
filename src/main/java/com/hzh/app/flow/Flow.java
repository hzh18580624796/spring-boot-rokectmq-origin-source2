package com.hzh.app.flow;

public interface Flow<Context, Response> {

    default FlowBuilder<Context, Response> startStep(String mainStep) {

        if (mainStep.startsWith("-")) {
            mainStep = mainStep.substring(1);
        }
        return new FlowBuilder<>("【" + mainStep + "】");
    }

    default String flowName(Context context) {
        return "";
    }

    Response flowApply(Context context);

}
