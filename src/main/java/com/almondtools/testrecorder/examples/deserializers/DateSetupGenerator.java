package com.almondtools.testrecorder.examples.deserializers;

import java.lang.reflect.Type;
import java.util.Date;

import net.amygdalum.testrecorder.DeserializationException;
import net.amygdalum.testrecorder.deserializers.Adaptor;
import net.amygdalum.testrecorder.deserializers.Computation;
import net.amygdalum.testrecorder.deserializers.builder.DefaultSetupGenerator;
import net.amygdalum.testrecorder.deserializers.builder.SetupGenerators;
import net.amygdalum.testrecorder.values.SerializedImmutable;

@SuppressWarnings("rawtypes")
public class DateSetupGenerator extends DefaultSetupGenerator<SerializedImmutable<Date>> implements Adaptor<SerializedImmutable<Date>, SetupGenerators> {

    @Override
	public Class<SerializedImmutable> getAdaptedClass() {
		return SerializedImmutable.class;
	}

	@Override
	public boolean matches(Type type) {
		return true;
	}

	@Override
	public Computation tryDeserialize(SerializedImmutable<Date> value, SetupGenerators generator) throws DeserializationException {
        // TODO Auto-generated method stub
        return null;
	}

}
