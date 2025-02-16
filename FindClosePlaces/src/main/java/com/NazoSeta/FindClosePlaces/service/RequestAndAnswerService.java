package com.NazoSeta.FindClosePlaces.service;

import com.NazoSeta.FindClosePlaces.dto.RequestAndAnswerDTO;
import com.NazoSeta.FindClosePlaces.model.RequestAndAnswer;
import com.NazoSeta.FindClosePlaces.repository.RequestAndAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestAndAnswerService {

    private final RequestAndAnswerRepository requestAndAnswerRepository;

    public RequestAndAnswerDTO addLocation(RequestAndAnswer requestAndAnswer) {

        ModelMapper modelMapper = new ModelMapper();
        RequestAndAnswerDTO requestAndAnswerDTO = modelMapper.map(requestAndAnswer, RequestAndAnswerDTO.class);

        if(!(requestAndAnswerRepository.existsByCoordinatesAndRadius(requestAndAnswer.getLongitude(), requestAndAnswer.getLatitude(), requestAndAnswer.getRadius()))) {
            requestAndAnswerRepository.save(requestAndAnswer);
        }

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
