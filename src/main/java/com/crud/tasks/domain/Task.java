package com.crud.tasks.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity(name = "tasks")

public class Task {
    @Id
    @GeneratedValue//wartość generowana autimatycznie
    private Long id;
    @Column(name = "name")
    private String title;
    @Column(name = "content")
    private String content;
}