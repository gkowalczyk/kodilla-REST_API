package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/trello/")
@RequiredArgsConstructor

@CrossOrigin("*")
public class TrelloController { // testowanie implementacji

    //private final TrelloClient trelloClient;
    private final TrelloService trelloService;
    private final TrelloFacade trelloFacade;

    @GetMapping("boards")
    public ResponseEntity<List<TrelloBoardDto>>getTrelloBoards() {
         return ResponseEntity.ok(trelloFacade.fetchTrelloBoards());   //.stream()
      // return ResponseEntity.ok(trelloService.fetchTrelloBoards());   //.stream()
              //  .filter(trelloClient -> trelloClient.getName().equals("Kodilla-application"))
                //.collect(Collectors.toList());

       // trelloBoards.forEach(trelloBoardDto -> {
              //      System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
           // System.out.println("This board contains lists: ");
           // trelloBoardDto.getLists().forEach(trelloList -> {
              //  System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed());
          //  });
       // });
    }
    @PostMapping("cards")
    public ResponseEntity<CreatedTrelloCardDto>  createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return ResponseEntity.ok(trelloFacade.createdTrelloCard(trelloCardDto));
       // return ResponseEntity.ok(trelloService.createdTrelloCard(trelloCardDto));
    }
}

