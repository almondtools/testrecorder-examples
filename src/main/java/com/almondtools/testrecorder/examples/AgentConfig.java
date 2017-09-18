package com.almondtools.testrecorder.examples;

import static java.util.Arrays.asList;

import java.nio.file.Paths;
import java.util.List;

import net.amygdalum.testrecorder.DefaultTestRecorderAgentConfig;
import net.amygdalum.testrecorder.Packages;
import net.amygdalum.testrecorder.ScheduledTestGenerator;
import net.amygdalum.testrecorder.SnapshotConsumer;

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
	public List<Packages> getPackages() {
		return asList(Packages.byPrefix("com.almondtools.testrecorder.examples"));
	}

}
