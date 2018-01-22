package com.commons.annotations;

import com.commons.Exchanges;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by manel on 21/01/18.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Exchange {
    Exchanges name();
}
