package com.api.restfulgym.service;

import com.api.restfulgym.dto.ScheduleTimeStampDTO;
import com.api.restfulgym.model.Notifications;
import com.api.restfulgym.model.Schedule;
import com.api.restfulgym.model.User;
import com.api.restfulgym.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final NotificationService notificationService;

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public Schedule updateTimeStamp(Schedule schedule, ScheduleTimeStampDTO scheduleTimestampDTO) {
        schedule.setStartTime(scheduleTimestampDTO.getStartTime());
        schedule.setEndTime(scheduleTimestampDTO.getEndTime());
        return scheduleRepository.save(schedule);
    }

    public Schedule create(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule findById(Integer id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("not found. id: " + id));
    }

    public void deleteById(Integer id) {
        scheduleRepository.deleteById(id);
    }

    public Notifications createNotificationToUserAboutScheduleChange(
            User user, ScheduleTimeStampDTO newScheduleTimeStampDTO, ScheduleTimeStampDTO oldSchedule) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNewStartTime =
                formatter.format(newScheduleTimeStampDTO.getStartTime().atZone(ZoneId.systemDefault()));
        String formattedNewEndTime = formatter.format(newScheduleTimeStampDTO.getEndTime().atZone(ZoneId.systemDefault()));
        String formattedOldStartTime =
                formatter.format(oldSchedule.getStartTime().atZone(ZoneId.systemDefault()));
        String formattedOldEndTime = formatter.format(oldSchedule.getEndTime().atZone(ZoneId.systemDefault()));

        String message = notificationService.createNotificationMessage(
                formattedOldStartTime, formattedOldEndTime,
                formattedNewStartTime, formattedNewEndTime, user
        );
        ZonedDateTime nowInMoscow = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        Notifications notification =
                Notifications.builder()
                        .message(message)
                        .users(user)
                        .createdAt(nowInMoscow)
                        .build();
        return notificationService.create(notification);
    }
}
