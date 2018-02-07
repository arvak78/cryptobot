package com.core;

import com.commons.Exchanges;
import com.core.utils.Telegram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by Manel on 04/02/2018.
 */
@Service
public class ScheduleRemoveOportunities {

    @Autowired
    private Telegram telegram;



    @Autowired
    private FilterResults<Exchanges> filterResults;

    @Scheduled(initialDelay=300000, fixedDelay = 180000)
    public void callRemove() {
        filterResults.removeOldOportunities();
    }
}
