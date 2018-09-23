package com.almondtools.testrecorder.examples.deserializers;

import static net.amygdalum.testrecorder.deserializers.Templates.assignLocalVariableStatement;
import static net.amygdalum.testrecorder.deserializers.Templates.callMethod;
import static net.amygdalum.testrecorder.deserializers.Templates.callMethodStatement;
import static net.amygdalum.testrecorder.util.Types.mostSpecialOf;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.amygdalum.testrecorder.deserializers.Adaptor;
import net.amygdalum.testrecorder.deserializers.Deserializer;
import net.amygdalum.testrecorder.deserializers.builder.DefaultSetupGenerator;
import net.amygdalum.testrecorder.types.Computation;
import net.amygdalum.testrecorder.types.DeserializerContext;
import net.amygdalum.testrecorder.types.TypeManager;
import net.amygdalum.testrecorder.values.SerializedImmutable;

@SuppressWarnings("rawtypes")
public class DateSetupGenerator extends DefaultSetupGenerator<SerializedImmutable<Date>> implements Adaptor<SerializedImmutable<Date>> {

    @Override
	public Class<SerializedImmutable> getAdaptedClass() {
		return SerializedImmutable.class;
	}

	@Override
	public boolean matches(Type type) {
		return type.equals(Date.class);
	}

	@Override
	public Computation tryDeserialize(SerializedImmutable<Date> value, Deserializer generator) {
		DeserializerContext context = generator.getContext();
        TypeManager types = context.getTypes();
        types.registerTypes(Date.class, Calendar.class);

        Date date = value.getValue();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        
        List<String> statements = new ArrayList<>();
        String calexpression = context.newLocal("cal");
        statements.add(assignLocalVariableStatement(types.getVariableTypeName(Calendar.class), calexpression, callMethod(types.getRawTypeName(Calendar.class), "getInstance")));
        statements.add(callMethodStatement(calexpression, "set", "Calendar.DAY_OF_MONTH", String.valueOf(day)));
        statements.add(callMethodStatement(calexpression, "set", "Calendar.MONTH", String.valueOf(month)));
        statements.add(callMethodStatement(calexpression, "set", "Calendar.YEAR", String.valueOf(year)));
        
        String expression = callMethod(calexpression, "getTime");
        return Computation.expression(expression, mostSpecialOf(value.getUsedTypes()).orElse(Object.class), statements);
	}

}
