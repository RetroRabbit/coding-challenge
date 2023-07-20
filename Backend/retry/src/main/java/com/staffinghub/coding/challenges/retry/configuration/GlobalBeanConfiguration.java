package com.staffinghub.coding.challenges.retry.configuration;

import com.staffinghub.coding.challenges.retry.core.entities.EmailNotificationMessage;
import com.staffinghub.coding.challenges.retry.core.inbound.NotificationHandler;
import com.staffinghub.coding.challenges.retry.core.logic.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.mongodb.store.ConfigurableMongoDbMessageStore;
import org.springframework.integration.mongodb.store.MongoDbChannelMessageStore;
import org.springframework.integration.store.MessageGroupQueue;
import org.springframework.messaging.PollableChannel;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;

@Configuration
@Slf4j
public class GlobalBeanConfiguration {
    private static final String GROUP_ID = "email-group";
    private static final String COLLECTION_NAME = "message-store";
    @Bean
    public NotificationHandler notificationHandler(PollableChannel channel) {
        return new NotificationService(channel);
    }

    @Bean
    public MongoDbChannelMessageStore mongoDbChannelMessageStore(MongoDbFactory MongoDbFactory) {
        return new MongoDbChannelMessageStore(MongoDbFactory, COLLECTION_NAME);
    }

    @Bean
    public ConfigurableMongoDbMessageStore configurableMongoDbMessageStore(MongoDbFactory MongoDbFactory) {
        return new ConfigurableMongoDbMessageStore(MongoDbFactory, COLLECTION_NAME);
    }

    @Bean
    public PollableChannel channel(MongoDbChannelMessageStore messageStore) {
        MessageGroupQueue messageGroupQueue = new MessageGroupQueue(messageStore, GROUP_ID);
        return new QueueChannel(messageGroupQueue);
    }

    @Bean
    public IntegrationFlow integrationFlow(PollableChannel channel, ApplicationEventPublisher events) {
        return IntegrationFlows
                .from(channel)
                .filter(
                        EmailNotificationMessage.class,
                        p -> p.getDueTimestamp().isBefore(LocalDateTime.now()),
                        e -> e.poller(Pollers
                                .fixedRate(7000, 5000)
                                .maxMessagesPerPoll(1)
                                .taskExecutor(Executors.newSingleThreadExecutor())))
                .handle(message -> {
                            EmailNotificationMessage emailNotificationMessage = (EmailNotificationMessage)message.getPayload();
                            events.publishEvent(emailNotificationMessage);
                            log.info("Message pulled: {}", message.getPayload());
                        }
                     )
                .get();
    }
}
