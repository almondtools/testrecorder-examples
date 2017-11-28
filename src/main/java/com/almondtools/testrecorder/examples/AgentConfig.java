package com.almondtools.testrecorder.examples;

import static java.util.Arrays.asList;

import java.nio.file.Paths;
import java.util.List;

import net.amygdalum.testrecorder.DefaultTestRecorderAgentConfig;
import net.amygdalum.testrecorder.ScheduledTestGenerator;
import net.amygdalum.testrecorder.SnapshotConsumer;
import net.amygdalum.testrecorder.profile.Classes;

public class AgentConfig extends DefaultTestRecorderAgentConfig {

	@Override
	public SnapshotConsumer getSnapshotConsumer() {
		return new ScheduledTestGenerator()
			.withDumpOnShutDown(true)
			.withDumpTo(Paths.get("target/generated"));
	}
	
	@Override
	public long getTimeoutInMillis() {
		return 100_000;
	}

	@Override
	public List<Classes> getClasses() {
		return asList(Classes.byPackage("com.almondtools.testrecorder.examples"));
	}

}
