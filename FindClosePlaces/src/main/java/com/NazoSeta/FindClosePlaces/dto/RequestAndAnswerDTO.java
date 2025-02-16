package com.NazoSeta.FindClosePlaces.dto;

import lombok.Data;

@Data
public class RequestAndAnswerDTO {

    private Long id;
    private String longitude;
    private String latitude;
    private String radius;

}
