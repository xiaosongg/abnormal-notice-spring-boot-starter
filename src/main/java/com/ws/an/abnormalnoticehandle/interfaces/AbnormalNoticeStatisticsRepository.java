package com.ws.an.abnormalnoticehandle.interfaces;

import com.ws.an.pojos.AbnormalStatistics;
import com.ws.an.pojos.Notice;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/9 9:29
 * @description
 */
public interface AbnormalNoticeStatisticsRepository {

    public AbnormalStatistics increaseOne(Notice notice);

    public void increaseShowOne(AbnormalStatistics abnormalStatistics);

    public void clear();

}
