package com.example.demo.service;

import com.example.demo.dto.EventDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EventService {
    void saveEvents(String tenantId, List<EventDto> events);

    List<EventDto> parseCsv(MultipartFile file) throws IOException;
}
