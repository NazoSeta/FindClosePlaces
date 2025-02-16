package com.NazoSeta.FindClosePlaces.dto;

import lombok.Data;

@Data
public class RequestAndAnswerDTO {

    private Long id;
    private Double Longitude;
    private Double Latitude;
    private Double Radius;

}
