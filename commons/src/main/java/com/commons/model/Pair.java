package com.commons.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Manel on 03/02/2018.
 */
@Setter
@Getter
public class Pair<O> {

    public Pair(O object0, O object1) {
        this.object0 = object0;
        this.object1 = object1;
    }

    private O object0;
    private O object1;
}
