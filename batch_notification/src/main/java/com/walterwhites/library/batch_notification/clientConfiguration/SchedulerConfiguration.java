package com.walterwhites.library.batch_notification.clientConfiguration;

import com.walterwhites.library.batch_notification.configuration.JobCompletionNotificationListener;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.batch.core.Step;

import java.util.Date;

@Component
@AllArgsConstructor
public class SchedulerConfiguration {
    private final JobLauncher jobLauncher;
    private final Job importNotificationJob;

    @Autowired
    private JobCompletionNotificationListener listener;

    @Autowired
    private Step stepNotification;

    @Scheduled(cron = "${batch.cron}")
    public void schedule() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        jobLauncher.run(importNotificationJob, new JobParametersBuilder()
                .addDate("date", new Date())
                .toJobParameters());
    }
}