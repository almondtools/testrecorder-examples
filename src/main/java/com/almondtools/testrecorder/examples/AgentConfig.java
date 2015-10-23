package com.almondtools.testrecorder.examples;

import static java.util.Arrays.asList;

import java.nio.file.Paths;
import java.util.List;

import com.almondtools.testrecorder.SnapshotConfig;
import com.almondtools.testrecorder.generator.MethodSnapshotConsumer;
import com.almondtools.testrecorder.generator.ScheduledTestGenerator;
import com.almondtools.testrecorder.generator.ValueSnapshotConsumer;

public class AgentConfig implements SnapshotConfig {

	@Override
	public MethodSnapshotConsumer getMethodConsumer() {
		return new ScheduledTestGenerator()
			.withDumpOnShutDown(true)
			.withDumpTo(Paths.get("target/generated"));
	}
	
	@Override
	public ValueSnapshotConsumer getValueConsumer() {
		return null;
	}

	@Override
	public long getTimeoutInMillis() {
		return 100_000;
	}

	@Override
	public List<String> getPackages() {
		return asList("com.almondtools.testrecorder.examples");
	}
}
