package com.almondtools.testrecorder.manual;

import org.hamcrest.Matcher;

import net.amygdalum.testrecorder.ConfigurableSerializerFacade;
import net.amygdalum.testrecorder.codeserializer.CodeSerializer;
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
		CodeSerializer codeSerializer = new CodeSerializer("", ConfigurableSerializerFacade::new, MatcherGenerators::new);
		codeSerializer.getTypes().registerTypes(Matcher.class);
		String code = codeSerializer.serialize(exampleObject);
		System.out.println(code);
	}

}
