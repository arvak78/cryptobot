package com.core;

import com.commons.Exchanges;
import com.commons.model.Pair;
import com.commons.utils.ListUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Manel on 03/02/2018.
 */
@Component
public class FilterResults<O> {

    private List<Pair<Exchanges>> exchangesPairRunning;
    private Set<Oportunitat> oportunitatSet;

    /**
     * Add a pair of exchanges running in matchCurrencies
     * with this pair list we avoid repetitions sending notifications
     * for the case CRIPTOPIA-BINANCE and BINANCE-CRIPTOPIA
     * @param obj0
     * @param obj1
     */
    public void addPair(Exchanges obj0, Exchanges obj1) {
        if (exchangesPairRunning == null)
            exchangesPairRunning = new ArrayList<>();

        exchangesPairRunning.add(new Pair<Exchanges>(obj0, obj1));
    }

    public boolean existPair(Exchanges obj0, Exchanges obj1) {
        for (Pair<Exchanges> exchangesPair : ListUtils.nullSafe(exchangesPairRunning)) {
            if ( (exchangesPair.getObject0().equals(obj0) && exchangesPair.getObject1().equals(obj1))  ||
                    exchangesPair.getObject1().equals(obj0) && exchangesPair.getObject0().equals(obj1)) {
                return true;
            }
        }
        return false;
    }

    public void addOportunitat(Oportunitat oportunitat) {
        if (oportunitatSet == null)
            oportunitatSet = new HashSet<>();

        oportunitatSet.add(oportunitat);
    }

    public Oportunitat findOportunitat(Oportunitat oportunitat) {

        if (oportunitatSet == null)
            return null;

        for (Oportunitat op : oportunitatSet) {
            if (op.compareTo(oportunitat) == 0) {
                return op;
            }
        }
        return null;
    }

}
