package com.linkedinApp.notification_service.consumer;

import com.linkedinApp.notification_service.clients.ConnectionsClient;
import com.linkedinApp.notification_service.dto.PersonDTO;
import com.linkedinApp.notification_service.entity.Notification;
import com.linkedinApp.notification_service.repository.NotificationRepository;
import com.linkiedinApp.posts_service.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostsServiceConsumer {

    private final ConnectionsClient connectionsClient;
    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "post-created-topic")
    public void handlePostCreated(PostCreatedEvent postCreatedEvent){
        List<PersonDTO> connections = connectionsClient.getFirstConnections(postCreatedEvent.getCreatorId());

        for(PersonDTO connection: connections){
            sendNotification(connection.getUserId(), "Your connection " +
                    postCreatedEvent.getCreatorId() + " has created a post, Check it out");
        }
    }

    public void sendNotification(Long userId, String message){
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);

        notificationRepository.save(notification);
    }

}
