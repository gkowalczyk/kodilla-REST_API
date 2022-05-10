package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor

public class TrelloService {

    private final TrelloClient trelloClient;
    //private final SimpleEmailService emailService;
    private final SimpleEmailServiceWithShedule simpleEmailServiceWithShedule;
    private static final String SUBJECT = "Tasks: New trello card";
    private final AdminConfig adminConfig;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createdTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        ofNullable(newCard).ifPresent(cards-> simpleEmailServiceWithShedule.send(
                       new Mail(
                adminConfig.getAdminMail(), //z klasy Mail
                SUBJECT,
                "New card" + trelloCardDto.getName() + "has been created on your trello account",
                null
        )));
return newCard;
    }
}
