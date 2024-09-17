package com.api.restfulgym.rest;

import com.api.restfulgym.config.Views;
import com.api.restfulgym.dto.NotificationsDTO;
import com.api.restfulgym.dto.ScheduleDTO;
import com.api.restfulgym.dto.ScheduleTimeStampDTO;
import com.api.restfulgym.model.Notifications;
import com.api.restfulgym.model.Schedule;
import com.api.restfulgym.model.User;
import com.api.restfulgym.service.NotificationService;
import com.api.restfulgym.service.ScheduleService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/schedules")
public class ScheduleController {
    ScheduleService scheduleService;
    ModelMapper modelMapper;

    NotificationService notificationService;
    SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping
    @JsonView(Views.ScheduleView.class)
    public ResponseEntity<List<ScheduleDTO>> findAll() {
        Type listType = new TypeToken<List<ScheduleDTO>>() {
        }.getType();
        return ResponseEntity.ok(modelMapper.map(scheduleService.findAll(), listType));
    }

    @PatchMapping("/update-time-stamp")
    @PreAuthorize("hasRole('ADMIN')")
    @JsonView(Views.ScheduleView.class)
    public ResponseEntity<ScheduleDTO> updateTimeStamp(@RequestParam("schedule_id") Integer scheduleId,
                                                       @RequestBody ScheduleTimeStampDTO newScheduleTimeStampDTO,
                                                       @RequestParam(name = "notify", required = false, defaultValue =
                                                               "false")
                                                       boolean notify) {
        Schedule oldSchedule = scheduleService.findById(scheduleId);
        ScheduleTimeStampDTO oldScheduleTimeStampDTO = ScheduleTimeStampDTO.builder()
                .startTime(oldSchedule.getStartTime())
                .endTime(oldSchedule.getEndTime())
                .build();
        Schedule updatedSchedule = scheduleService.updateTimeStamp(oldSchedule, newScheduleTimeStampDTO);
        if (notify) {
            User user = updatedSchedule.getUser();
            Notifications createdNotification = scheduleService
                    .createNotificationToUserAboutScheduleChange(
                            user, newScheduleTimeStampDTO, oldScheduleTimeStampDTO
                    );
            simpMessagingTemplate.convertAndSendToUser(
                    user.getUsername(), "/specific",
                    modelMapper.map(createdNotification, NotificationsDTO.class)
            );
        }
        return ResponseEntity.ok(modelMapper.map(updatedSchedule, ScheduleDTO.class));
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @JsonView(Views.ScheduleView.class)
    public ResponseEntity<ScheduleDTO> createSchedule(
            @Valid @RequestBody ScheduleDTO schedule) {
        Schedule createdSchedule = scheduleService.create(modelMapper.map(schedule, Schedule.class));
        ScheduleDTO createdScheduleDTO = modelMapper.map(createdSchedule, ScheduleDTO.class);
        return ResponseEntity.ok(createdScheduleDTO);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Integer id) {
        scheduleService.deleteById(id);
    }
}
