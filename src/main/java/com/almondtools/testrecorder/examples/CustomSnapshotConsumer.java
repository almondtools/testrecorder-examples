package com.almondtools.testrecorder.examples;

import net.amygdalum.testrecorder.profile.AgentConfiguration;
import net.amygdalum.testrecorder.profile.SnapshotConsumer;
import net.amygdalum.testrecorder.types.ContextSnapshot;

/**
 * In order to use this class as SnapshotConsumer you have to put the line
 * 
 * 	`com.almondtools.testrecorder.examples.CustomSnapshotConsumer`
 * 
 * into the file
 * 
 * 	agentconfig/net.amygdalum.testrecorder.profile.SnapshotConsumer
 */
public class CustomSnapshotConsumer implements SnapshotConsumer {

	public CustomSnapshotConsumer(AgentConfiguration config) {
	}
	
	@Override
	public void accept(ContextSnapshot snapshot) {
		System.out.println("captured call of " + snapshot.getMethodName());
	}

}
