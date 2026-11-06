package com.levis.life_event_service.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter



//This Dto to hold the Incoming Kafka message
public class LifeEventRequest {

    private String mobile;
    private String event;
    private String customerName;
}
