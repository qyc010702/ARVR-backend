package com.ist.datalog.business.model;

import lombok.*;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Check {
    @Id
    private String id;

    private String place;

    private String time;

    private String status;

    private String person;

}
