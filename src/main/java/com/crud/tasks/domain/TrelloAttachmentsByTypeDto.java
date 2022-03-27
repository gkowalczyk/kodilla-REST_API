package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor

public class TrelloAttachmentsByTypeDto {

    @JsonProperty("trello")
    private TrelloTrelloDto trello;
}
