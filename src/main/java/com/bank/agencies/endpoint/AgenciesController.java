package com.bank.agencies.endpoint;

import com.bank.agencies.converter.AgencyResponseConverter;
import com.bank.agencies.domain.AgencyGatewayResponse;
import com.bank.agencies.domain.AgencyResponse;
import com.bank.agencies.usecase.FindAllAgenciesUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/agencies", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgenciesController {

    private final FindAllAgenciesUseCase findAllAgenciesUseCase;

    private final AgencyResponseConverter agencyResponseConverter;

    public AgenciesController(FindAllAgenciesUseCase findAllAgenciesUseCase, AgencyResponseConverter agencyResponseConverter) {
        this.findAllAgenciesUseCase = findAllAgenciesUseCase;
        this.agencyResponseConverter = agencyResponseConverter;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AgencyResponse>> findAllAgencies() {

        List<AgencyGatewayResponse> agencies = findAllAgenciesUseCase.execute();
        List<AgencyResponse> agencyResponse = agencyResponseConverter.getAgencyResponse(agencies);

        return new ResponseEntity<>(agencyResponse, HttpStatus.OK);
    }

    @GetMapping("/grouped")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, List<AgencyResponse>>> findAllAgenciesGrouped() {

        List<AgencyGatewayResponse> agencies = findAllAgenciesUseCase.execute();
        Map<String, List<AgencyResponse>> agencyResponseGrouped = agencyResponseConverter.getAgencyResponseGrouped(agencies);

        return new ResponseEntity<>(agencyResponseGrouped, HttpStatus.OK);
    }
}
