package com.tekitsolutions.remindme.CardUtils;

import com.tekitsolutions.remindme.R;

import static com.tekitsolutions.remindme.Utils.CommonUtils.AMEX;
import static com.tekitsolutions.remindme.Utils.CommonUtils.DISCOVER;
import static com.tekitsolutions.remindme.Utils.CommonUtils.MASTERCARD;
import static com.tekitsolutions.remindme.Utils.CommonUtils.RUPAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.VISA;

public class CardUtils {

    /*TODO:-> Link: https://stackoverflow.com/questions/28207252/regex-for-determining-credit-cards-in-android
              Link: https://stackoverflow.com/questions/72768/how-do-you-detect-credit-card-type-based-on-number
              Link: https://www.regular-expressions.info/creditcard.html*/

    public static int getCardIcon(String cardNumber) {
        String cardType = getCardType(cardNumber);
        switch (cardType) {
            case VISA:
                return R.drawable.ic_visa_logo;
            case MASTERCARD:
                return R.drawable.ic_master_card_logo;
            case AMEX:
                return R.drawable.ic_amex_logo;
            case DISCOVER:
                return R.drawable.ic_discover_logo;
            case RUPAY:
                return R.drawable.ic_rupay_logo;
            default:
                return R.drawable.ic_payment_icon;
        }
    }

    // Return true if the card number is valid
    public static boolean isValid(long number) {
        return (getSize(number) >= 13 &&

                getSize(number) <= 19) &&
                (prefixMatched(number, 4) ||
                        prefixMatched(number, 5) ||
                        prefixMatched(number, 37) ||
                        prefixMatched(number, 6)) &&
                ((sumOfDoubleEvenPlace(number) +
                        sumOfOddPlace(number)) % 10 == 0);
    }

    // Get the result from Step 2
    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

        return sum;
    }

    // Return this number if it is a single digit, otherwise,
    // return the sum of the two digits
    public static int getDigit(int number) {
        if (number < 9)
            return number;
        return number / 10 + number % 10;
    }

    // Return sum of odd-place digits in number
    public static int sumOfOddPlace(long number) {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");
        return sum;
    }

    // Return true if the digit d is a prefix for number
    public static boolean prefixMatched(long number, int d) {
        return getPrefix(number, getSize(d)) == d;
    }

    //

    // Return the number of digits in d
    public static int getSize(long d) {
        String num = d + "";
        return num.length();
    }

    // Return the first k number of digits from
    // number. If the number of digits in number
    // is less than k, return number.
    public static long getPrefix(long number, int k) {
        if (getSize(number) > k) {
            String num = number + "";
            return Long.parseLong(num.substring(0, k));
        }
        return number;
    }

    public static String getCardType(String credCardNumber) {
        String creditCard = credCardNumber.trim().replace(" ", "");
        //start without knowing the credit card type
        String cardType = "";
        if (creditCard.length() >= 13 && creditCard.length() <= 16
                && creditCard.startsWith("4")) {
            cardType = VISA;
        } else if (creditCard.length() == 16) {
            int prefix = Integer.parseInt(creditCard.substring(0, 2));
            if (prefix >= 51 && prefix <= 55) {
                cardType = MASTERCARD;
            }
        } else if (creditCard.length() == 15
                && (creditCard.startsWith("34") || creditCard
                .startsWith("37"))) {
            cardType = AMEX;
        } else if (creditCard.length() <= 16 || creditCard.length() >= 19 && creditCard.startsWith("6011")) {
            cardType = DISCOVER;
        } else if (creditCard.length() == 16 && creditCard.startsWith("6070")) {
            cardType = RUPAY;
        } else {
            cardType = "Unknown";
        }


        return cardType;
    }

    public static String getSecureNumber(String cardNumber) {
        return "XXXX XXXX XXXX " + cardNumber.substring(cardNumber.length() - 4);
    }
}
