package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TrelloBudgetDto {

    @JsonProperty("votes")
    private int votes;

    @JsonProperty("attachmentByType")
    private TrelloAttachmentsByTypeDto attachmentByTypeDto;
}
