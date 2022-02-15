package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Mail {

    private String mail;
    private String subject;
    private String message;
    private String toCc;
}


