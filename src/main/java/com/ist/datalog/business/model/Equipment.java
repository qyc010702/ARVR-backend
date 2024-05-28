package com.ist.datalog.business.model;

import lombok.*;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    @Id
    private String id;

    private String name;

    private String model;

    private String status;

    private String inTime;

    private String nextTime;

}
