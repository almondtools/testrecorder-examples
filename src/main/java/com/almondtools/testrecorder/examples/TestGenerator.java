package com.almondtools.testrecorder.examples;

import java.nio.file.Paths;

import net.amygdalum.testrecorder.ScheduledTestGenerator;
import net.amygdalum.testrecorder.profile.AgentConfiguration;

public class TestGenerator extends ScheduledTestGenerator {

	public TestGenerator(AgentConfiguration config) {
		super(config);
		this.generateTo = Paths.get("target/generated");
		this.dumpOnShutdown(true);
	}

}
