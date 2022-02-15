package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import javassist.Loader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class TrelloService {

    private final TrelloClient trelloClient;
    private final SimpleEmailService emailService;
    private static final String SUBJECT = "Tasks: New trello card";
    private final AdminConfig adminConfig;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCard createdTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);
        ofNullable(newCard).ifPresent(cards-> emailService.send(
                       new Mail(
                adminConfig.getAdminMail(), //z klasy Mail
                SUBJECT,
                "New card" + trelloCardDto.getName() + "has been created on your trello account",
                null
        )));
return newCard;
    }
}
