package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
//DTO - mapowanie pożądanych pól, odcinamy się od transakcji,
// te obiekty które wystawiamy i wysyłamy do zewnętrznych serwisów


@Getter
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String content;
}
