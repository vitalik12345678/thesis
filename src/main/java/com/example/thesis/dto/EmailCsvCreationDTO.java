package com.example.thesis.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailCsvCreationDTO {

    @CsvBindByName(column = "email")
    private String email;
    @CsvIgnore
    private String token;
    @CsvBindByName(column = "role")
    private String role;

}
