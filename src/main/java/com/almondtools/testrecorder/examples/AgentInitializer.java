package com.almondtools.testrecorder.examples;

import net.amygdalum.testrecorder.runtime.TestRecorderAgentInitializer;

public class AgentInitializer implements TestRecorderAgentInitializer {

    @Override
    public void run() {
        System.out.println("starting initializer");
    }

}
