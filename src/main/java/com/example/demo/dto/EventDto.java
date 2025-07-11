package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventDto {
    private Long timestamp;
    private Integer eventNumber;

    public EventDto(Long timestamp, Integer eventNumber) {
        this.timestamp = timestamp;
        this.eventNumber = eventNumber;
    }
}