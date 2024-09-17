package com.api.restfulgym.service;

import com.api.restfulgym.model.Notifications;
import com.api.restfulgym.model.User;
import com.api.restfulgym.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
    NotificationRepository notificationRepository;

    public Notifications create(Notifications notifications) {
        return notificationRepository.save(notifications);
    }

    public String createNotificationMessage(String formattedOldStartTime, String formattedOldEndTime,
                                            String formattedNewStartTime, String formattedNewEndTime, User user) {
        String message = "Привет дорогой " +
                user.getFirstName() + " " +
                user.getLastName() + "!\n" +
                "Твое расписание было изменено c 'начало: " + formattedOldStartTime + " конец: " +
                formattedOldEndTime +
                "' на 'начало: " + formattedNewStartTime + " конец: " + formattedNewEndTime;
        return message;
    }
}
