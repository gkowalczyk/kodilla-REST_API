package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@AllArgsConstructor
public class Mail {

    private final String mail;
    private final String subject;
    private final String message;
    private final String toCc;
}


