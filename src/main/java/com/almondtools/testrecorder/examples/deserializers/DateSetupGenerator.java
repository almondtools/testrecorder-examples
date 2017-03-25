package com.almondtools.testrecorder.examples.deserializers;

import static net.amygdalum.testrecorder.deserializers.Templates.assignLocalVariableStatement;
import static net.amygdalum.testrecorder.deserializers.Templates.callMethod;
import static net.amygdalum.testrecorder.deserializers.Templates.callMethodStatement;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.amygdalum.testrecorder.DeserializationException;
import net.amygdalum.testrecorder.deserializers.Adaptor;
import net.amygdalum.testrecorder.deserializers.Computation;
import net.amygdalum.testrecorder.deserializers.TypeManager;
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
		return type.equals(Date.class);
	}

	@Override
	public Computation tryDeserialize(SerializedImmutable<Date> value, SetupGenerators generator) throws DeserializationException {
        TypeManager types = generator.getTypes();
        types.registerTypes(Date.class, Calendar.class);

        Date date = value.getValue();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        
        List<String> statements = new ArrayList<>();
        String calexpression = "cal";
        statements.add(assignLocalVariableStatement(types.getBestName(Calendar.class), calexpression, callMethod(types.getBestName(Calendar.class), "getInstance")));
        statements.add(callMethodStatement(calexpression, "set", "Calendar.DAY_OF_MONTH", String.valueOf(day)));
        statements.add(callMethodStatement(calexpression, "set", "Calendar.MONTH", String.valueOf(month)));
        statements.add(callMethodStatement(calexpression, "set", "Calendar.YEAR", String.valueOf(year)));
        
        String expression = callMethod(calexpression, "getTime");
        return new Computation(expression, value.getResultType(), statements);
	}

}
