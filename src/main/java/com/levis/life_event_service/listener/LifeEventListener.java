package com.levis.life_event_service.listener;


import com.levis.life_event_service.dto.LifeEventRequest;
import com.levis.life_event_service.service.LifeEventProcessor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor


//Listens to kafka topic
@Component
public class LifeEventListener {

    private final LifeEventProcessor lifeEventProcessor;


    @RetryableTopic(
            attempts = "2",
            backOff = @BackOff(delay = 60000),
            retryTopicSuffix = ".retry",
            dltTopicSuffix = ".DLT"
    )
    @KafkaListener(
            topics = "levis.customer.loyality.life-events",
            groupId = "life-event-group"
    )
    public void listen(LifeEventRequest lifeEventRequest) throws Exception {
        System.out.println("Incoming event: " + lifeEventRequest.getEvent());
        lifeEventProcessor.process(lifeEventRequest);
    }
        @DltHandler
                public void handleDlt(LifeEventRequest request){
            System.out.println("Message moved to DLT:" + request.getCustomerName());
        }
    }

