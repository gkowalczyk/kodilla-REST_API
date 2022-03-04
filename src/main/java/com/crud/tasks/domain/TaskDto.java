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
//DTO są to obiekty transferujące, które odpowiadają za
// przekazywanie danych pomiędzy serwerami. Obiekt DTO
// jest miejscem, w którym gromadzimy wejściowe dane do
// aplikacji (trafiające do Controllera), bądź dane,
// które opuszczają naszą aplikację (są zwracane przez
// Controller).