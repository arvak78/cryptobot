package com.commons.utils;

import java.util.Collection;
import java.util.Collections;

public class ListUtils {

    public static <T> Collection<T> nullSafe(Collection<T> var0) {
        return (Collection)(var0 == null ? Collections.emptyList() : var0);
    }

}
