package com.levis.life_event_service.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

//dto used to hold outgoing SmS message
public class SmsMessage {

    private String CustomerName;
    private String message;
    private String voucherId;

}
