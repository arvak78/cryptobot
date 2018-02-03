package com.core;

import com.commons.model.BotPrice;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by Manel on 03/02/2018.
 */
@Getter
@Setter
public class MaxMinPrices {
    private BotPrice maxPrice;
    private BotPrice minPrice;
}
