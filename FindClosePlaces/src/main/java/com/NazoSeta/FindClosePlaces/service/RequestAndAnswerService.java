package com.NazoSeta.FindClosePlaces.service;

import com.NazoSeta.FindClosePlaces.dto.RequestAndAnswerDTO;
import com.NazoSeta.FindClosePlaces.model.RequestAndAnswer;
import com.NazoSeta.FindClosePlaces.repository.RequestAndAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestAndAnswerService {

    private final RequestAndAnswerRepository requestAndAnswerRepository;

    @Value("${google.places.api.key}")
    private String apiKey;

    private final String GOOGLE_PLACES_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";

    public String getNearbyPlaces(String longitude, String latitude, String radius){
        String url = GOOGLE_PLACES_URL +
                "?location=" + latitude + "," + longitude +
                "&radius=" + radius +
                "&key=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    public RequestAndAnswerDTO addLocation(RequestAndAnswer requestAndAnswer) {

        if(!(requestAndAnswerRepository.existsByCoordinatesAndRadius(requestAndAnswer.getLongitude(), requestAndAnswer.getLatitude(), requestAndAnswer.getRadius()))) {
            requestAndAnswer.setTheJSON(getNearbyPlaces(requestAndAnswer.getLongitude(), requestAndAnswer.getLatitude(), requestAndAnswer.getRadius()));
            requestAndAnswerRepository.save(requestAndAnswer);
        }
        else {
            requestAndAnswer = requestAndAnswerRepository.findJSON(requestAndAnswer.getLongitude(), requestAndAnswer.getLatitude(), requestAndAnswer.getRadius());
        }

        ModelMapper modelMapper = new ModelMapper();
        RequestAndAnswerDTO requestAndAnswerDTO = modelMapper.map(requestAndAnswer, RequestAndAnswerDTO.class);

        return requestAndAnswerDTO;
    }

    public List<RequestAndAnswerDTO> getAllLocations() {

        List<RequestAndAnswer> requestAndAnswerList = requestAndAnswerRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<RequestAndAnswerDTO> requestAndAnswerDTOList = Arrays.asList(modelMapper.map(requestAndAnswerList, RequestAndAnswerDTO[].class));

        return requestAndAnswerDTOList;
    }

    public RequestAndAnswerDTO getLocation(Long id) {

        RequestAndAnswer requestAndAnswer = requestAndAnswerRepository.findById(id).orElseThrow();
        ModelMapper modelMapper = new ModelMapper();
        RequestAndAnswerDTO requestAndAnswerDTO = modelMapper.map(requestAndAnswer, RequestAndAnswerDTO.class);

        return requestAndAnswerDTO;
    }

    public RequestAndAnswerDTO updateLocation(RequestAndAnswer newRequestAndAnswer, Long id){

        RequestAndAnswer requestAndAnswer = requestAndAnswerRepository.findById(id).orElseThrow();

        if (newRequestAndAnswer.getLongitude() != null) {
            requestAndAnswer.setLongitude(newRequestAndAnswer.getLongitude());
        }
        if (newRequestAndAnswer.getLatitude() != null) {
            requestAndAnswer.setLatitude(newRequestAndAnswer.getLatitude());
        }
        if (newRequestAndAnswer.getRadius() != null) {
            requestAndAnswer.setRadius(newRequestAndAnswer.getRadius());
        }

        requestAndAnswerRepository.save(requestAndAnswer);

        ModelMapper modelMapper = new ModelMapper();
        RequestAndAnswerDTO requestAndAnswerDTO = modelMapper.map(requestAndAnswer, RequestAndAnswerDTO.class);

        return requestAndAnswerDTO;
    }

    public RequestAndAnswerDTO deleteLocation(Long id){

        RequestAndAnswer requestAndAnswer = requestAndAnswerRepository.findById(id).orElseThrow();
        ModelMapper modelMapper = new ModelMapper();
        RequestAndAnswerDTO requestAndAnswerDTO = modelMapper.map(requestAndAnswer, RequestAndAnswerDTO.class);

        requestAndAnswerRepository.delete(requestAndAnswer);

        return requestAndAnswerDTO;
    }

}
