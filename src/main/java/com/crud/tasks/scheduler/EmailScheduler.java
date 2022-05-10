package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailServiceWithShedule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";
    //private final SimpleEmailService simpleEmailService;
    @Autowired
    private SimpleEmailServiceWithShedule simpleEmailServiceWithShedule;
    @Autowired
    private final TaskRepository taskRepository;
    @Autowired
    private final AdminConfig adminConfig;

    //@Scheduled(fixedDelay = 10000)
    //@Scheduled(cron = "0 0 23 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();

        String message = " message" + ((size > 1L) ? "tasks" : "task");
        Mail mail = new Mail(adminConfig.getAdminMail(), SUBJECT, message, "");
        simpleEmailServiceWithShedule.send(mail);
    }
}

