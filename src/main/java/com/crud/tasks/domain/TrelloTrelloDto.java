package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)


@AllArgsConstructor
@Getter
public class TrelloTrelloDto {

    @JsonProperty("board")
    private int board;

    @JsonProperty("card")
    private int card;
}