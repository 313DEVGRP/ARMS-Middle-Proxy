package com.arms.util.cron;

import org.springframework.scheduling.support.CronExpression;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CronLocalDateTimes {
    private final List<LocalDateTime> localDateTimes;
    private static final int LIMIT_COUNT = 10;
    public CronLocalDateTimes(String cronExpression) {
        CronExpression expression = CronExpression.parse(cronExpression);
        localDateTimes = Stream.iterate(LocalDateTime.now(), expression::next)
            .skip(1)
            .limit(LIMIT_COUNT)
            .collect(Collectors.toList());
        if(localDateTimes.isEmpty()){
            throw new IllegalArgumentException("값이 존재 하지 않습니다.");
        }
        if(localDateTimes.size()<2){
            throw new IllegalArgumentException("비교 대상이 너무 적습니다.");
        }
    }
    public boolean hasCloseTimeDifferenceInSeconds(int seconds){
        return IntStream.range(0, localDateTimes.size() - 1)
                .anyMatch(i -> Duration.between(localDateTimes.get(i), localDateTimes.get(i + 1)).toSeconds() < seconds);
    }

    public List<String> simulateResult() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTimes.stream().map(dateTimeFormatter::format).collect(Collectors.toList());
    }
}
