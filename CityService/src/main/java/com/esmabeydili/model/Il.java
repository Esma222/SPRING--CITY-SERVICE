package com.esmabeydili.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data//getter//setter
@NoArgsConstructor//parametresiz constructor
@AllArgsConstructor//tüm parametreleri alan constructor
public class Il {

    private Date createDate= new Date();

    @Id
    private String id;

    private String name;


}
