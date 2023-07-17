package io.github.hello09x.fakeplayer.util;

import org.jetbrains.annotations.Nullable;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionUtils {

    private final static MethodHandles.Lookup LOOKUP = MethodHandles.lookup();

    public static @Nullable Field getFirstFieldByType(Class<?> clazz, Class<?> fieldType, boolean includeStatic) {
        for (var field : clazz.getDeclaredFields()) {
            if (includeStatic ^ Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            if (field.getType() == fieldType) {
                field.setAccessible(true);
                return field;
            }
        }
        return null;
    }

}