package com.bank.agencies.endpoint;

import com.bank.agencies.converter.AgencyResponseConverter;
import com.bank.agencies.domain.AgencyGatewayResponse;
import com.bank.agencies.domain.AgencyResponse;
import com.bank.agencies.repository.AgenciesRepository;
import com.bank.agencies.usecase.FindAllAgenciesUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/agencies", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgenciesController {

    private final FindAllAgenciesUseCase findAllAgenciesUseCase;

    private final AgencyResponseConverter agencyResponseConverter;

    private final AgenciesRepository agenciesRepository;

    public AgenciesController(FindAllAgenciesUseCase findAllAgenciesUseCase, AgencyResponseConverter agencyResponseConverter, AgenciesRepository agenciesRepository) {
        this.findAllAgenciesUseCase = findAllAgenciesUseCase;
        this.agencyResponseConverter = agencyResponseConverter;
        this.agenciesRepository = agenciesRepository;
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

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Map<String, List<AgencyResponse>>> saveAgency() {

        List<AgencyGatewayResponse> agencies = findAllAgenciesUseCase.execute();
        List<AgencyResponse> agencyResponse = agencyResponseConverter.getAgencyResponse(agencies);
        agenciesRepository.saveAll(agencyResponse);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/optimized")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Iterable<AgencyResponse>> findAllAgenciesOptimized() {

        return new ResponseEntity<>(agenciesRepository.findAll(), HttpStatus.OK);
    }
}
