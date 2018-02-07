package com.core;

import com.commons.Exchanges;
import com.commons.constants.StringConstants;
import com.commons.model.Pair;
import com.commons.utils.ListUtils;
import com.core.utils.Telegram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Created by Manel on 03/02/2018.
 */
@Component
public class FilterResults<O> {

    @Autowired
    private Telegram telegram;

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

    public void removeOldOportunities() {
        ZonedDateTime now = ZonedDateTime.now();
        boolean flag = false;

        if (oportunitatSet != null && oportunitatSet.size() > 0) {

            Iterator<Oportunitat> iterator = oportunitatSet.iterator();

            StringBuilder sb = new StringBuilder();
            sb.append("Oportunitats Eliminades").append(StringConstants.NEW_LINE)
                    .append("-------------------------------------").append(StringConstants.NEW_LINE);

            while (iterator.hasNext()) {
                Oportunitat oportunitat = iterator.next();
                long between = ChronoUnit.MINUTES.between(oportunitat.getLastPickOutInstant(), now);
                if (between >= 1) {
                    flag = true;
                    long exposedTime = ChronoUnit.MINUTES.between(oportunitat.getExposedInstant(), oportunitat.getLastPickOutInstant());

                    sb.append(oportunitat.getOriginExchange()).append("-")
                            .append(oportunitat.getDestinyExchange()).append(StringConstants.NEW_LINE)
//                            .append(oportunitat.getOriginPrice().getSymbol()).append("-")
                            .append(oportunitat.getDestinyPrice().getSymbol()).append(StringConstants.NEW_LINE)
                            .append("minAskPrice: ").append(oportunitat.getOriginPrice().getAskPrice()).append("-").append(StringConstants.NEW_LINE)
                            .append("maxBidPrice: ").append(oportunitat.getDestinyPrice().getBidPrice()).append(StringConstants.NEW_LINE)
                            .append("Max profit: ").append(oportunitat.getProfitList().get(oportunitat.getProfitList().size()-1)).append(StringConstants.NEW_LINE)
                            .append("Durant ").append(exposedTime).append(" minuts").append(StringConstants.NEW_LINE);

                    iterator.remove();
                }
            }
            if (flag == true) {
                telegram.sendMessage(sb.toString(), null);
            }
        }

    }

    public void removePairs() {
        exchangesPairRunning = new ArrayList<>();
    }
}
