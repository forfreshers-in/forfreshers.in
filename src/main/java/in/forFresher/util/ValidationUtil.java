package in.forFresher.util;

import java.util.regex.Pattern;

public class ValidationUtil {


    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern email_pattern = Pattern.compile(EMAIL_PATTERN);
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";
    private static final Pattern password_pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return email_pattern.matcher(email).matches();
    }
    
    public static boolean isValidPassword(String password) {
    	 if (password == null) {
             return false;
         }
         return password_pattern.matcher(password).matches();
    }
}
