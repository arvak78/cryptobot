package com.commons.utils;

import javax.validation.constraints.NotNull;

/**
 * Created by manel on 22/01/18.
 */
public class StringUtils {

    public static double StringToDouble(@NotNull String value) {
        return value != null && value.length() > 0 ? Double.parseDouble(value) : 0;
    }

}
