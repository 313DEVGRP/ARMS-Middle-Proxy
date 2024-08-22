package com.arms.api.scheduler.dynamic.dto;


import lombok.Getter;
import lombok.Setter;
import com.arms.util.validation.CronCheck;
import com.arms.util.validation.group.ApplyNode;

@Setter
@Getter
public class SchedulerDTO {
  String targetUrl;

  @CronCheck(groups = {ApplyNode.class})
  String cronExpression;
}
