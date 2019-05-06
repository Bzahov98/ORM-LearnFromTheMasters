package com.bzahov.OnlineFilmSystem.Utils;

import java.math.BigDecimal;

public class BigDecimalUtils {
    public static boolean isAmountInvalid(BigDecimal amount, String where) {
        if (amount == null) {
            System.err.println("\n>>Error in " + where );
            return true;
        } else if (amount.compareTo(BigDecimal.ZERO) < 0) {
            System.err.println("\n>>Error in " + where + " - less than ZERO");
            return true;
        }
        return false;
    }
}