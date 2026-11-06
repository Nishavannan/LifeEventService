package com.levis.life_event_service.service;


import com.levis.life_event_service.dto.LifeEventRequest;
import com.levis.life_event_service.dto.SmsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
//the service class - validation, generate voucher, and generate greeting
public class LifeEventProcessor {

    private final KafkaTemplate<String, SmsMessage> kafkaTemplate;

    public void process(LifeEventRequest request)throws Exception{

        //1. incoming Field valiadtion
        if (request.getMobile() == null || request.getMobile().isBlank() ||
                request.getCustomerName() == null || request.getCustomerName().isBlank() ||
                request.getEvent() == null || request.getEvent().isBlank()){
            throw new IllegalArgumentException("Invalid customer details");
        }

        //2.generate voucher

            String voucherId = UUID.randomUUID()
                    .toString()
                    .substring(0, 10)
                    .toUpperCase();
        //3.creating greeting Based on Event

        String greeting;

        switch (request.getEvent().toLowerCase()){

            case "birth":
                greeting = "Happy Birthday " + request.getCustomerName()+"! Enjoy your special discount";
                break;
            case "wedding":
                         greeting =     "Happy Wedding Anniversary " + request.getCustomerName()+"! Enjoy your special discount";
                break;
            default:
                throw new IllegalArgumentException("Unsupported event type");

        }
        //4.me creating outbound message
        SmsMessage smsMessage = new SmsMessage(
                request.getCustomerName(),greeting,voucherId
        );

        //5.outbound kafka topic
        kafkaTemplate.send("customer.comms.sms",request.getMobile(),smsMessage).get();

        }

    }


