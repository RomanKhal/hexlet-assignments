package exercise;

import java.lang.reflect.Field;
import java.util.*;

// BEGIN
public class Validator {
    public static void main(String[] args) {
        Address address1 = new Address("Rusa", "", "Lenina", "54", null);
        advancedValidate(address1);
    }

    public static List<String> validate(Address address) {
        List<String> result = new ArrayList<>();
        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(address) == null || field.get(address) == "") {
                        result.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        List<String> isNull = validate(address);
        Field[] fields = address.getClass().getDeclaredFields();
        Map<String, List<String>> result = new HashMap<>();
        for (Field field : fields) {
            String fieldName = field.getName();
            List<String> notValidFields = new ArrayList<>();
            if (field.isAnnotationPresent(MinLength.class)) {
                field.setAccessible(true);
                try {
                    int valueLength = field.get(address).toString().length();
                    int minLength = field.getAnnotation(MinLength.class).minLength();
                    if (valueLength < minLength) {
                        notValidFields.add(String.format("length less than %d", minLength));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            if (isNull.contains(fieldName)) {
                notValidFields.add("can not be null");
            }
            if (!notValidFields.isEmpty()) {
                result.put(fieldName, notValidFields);
            }
        }
        return result;
    }
}
// END
