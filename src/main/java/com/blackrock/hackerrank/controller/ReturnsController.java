package com.blackrock.hackerrank.controller;

import com.blackrock.hackerrank.model.ReturnRequest;
import com.blackrock.hackerrank.model.ReturnResponse;
import com.blackrock.hackerrank.service.ReturnsService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blackrock/challenge/v1")
public class ReturnsController {

    private final ReturnsService returnsService;

    public ReturnsController(ReturnsService returnsService) {
        this.returnsService = returnsService;
    }

    @PostMapping("/returns:nps")
    public ReturnResponse calculateNpsReturn(
            @RequestBody ReturnRequest request) {

        return returnsService.calculateNps(request);
    }

    @PostMapping("/returns:index")
    public ReturnResponse calculateIndexReturn(
            @RequestBody ReturnRequest request) {

        return returnsService.calculateIndex(request);
    }

}