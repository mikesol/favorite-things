package uk.ivanc.archimvvm;

/**
 * Created by mikesolomon on 05/10/16.
 */

public class Utils {
    public static String toBraquePath(String... strings) {
        StringBuilder builder = new StringBuilder();
        if (strings == null) {
            return null;
        }
        int i = 0;
        for (String string : strings) {
            if (i > 0) {
                builder.append("/");
            }
            builder.append(string);
            i++;
        }
        return builder.toString();
    }
}
