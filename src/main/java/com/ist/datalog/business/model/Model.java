package com.ist.datalog.business.model;
import lombok.*;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Model {
    @Id
    private String id;

    private String name;

    private String time;

    private String directory;

    private String detail;
}
