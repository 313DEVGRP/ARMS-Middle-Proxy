package com.arms.api.scheduler.dynamic.controller;

import lombok.extern.slf4j.Slf4j;
import com.arms.api.scheduler.dynamic.dto.SchedulerDTO;
import com.arms.util.cron.CronLocalDateTimes;
import com.arms.util.cron.MadCronExpression;
import com.arms.util.response.CommonResponse.ApiResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.arms.util.response.CommonResponse.success;

@RestController
@Slf4j
public class DynamicScheduleController {

  @PostMapping("/auth-user/schedules/simulate")
  public Mono<ApiResult<List<String>>> cronSimulate(@RequestBody SchedulerDTO schedulerDTO) {
    CronLocalDateTimes cronLocalDateTimes
            = new MadCronExpression(schedulerDTO.getCronExpression()).localDateTimes();
    return Mono.just(success(cronLocalDateTimes.simulateResult()));
  }


}
