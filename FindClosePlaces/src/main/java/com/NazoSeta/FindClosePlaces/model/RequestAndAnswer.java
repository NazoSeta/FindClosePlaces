package com.NazoSeta.FindClosePlaces.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RequestAndAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String longitude;
    private String latitude;
    private String radius;
    @Column(name = "thejson", columnDefinition = "LONGTEXT")
    private String theJSON;

}
