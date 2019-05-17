package com.tekitsolutions.remindme.Utils;

import android.util.Patterns;

import java.util.regex.Pattern;

public class ValidationUtils {

    public static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

    public static final String PHONE_VALIDATION_REGEX = "[a-zA-Z]+";

    public static final String USERNAME_REGEX = "^[a-z0-9_-]{3,15}$";

    public static final String IFSC_CODE_REGEX = "^[A-Za-z]{4}0[A-Z0-9a-z]{6}$";

    public static Pattern pattern;

    public static boolean validateEmail(String emailID) {

        pattern = Pattern.compile(ValidationUtils.EMAIL_VALIDATION_REGEX);
        return pattern.matcher(emailID).matches();
    }

    public static boolean validateMobile(String phone) {
        if (!Pattern.matches(ValidationUtils.PHONE_VALIDATION_REGEX, phone)) {
            // if(phone.length() != 10) {
            return phone.length() >= 10 && phone.length() <= 13;
        } else {
            return false;
        }
    }

    public static boolean validateFax(String fax) {
        if (!Pattern.matches(ValidationUtils.PHONE_VALIDATION_REGEX, fax)) {
            // if(phone.length() != 10) {
            return fax.length() >= 7 && fax.length() <= 13;
        } else {
            return false;
        }
    }

    public static boolean validateUrl(String url) {
        pattern = Patterns.WEB_URL;
        return pattern.matcher(url.toLowerCase()).matches();
    }

    public static boolean validateUserName(String skypeId) {
        pattern = Pattern.compile(ValidationUtils.USERNAME_REGEX);
        return pattern.matcher(skypeId).matches();
    }

    public static boolean validateIFSC(String ifscCode) {

        pattern = Pattern.compile(ValidationUtils.IFSC_CODE_REGEX);
        return pattern.matcher(ifscCode).matches();
    }
}