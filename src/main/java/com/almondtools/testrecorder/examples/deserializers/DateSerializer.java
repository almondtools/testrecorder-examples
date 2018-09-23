package com.almondtools.testrecorder.examples.deserializers;

import static java.util.Arrays.asList;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import net.amygdalum.testrecorder.types.Serializer;
import net.amygdalum.testrecorder.types.SerializerSession;
import net.amygdalum.testrecorder.values.SerializedImmutable;

public class DateSerializer implements Serializer<SerializedImmutable<Date>> {

	public DateSerializer() {
    }

    @Override
    public List<Class<?>> getMatchingClasses() {
        return asList(Date.class);
    }
    
    @Override
    public Stream<?> components(Object object, SerializerSession session) {
    	return Stream.empty();
    }

    @Override
    public SerializedImmutable<Date> generate(Class<?> type, SerializerSession session) {
        return new SerializedImmutable<>(type);
    }

    @Override
    public void populate(SerializedImmutable<Date> serializedObject, Object object, SerializerSession session) {
        Date date = (Date) ((Date) object).clone();
        serializedObject.setValue(date);
    }

}
