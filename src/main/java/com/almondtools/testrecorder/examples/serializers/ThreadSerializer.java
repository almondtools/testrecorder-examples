package com.almondtools.testrecorder.examples.serializers;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import net.amygdalum.testrecorder.serializers.AbstractCompositeSerializer;
import net.amygdalum.testrecorder.types.Serializer;
import net.amygdalum.testrecorder.types.SerializerSession;
import net.amygdalum.testrecorder.values.SerializedField;
import net.amygdalum.testrecorder.values.SerializedObject;

public class ThreadSerializer extends AbstractCompositeSerializer implements Serializer<SerializedObject> {

	public ThreadSerializer() {
	}

	@Override
	public List<Class<?>> getMatchingClasses() {
		return asList(Thread.class);
	}

	@Override
	public SerializedObject generate(Class<?> type, SerializerSession session) {
		return new SerializedObject(type);
	}

	@Override
	public Stream<?> components(Object object, SerializerSession session) {
		Builder<Object> builder = Stream.builder();
		Thread thread = (Thread) object;
		Runnable runnable = (Runnable) fieldOf(thread, Thread.class, "target");
		builder.add(runnable);
		return builder.build();
	}

	@Override
	public void populate(SerializedObject serializedObject, Object object, SerializerSession session) {
		Thread thread = (Thread) object;
		SerializedField serializedField = resolvedFieldOf(session, thread, Thread.class, "target");
		serializedObject.addField(serializedField);
	}

}
