package com.almondtools.testrecorder.examples.serializers;

import static java.util.Arrays.asList;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

import net.amygdalum.testrecorder.SerializedValue;
import net.amygdalum.testrecorder.Serializer;
import net.amygdalum.testrecorder.SerializerFacade;
import net.amygdalum.testrecorder.SerializerFactory;
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
	public SerializedObject generate(Type resultType, Type type) {
	    return new SerializedObject(type).withResult(resultType);
	}

	@Override
	public void populate(SerializedObject serializedObject, Object object) {
		try {
	        Thread thread = (Thread) object;
            Field field = Thread.class.getDeclaredField("target");
            Reflections.accessing(field).exec(()-> {
                Runnable runnable = (Runnable) field.get(thread);
                SerializedValue serializedRunnable = facade.serialize(runnable.getClass(), runnable);
                serializedObject.addField(new SerializedField(Thread.class, "target", Runnable.class, serializedRunnable));
            });
        } catch (ReflectiveOperationException | SecurityException e) {
            throw new RuntimeException(e.getMessage());
        }
	}

	public static class Factory implements SerializerFactory<SerializedObject> {

		@Override
		public ThreadSerializer newSerializer(SerializerFacade facade) {
			return new ThreadSerializer(facade);
		}

	}

}
