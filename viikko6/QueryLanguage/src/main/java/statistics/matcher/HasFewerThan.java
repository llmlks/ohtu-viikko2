package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

public class HasFewerThan implements Matcher {

    private final String methodName;
    private final int number;

    public HasFewerThan(int number, String field) {

        this.number = number;
        this.methodName = "get" + Character.toUpperCase(field.charAt(0))
                + field.substring(1);
    }

    @Override
    public boolean matches(Player p) {

        try {

            Method method = p.getClass().getMethod(methodName);
            int valueOfField = (int) method.invoke(p);

            return valueOfField < number;

        } catch (Exception ex) {

            System.out.println(ex);
            throw new IllegalStateException("Player does not have field "
                    + methodName.substring(3).toLowerCase());
        }
    }
}
