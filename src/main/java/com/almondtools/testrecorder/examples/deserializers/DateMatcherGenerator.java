package com.almondtools.testrecorder.examples.deserializers;

import java.lang.reflect.Type;
import java.util.Date;

import net.amygdalum.testrecorder.DeserializationException;
import net.amygdalum.testrecorder.deserializers.Adaptor;
import net.amygdalum.testrecorder.deserializers.Computation;
import net.amygdalum.testrecorder.deserializers.matcher.DefaultMatcherGenerator;
import net.amygdalum.testrecorder.deserializers.matcher.MatcherGenerators;
import net.amygdalum.testrecorder.values.SerializedImmutable;

@SuppressWarnings("rawtypes")
public class DateMatcherGenerator extends DefaultMatcherGenerator<SerializedImmutable<Date>> implements Adaptor<SerializedImmutable<Date>, MatcherGenerators> {

    @Override
    public Class<SerializedImmutable> getAdaptedClass() {
        return SerializedImmutable.class;
    }

    @Override
    public boolean matches(Type type) {
        return true;
    }

    @Override
    public Computation tryDeserialize(SerializedImmutable<Date> value, MatcherGenerators generator) throws DeserializationException {
        // TODO Auto-generated method stub
        return null;
    }
}
