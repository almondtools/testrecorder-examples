package com.almondtools.testrecorder.manual;

import org.hamcrest.Matcher;

import net.amygdalum.testrecorder.CodeSerializer;
import net.amygdalum.testrecorder.ConfigurableSerializerFacade;
import net.amygdalum.testrecorder.DefaultTestRecorderAgentConfig;
import net.amygdalum.testrecorder.SerializationProfile;
import net.amygdalum.testrecorder.SerializerFacade;
import net.amygdalum.testrecorder.deserializers.DeserializerFactory;
import net.amygdalum.testrecorder.deserializers.matcher.MatcherGenerators;

public class ExampleMain {

	public static void main(String[] args) {
		ExampleObject exampleObject = new ExampleObject();
		exampleObject.setName("Testrecorder");

		printObjectCode(exampleObject);
		printMatcherCode(exampleObject);
	}

	private static void printObjectCode(ExampleObject exampleObject) {
		CodeSerializer codeSerializer = new CodeSerializer();
		String code = codeSerializer.serialize(exampleObject);
		System.out.println(code);
	}

	private static void printMatcherCode(ExampleObject exampleObject) {
		SerializationProfile profile = new DefaultTestRecorderAgentConfig();
		SerializerFacade facade = new ConfigurableSerializerFacade(profile);
		DeserializerFactory factory = new MatcherGenerators.Factory();

		CodeSerializer codeSerializer = new CodeSerializer("", facade, factory);
		codeSerializer.getTypes().registerTypes(Matcher.class, ExampleObject.class);
		String code = codeSerializer.serialize(exampleObject);
		System.out.println(code);
	}

}
