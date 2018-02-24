package com.almondtools.testrecorder.examples.deserializers;

import static java.lang.String.valueOf;
import static net.amygdalum.testrecorder.deserializers.Templates.callMethod;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

import org.hamcrest.Matcher;

import net.amygdalum.testrecorder.deserializers.Adaptor;
import net.amygdalum.testrecorder.deserializers.Computation;
import net.amygdalum.testrecorder.deserializers.TypeManager;
import net.amygdalum.testrecorder.deserializers.matcher.DefaultMatcherGenerator;
import net.amygdalum.testrecorder.deserializers.matcher.MatcherGenerators;
import net.amygdalum.testrecorder.types.DeserializationException;
import net.amygdalum.testrecorder.types.DeserializerContext;
import net.amygdalum.testrecorder.values.SerializedImmutable;

@SuppressWarnings("rawtypes")
public class DateMatcherGenerator extends DefaultMatcherGenerator<SerializedImmutable<Date>> implements Adaptor<SerializedImmutable<Date>, MatcherGenerators> {

    @Override
    public Class<SerializedImmutable> getAdaptedClass() {
        return SerializedImmutable.class;
    }

    @Override
    public boolean matches(Type type) {
        return type.equals(Date.class);
    }

    @Override
    public Computation tryDeserialize(SerializedImmutable<Date> value, MatcherGenerators generator, DeserializerContext context) throws DeserializationException {
        Date date = value.getValue();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        TypeManager types = context.getTypes();
        types.registerType(DateMatcher.class);
        
        String expression = callMethod(types.getRawTypeName(DateMatcher.class), "matchesDate");
        expression = callMethod(expression, "withDay", valueOf(cal.get(Calendar.DAY_OF_MONTH)));
        expression = callMethod(expression, "withMonth", valueOf(cal.get(Calendar.MONTH)));
        expression = callMethod(expression, "withYear", valueOf(cal.get(Calendar.YEAR)));
        
        return Computation.expression(expression, Matcher.class);
    }
}
