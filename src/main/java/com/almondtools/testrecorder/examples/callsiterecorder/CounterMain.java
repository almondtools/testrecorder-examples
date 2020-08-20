package com.almondtools.testrecorder.examples.callsiterecorder;

import static java.util.Collections.emptyList;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.amygdalum.testrecorder.DefaultPerformanceProfile;
import net.amygdalum.testrecorder.DefaultSerializationProfile;
import net.amygdalum.testrecorder.DefaultSnapshotConsumer;
import net.amygdalum.testrecorder.callsiterecorder.CallsiteRecorder;
import net.amygdalum.testrecorder.deserializers.DeserializerFactory;
import net.amygdalum.testrecorder.deserializers.DeserializerTypeManager;
import net.amygdalum.testrecorder.deserializers.builder.SetupGenerators;
import net.amygdalum.testrecorder.deserializers.matcher.MatcherGenerators;
import net.amygdalum.testrecorder.generator.JUnit4TestTemplate;
import net.amygdalum.testrecorder.generator.MethodGenerator;
import net.amygdalum.testrecorder.profile.AgentConfiguration;
import net.amygdalum.testrecorder.profile.ClassPathConfigurationLoader;
import net.amygdalum.testrecorder.profile.DefaultPathConfigurationLoader;
import net.amygdalum.testrecorder.profile.PerformanceProfile;
import net.amygdalum.testrecorder.profile.SerializationProfile;
import net.amygdalum.testrecorder.profile.SnapshotConsumer;
import net.amygdalum.testrecorder.types.ContextSnapshot;
import net.amygdalum.testrecorder.types.TypeManager;

public class CounterMain {

	public static void main(String[] args) throws Exception {
		Counter counter = new Counter();
		try (CallsiteRecorder recorder = CallsiteRecorder.create(Counter.class.getDeclaredMethod("inc"))) {
			printTest(recorder.record(() -> {
				int doubleInc = counter.inc().inc().get();
				System.out.println(doubleInc);
			}));
			printTest(recorder.record(() -> {
				int resetInc = counter.reset().inc().get();
				System.out.println(resetInc);
			}));
		}

	}

	private static void printTest(CompletableFuture<List<ContextSnapshot>> recordings) {
		AgentConfiguration config = new AgentConfiguration(new ClassPathConfigurationLoader(), new DefaultPathConfigurationLoader())
			.withDefaultValue(SerializationProfile.class, DefaultSerializationProfile::new)
			.withDefaultValue(PerformanceProfile.class, DefaultPerformanceProfile::new)
			.withDefaultValue(SnapshotConsumer.class, DefaultSnapshotConsumer::new);

		TypeManager types = new DeserializerTypeManager();
		DeserializerFactory setup = new SetupGenerators(config);
		DeserializerFactory matcher = new MatcherGenerators(config);
		recordings.thenAccept(snapshots -> snapshots.stream().forEach(snapshot -> System.out.println(new MethodGenerator(0, types, setup, matcher, new JUnit4TestTemplate(), emptyList())
			.analyze(snapshot)
			.generateArrange()
			.generateAct()
			.generateAssert()
			.generateTest())));
	}
}
