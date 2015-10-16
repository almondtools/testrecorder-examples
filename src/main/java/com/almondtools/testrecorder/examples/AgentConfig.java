package com.almondtools.testrecorder.examples;

import static java.util.Arrays.asList;

import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;

import com.almondtools.testrecorder.GeneratedSnapshot;
import com.almondtools.testrecorder.SnapshotConfig;
import com.almondtools.testrecorder.generator.ScheduledTestGenerator;

public class AgentConfig implements SnapshotConfig {

	@Override
	public Consumer<GeneratedSnapshot> getConsumer() {
		return new ScheduledTestGenerator()
			.withDumpOnShutDown(true)
			.withDumpTo(Paths.get("target/generated"));
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
