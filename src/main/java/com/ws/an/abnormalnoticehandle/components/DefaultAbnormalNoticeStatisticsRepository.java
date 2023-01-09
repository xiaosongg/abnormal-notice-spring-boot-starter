package com.ws.an.abnormalnoticehandle.components;

import com.ws.an.abnormalnoticehandle.interfaces.AbnormalNoticeStatisticsRepository;
import com.ws.an.pojos.AbnormalStatistics;
import com.ws.an.pojos.Notice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/9 9:34
 * @description 默认的统计组件
 */
public class DefaultAbnormalNoticeStatisticsRepository implements AbnormalNoticeStatisticsRepository {

    /**
     * 用线程安全map来统计异常的次数信息等
     */
    private final Map<String, AbnormalStatistics> map = Collections.synchronizedMap(new HashMap<>());

    @Override
    public AbnormalStatistics increaseOne(Notice notice) {

        AbnormalStatistics abnormalStatistics = map.getOrDefault(notice.getUid(), new AbnormalStatistics(notice.getUid()));

        if(abnormalStatistics.isFirstCreated()){
            synchronized (abnormalStatistics){
                // 如果abnormalStatistics在map中为空则将其put到map中，如果abnormalStatistics为空则从map中移除
                map.merge(abnormalStatistics.getUid(), abnormalStatistics, (x, y) -> {
                    if (x == null) {
                        return y;
                    } else {
                        x.setFirstCreated(false);
                        return x;
                    }
                });
            }
        }

        abnormalStatistics.plusOne();
        return abnormalStatistics;
    }

    @Override
    public void increaseShowOne(AbnormalStatistics abnormalStatistics) {
        abnormalStatistics.refreshShow();
    }

    @Override
    public void clear() {
        map.clear();
    }

}
