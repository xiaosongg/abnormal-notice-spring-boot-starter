package com.ws.an.abnormalnoticehandle.event;

import com.ws.an.abnormalnoticehandle.interfaces.AbnormalNoticeStatisticsRepository;
import com.ws.an.message.INoticeSendComponent;
import com.ws.an.pojos.AbnormalStatistics;
import com.ws.an.pojos.Notice;
import com.ws.an.properties.abnormal.AbnormalNoticeFrequencyStrategy;
import org.springframework.context.ApplicationListener;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 17:28
 * @description 抽象通知发送
 */
public abstract class AbstractNoticeSendListener implements ApplicationListener<AbnormalNoticeEvent> {

    private final AbnormalNoticeFrequencyStrategy abnormalNoticeFrequencyStrategy;

    private final List<INoticeSendComponent<Notice>> noticeSendComponents;

    private final AbnormalNoticeStatisticsRepository abnormalNoticeStatisticsRepository;

    /**
     * @param noticeSendComponents
     */
    public AbstractNoticeSendListener(AbnormalNoticeFrequencyStrategy abnormalNoticeFrequencyStrategy, List<INoticeSendComponent<Notice>> noticeSendComponents, AbnormalNoticeStatisticsRepository abnormalNoticeStatisticsRepository) {

        this.abnormalNoticeFrequencyStrategy = abnormalNoticeFrequencyStrategy;
        this.noticeSendComponents = noticeSendComponents;
        this.abnormalNoticeStatisticsRepository = abnormalNoticeStatisticsRepository;
    }

    public void send(Notice notice) {

        AbnormalStatistics abnormalStatistics = abnormalNoticeStatisticsRepository.increaseOne(notice);

        if (this.stratergyCheck(abnormalStatistics, abnormalNoticeFrequencyStrategy)) {
            notice.setShowCount(abnormalStatistics.getShowCount().longValue());
            notice.setCreateTime(LocalDateTime.now());
            noticeSendComponents.forEach(x -> x.send(notice));
            abnormalNoticeStatisticsRepository.increaseShowOne(abnormalStatistics);
        }


    }

    protected boolean stratergyCheck(AbnormalStatistics abnormalStatistics,
                                     AbnormalNoticeFrequencyStrategy abnormalNoticeFrequencyStrategy) {
        if (abnormalStatistics.isFirstCreated()) {
            abnormalStatistics.setFirstCreated(false);
            return true;
        }
        boolean flag = false;
        switch (abnormalNoticeFrequencyStrategy.getFrequencyType()) {
            case TIMEOUT:
                Duration dur = Duration.between(abnormalStatistics.getNoticeTime(), LocalDateTime.now());
                flag = abnormalNoticeFrequencyStrategy.getNoticeTimeInterval().compareTo(dur) < 0;
            case SHOWCOUNT:
                flag = abnormalStatistics.getShowCount().longValue() - abnormalStatistics.getLastNoticedCount()
                        .longValue() > abnormalNoticeFrequencyStrategy.getNoticeShowCount().longValue();
        }
        return flag;
    }
}
