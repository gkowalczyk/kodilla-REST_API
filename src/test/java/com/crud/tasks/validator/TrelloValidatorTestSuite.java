package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloValidatorTestSuite {

    @Autowired
    TrelloValidator trelloValidator;

    @Test
    void validateTrelloBoards() {

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("id", "name", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("id", "test", new ArrayList<>()));

        List<TrelloBoard> trelloBoardList = trelloValidator.validateTrelloBoards(trelloBoards);

        assertEquals(trelloBoardList.size(), 1);
        assertEquals(trelloBoardList.get(0).getName(), "name");
    }
}

