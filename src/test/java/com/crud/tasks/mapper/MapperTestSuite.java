package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    private static int testCounter = 0;

    @BeforeAll
    public static void beforeAllTests() {
        System.out.println("This is the beginning of tests.");
    }
    @AfterAll
    public static void afterAllTests() {
        System.out.println("All tests are finished.");
    }
    @BeforeEach
    public void beforeEveryTest() {
        testCounter++;
        System.out.println("Preparing to execute test #" + testCounter);
    }

    @Test
    void mapToBoardTest() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto(
                "ID from trelloboardDto", "trelloboardDto", new ArrayList<>()
        ));
        //When
        List<TrelloBoard> trelloBoardAfterMapping = trelloMapper.mapToBoard(trelloBoardDtoList);
        System.out.println(trelloBoardAfterMapping.get(0).getId());
        System.out.println(trelloBoardDtoList.get(0).getId());
        //Then
        assertEquals(trelloBoardAfterMapping.get(0).getId(), trelloBoardDtoList.get(0).getId());
    }

    @Test
    void mapToBoardDtoTest() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard(
                "ID from trelloboard", "trelloboard", new ArrayList<>()
        ));
        //When
        List<TrelloBoardDto> trelloBoardDtoAfterMapping = trelloMapper.mapToBoardDto(trelloBoardList);
        System.out.println(trelloBoardDtoAfterMapping.get(0).getId());
        System.out.println(trelloBoardList.get(0).getId());
        //Then
        assertEquals(trelloBoardDtoAfterMapping.get(0).getId(), trelloBoardList.get(0).getId());
    }

    @Test
    void mapToListDtoTest() {
        //Given
        List<TrelloList> trelloListList = new ArrayList<>();
        trelloListList.add(new TrelloList("id list", " name list", true));
        //When
        List<TrelloListDto> trelloListDtoAfterMapping = trelloMapper.mapToListDto(trelloListList);
        System.out.println(trelloListDtoAfterMapping.get(0).getName());
        System.out.println(trelloListList.get(0).getName());
        //Then
        assertEquals(trelloListDtoAfterMapping.get(0).getName(), trelloListList.get(0).getName());
    }

    @Test
    void mapToListTest() {
        //Given
        List<TrelloListDto> trelloListListDto = new ArrayList<>();
        trelloListListDto.add(new TrelloListDto("id list", " name list", true));
        //When
        List<TrelloList> trelloListDtoAfterMapping = trelloMapper.maptoList(trelloListListDto);
        System.out.println(trelloListDtoAfterMapping.get(0).getName());
        System.out.println(trelloListListDto.get(0).getName());
        //Then
        assertEquals(trelloListDtoAfterMapping.get(0).getName(), trelloListListDto.get(0).getName());
    }

    @Test
    void mapToCardTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard(" name", "description", "pos", "id");
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        System.out.println(trelloCardDto.getDescription());
        System.out.println(trelloCard.getDescription());
        assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
    }

    @Test
    void mapToCardDtoTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(" name", "description", "pos", "id");
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        System.out.println(trelloCardDto.getListId());
        System.out.println(trelloCard.getListId());
        assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
    }
}
