package com.bank.agencies.converter;

import com.bank.agencies.domain.AgencyGatewayResponse;
import com.bank.agencies.domain.AgencyResponse;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AgencyResponseConverter {

    public List<AgencyResponse> getAgencyResponse(List<AgencyGatewayResponse> agencies) {
        return agencies.stream()
                .map(agencyGateway -> AgencyResponse.AgencyResponseBuilder.anAgencyResponse()
                        .bank(agencyGateway.getBank())
                        .city(agencyGateway.getCity())
                        .name(agencyGateway.getName())
                        .state(agencyGateway.getState()).build())
                .collect(Collectors.toList());
    }

    public Map<String, List<AgencyResponse>> getAgencyResponseGrouped(List<AgencyGatewayResponse> agencies) {
        return getAgencyResponse(agencies).stream()
                .collect(Collectors.groupingBy(AgencyResponse::getState))
                .entrySet().stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (agency1, agency2) -> agency1, LinkedHashMap::new));

    }
}
