package com.NazoSeta.FindClosePlaces.controller;

import com.NazoSeta.FindClosePlaces.dto.RequestAndAnswerDTO;
import com.NazoSeta.FindClosePlaces.model.RequestAndAnswer;
import com.NazoSeta.FindClosePlaces.service.RequestAndAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class RequestAndAnswerController {

    @Autowired
    private final RequestAndAnswerService requestAndAnswerService;

    @PostMapping("/addlocation")
    public RequestAndAnswerDTO addLocation(@RequestBody RequestAndAnswer requestAndAnswer) {
        return requestAndAnswerService.addLocation(requestAndAnswer);
    }

    @GetMapping("/getalllocations")
    public List<RequestAndAnswerDTO> getAllLocations(){
        return requestAndAnswerService.getAllLocations();
    }

    @GetMapping("/getlocation/{id}")
    public RequestAndAnswerDTO getLocation(@PathVariable Long id) {
        return requestAndAnswerService.getLocation(id);
    }

    @PutMapping("/updatelocation/{id}")
    public RequestAndAnswerDTO updateLocation(@RequestBody RequestAndAnswer requestAndAnswer, @PathVariable Long id) {
        return requestAndAnswerService.updateLocation(requestAndAnswer, id);
    }

    @DeleteMapping("/deletelocation/{id}")
    public RequestAndAnswerDTO deleteLocation(@PathVariable Long id){
        return requestAndAnswerService.deleteLocation(id);
    }

}
