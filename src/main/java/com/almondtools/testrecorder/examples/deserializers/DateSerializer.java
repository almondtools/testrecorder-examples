package com.almondtools.testrecorder.examples.deserializers;

import static java.util.Arrays.asList;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import net.amygdalum.testrecorder.serializers.SerializerFacade;
import net.amygdalum.testrecorder.serializers.SerializerFactory;
import net.amygdalum.testrecorder.types.Serializer;
import net.amygdalum.testrecorder.values.SerializedImmutable;

public class DateSerializer implements Serializer<SerializedImmutable<Date>> {

    public DateSerializer() {
    }

    @Override
    public List<Class<?>> getMatchingClasses() {
        return asList(Date.class);
    }

    @Override
    public SerializedImmutable<Date> generate(Type resultType, Type type) {
        SerializedImmutable<Date> serializedImmutable = new SerializedImmutable<>(type);
        serializedImmutable.setResultType(resultType);
        return serializedImmutable;
    }

    @Override
    public void populate(SerializedImmutable<Date> serializedObject, Object object) {
        Date date = (Date) ((Date) object).clone();
        serializedObject.setValue(date);
    }

    public static class Factory implements SerializerFactory<SerializedImmutable<Date>> {

        @Override
        public DateSerializer newSerializer(SerializerFacade facade) {
            return new DateSerializer();
        }

    }

}
