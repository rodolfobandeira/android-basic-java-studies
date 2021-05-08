package com.rodolfobandeira.travelapp.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtil {

    public static final String ENGLISH = "en";
    public static final String CANADA = "ca";
    public static final String DEFAULT_FORMAT = "$";
    public static final String DEFAULT_SPACED_FORMAT = "CA$ ";

    public static String formatToCanada(BigDecimal valor) {
        NumberFormat canadianFormat = DecimalFormat.getCurrencyInstance(
                new Locale(ENGLISH, CANADA));
        return canadianFormat
                .format(valor)
                .replace(DEFAULT_FORMAT, DEFAULT_SPACED_FORMAT);
    }

}
