package com.almondtools.testrecorder.examples.serializers;

import static java.util.Arrays.asList;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

import net.amygdalum.testrecorder.serializers.SerializerFacade;
import net.amygdalum.testrecorder.types.SerializedReferenceType;
import net.amygdalum.testrecorder.types.SerializedValue;
import net.amygdalum.testrecorder.types.Serializer;
import net.amygdalum.testrecorder.util.Reflections;
import net.amygdalum.testrecorder.values.SerializedField;
import net.amygdalum.testrecorder.values.SerializedObject;

public class ThreadSerializer implements Serializer<SerializedObject> {

	private SerializerFacade facade;

	public ThreadSerializer(SerializerFacade facade) {
		this.facade = facade;
	}

	@Override
	public List<Class<?>> getMatchingClasses() {
		return asList(Thread.class);
	}

	@Override
	public SerializedObject generate(Type type) {
		return new SerializedObject(type);
	}

	@Override
	public void populate(SerializedObject serializedObject, Object object) {
		try {
			Thread thread = (Thread) object;
			Field field = Thread.class.getDeclaredField("target");
			Reflections.accessing(field).exec(f -> {
				Runnable runnable = (Runnable) f.get(thread);
				SerializedValue serializedRunnable = facade.serialize(runnable.getClass(), runnable);
				if (serializedRunnable instanceof SerializedReferenceType) {
					((SerializedReferenceType) serializedRunnable).useAs(Runnable.class);
				}
				serializedObject.addField(new SerializedField(Thread.class, "target", Runnable.class, serializedRunnable));
			});
		} catch (ReflectiveOperationException | SecurityException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
