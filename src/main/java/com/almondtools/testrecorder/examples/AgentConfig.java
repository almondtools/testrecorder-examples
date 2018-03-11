package com.almondtools.testrecorder.examples;

import static java.util.Arrays.asList;

import java.util.List;

import net.amygdalum.testrecorder.DefaultSerializationProfile;
import net.amygdalum.testrecorder.profile.Classes;

public class AgentConfig extends DefaultSerializationProfile {

	@Override
	public List<Classes> getClasses() {
		return asList(Classes.byPackage("com.almondtools.testrecorder.examples"));
	}

}
