package in.forFresher.util;

import java.util.regex.Pattern;

public class ValidationUtil {


    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }
    
}
