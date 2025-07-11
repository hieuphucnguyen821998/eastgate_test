package com.example.demo.service;

import com.example.demo.dto.EventDto;
import com.example.demo.persistence.entities.Event;
import com.example.demo.persistence.repositories.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    public void saveEvents(String tenantId, List<EventDto> events) {
        List<Event> entities = events.stream()
                .map(dto -> {
                    Event e = new Event();
                    e.setTenantId(tenantId);
                    e.setTimestamp(dto.getTimestamp());
                    e.setEventNumber(dto.getEventNumber());
                    return e;
                })
                .collect(Collectors.toList());

        eventRepository.saveAll(entities);
    }

    @Override
    public List<EventDto> parseCsvFile(MultipartFile file) throws RuntimeException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            return reader.lines()
                    .map(line -> line.split(","))
                    .map(parts -> new EventDto(Long.parseLong(parts[0]), Integer.parseInt(parts[1])))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse CSV: " + e.getMessage(), e);
        }
    }
}
