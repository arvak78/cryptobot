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

//    @Scheduled(cron = "0 0 08,15/12 * *")
    @Scheduled(cron = "0 0 8-21 * * *")
    public void callRemove() {
        telegram.sendMessage("Remove called!!", null);
        filterResults.removeOldOportunities();
    }
}
