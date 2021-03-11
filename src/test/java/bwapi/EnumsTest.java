package bwapi;

import org.junit.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EnumsTest {
    static String CLASSES_LOCATION = "src/main/java/bwapi";
    static String ARRAY_VALUE = "idToEnum";
    static String ID_VALUE = "id";


    static List<Class<?>> getBWAPIEnums() throws ClassNotFoundException {
        List<Class<?>> enums = new ArrayList<>();
        for (File file : Objects.requireNonNull(new File(CLASSES_LOCATION).listFiles())) {
            if (file.isFile()) {
                Class<?> cls = Class.forName("bwapi." + file.getName().replace(".java", ""));
                if (cls.isEnum()) {
                    enums.add(cls);
                }
            }
        }
        return enums;
    }
    /*
     * Check, via reflection, that all Enums that have an "idToEnum" array to be of correct lenght
     */
    @Test
    public void checkAllidToEnumsArrayLengths() throws Exception {
        for (final Class<?> cls : getBWAPIEnums()) {
            if (Arrays.stream(cls.getDeclaredFields()).anyMatch(f -> f.getName().equals(ARRAY_VALUE))) {
                Field field = cls.getDeclaredField(ARRAY_VALUE);
                assertFalse(field.isAccessible());
                field.setAccessible(true);

                final int arrayLength = ((Object[])field.get(null)).length;
                int maxEnumVal = Integer.MIN_VALUE;
                for (Object obj : (Object[]) cls.getDeclaredMethod("values").invoke(null)) {
                    final Field f = obj.getClass().getDeclaredField(ID_VALUE);
                    assertFalse(f.isAccessible());
                    f.setAccessible(true);
                    final int val = f.getInt(obj);
                    if (val > maxEnumVal) {
                        maxEnumVal = val;
                    }
                }
                assertEquals(arrayLength, maxEnumVal + 1);
            }
        }
    }

    @Test
    public void ensureSimpleGettersReturnNonNullAndDontFail() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        for (final Class<?> cls : getBWAPIEnums()) {
            final List<Method> simpleGetters = Arrays.stream(cls.getMethods())
                    .filter(it -> it.getParameterCount() == 0 && it.getReturnType() != Void.TYPE)
                    .collect(Collectors.toList());
            for (final Object type : cls.getEnumConstants()) {
                for (final Method getter : simpleGetters) {
                    // WHEN
                    final Object result = getter.invoke(type);

                    // THEN
                    assertThat(result).describedAs("When calling " + getter.getName()).isNotNull();
                }
            }
        }
    }

    @Test
    public void checkAllEnumsHaveGetID() throws Exception {
        for (final Class<?> cls : getBWAPIEnums()) {
            final List<Method> getID = Arrays.stream(cls.getMethods())
                    .filter(it -> it.getParameterCount() == 0
                            && it.getReturnType() == Integer.TYPE
                            && it.getName().equals("getID")
                            )
                    .collect(Collectors.toList());
            assertThat(getID).size().isEqualTo(1);
        }
    }
}
