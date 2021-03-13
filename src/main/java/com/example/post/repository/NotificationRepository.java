package com.example.post.repository;

import com.example.post.entity.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
    String queryToViewNotification = " select * from notification n where n.question_id in (select q.question_id from question q full join notification n1 on q.question_id = n1.question_id \n" +
            "where q.username = ?1)";

    @Query(value = queryToViewNotification, nativeQuery = true)
    List<Notification> getNotificationByUsername(String username);
    List<Notification> findByQuestionId(Long quid);

    String queryForNotificationCount = "select count(*) from notification n where n.question_id in (select q.question_id from question q full join notification n1 on q.question_id = n1.question_id where q.username = ?1)  AND n.is_read = true";

    @Query(value = queryForNotificationCount, nativeQuery = true)
    Long getNoticiationCount( String username) ;
}
