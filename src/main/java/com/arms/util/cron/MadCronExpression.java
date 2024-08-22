package com.arms.util.cron;

import org.springframework.scheduling.support.CronExpression;

public class MadCronExpression {
    private final String cronExpression;
    public MadCronExpression(String cronExpression){
        if(!CronExpression.isValidExpression(cronExpression)){
            throw new IllegalArgumentException("유효하지 않은 표현식 입니다.");
        }
        this.cronExpression = cronExpression;
    }

    public boolean isLessThan600seconds(){
        return localDateTimes().hasCloseTimeDifferenceInSeconds(600);
    }

    public CronLocalDateTimes localDateTimes(){
        return new CronLocalDateTimes(cronExpression);
    }

}
