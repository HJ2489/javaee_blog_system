package com.torch.web.scheduletask;

import com.torch.dao.StatisticMapper;
import com.torch.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: Torch
 * @Date: 2023/06/03 09:47
 * @Description:
 */
@Component
public class ScheduleTask {
    // 需要注入刚才写好的MailUtils以及文章统计表
    @Autowired
    private StatisticMapper statisticMapper;
    @Autowired
    private MailUtils mailUtils;

    @Value("${spring.mail.username}")
    private String mailto;

    /**
     * 每月1号的中午12点发送一次
     */
    @Scheduled(cron = "0 0 12 1 * ?")
    public void sendEmail() {
        // 定制邮件内容
        long totalVisit = statisticMapper.getTotalVisit();
        long totalComment = statisticMapper.getTotalComment();
        // 创建邮件主体
        StringBuffer content = new StringBuffer();
        content.append("博客访问总量为：" + totalVisit + "人次").append("\n");
        content.append("博客评论总数为：" + totalComment + "人次").append("\n");

        mailUtils.sendEmail(mailto, "博客系统流量统计情况", content.toString());

    }
}
