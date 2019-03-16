package com.walterwhites.library.batch_notification.configuration;

import com.walterwhites.library.batch_notification.apiClient.NotificationClient;
import com.walterwhites.library.batch_notification.processor.NotificationItemProcessor;
import library.io.github.walterwhites.loans.Notification;
import library.io.github.walterwhites.loans.UpdateAllNotificationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableBatchProcessing
@Configuration
@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class})
@EnableJpaRepositories("com.walterwhites.library.consumer.repository.entity")
@EntityScan("com.walterwhites.library.model.entity")
@ComponentScan("com.walterwhites.library")
public class BatchConfiguration {

    private static final Logger log = LoggerFactory.getLogger(NotificationItemProcessor.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public NotificationClient notificationClient;

    @Bean
    @StepScope
    public NotificationItemProcessor notificationProcessor() {
        return new NotificationItemProcessor();
    }

    @Bean
    public Job importNotificationJob(JobCompletionNotificationListener listener, Step stepNotification) {
        return jobBuilderFactory.get("importNotificationJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(stepNotification())
                .end()
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<Notification> notificationReader(){
        UpdateAllNotificationResponse updateAllNotificationResponse = notificationClient.updateOutdatedReservations();
        ItemReader<Notification> itemReader = new ListItemReader<Notification>(updateAllNotificationResponse.getNotification());
        return itemReader;
    }

    @Bean
    public Step stepNotification() {
        return stepBuilderFactory.get("stepNotification")
                .<Notification, Notification> chunk(10)
                .reader(notificationReader())
                .processor(notificationProcessor())
                .writer(new ItemWriter<Notification>() {
                    @Override
                    public void write(List<? extends Notification> items) throws Exception {
                    }
                })
                .build();
    }
}