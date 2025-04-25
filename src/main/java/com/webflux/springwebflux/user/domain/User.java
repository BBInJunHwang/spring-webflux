package com.webflux.springwebflux.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@AllArgsConstructor
@Table("users")
public class User {
    @Id
    private String id;
    private String name;
}
