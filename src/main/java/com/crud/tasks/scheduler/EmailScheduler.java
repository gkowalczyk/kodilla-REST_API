package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.controller.TrelloController;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";
    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;
    private final TrelloClient trelloClient;

    //@Scheduled(fixedDelay = 10000)
    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();

        String message = "";
        Mail mail = new Mail(adminConfig.getAdminMail(), SUBJECT, message, "");

        if (size > 1) {
            mail.setMessage("Currently in database you got: " + size + " tasks");
        } else {
            mail.setMessage("Currently in database you got: " + size + " task");
        }
        simpleEmailService.send(mail);
    }
}

