package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Getter
public class TrelloTrelloDto {

    @JsonProperty("board")
    private int board;

    @JsonProperty("card")
    private int card;
}