package com.bank.agencies.fixture;

import com.bank.agencies.domain.AgencyGatewayResponse;

import java.util.Arrays;
import java.util.List;

public class AgenciesFixture {

    public static List<AgencyGatewayResponse> gimmeBasicAgencies() {
        return List.of(AgencyGatewayResponse.AgencyGatewayResponseBuilder.anAgency().bank("BANCO DO BRASIL S.A.").name("EMPRESA JOAO PESSOA").city("JOAO PESSOA").state("PB").build());
    }

    public static List<AgencyGatewayResponse> gimmeBasicAgenciesWithTwo() {
        AgencyGatewayResponse Agency1 = AgencyGatewayResponse.AgencyGatewayResponseBuilder
                .anAgency().bank("BANCO DO BRASIL S.A.").name("EMPRESA JOAO PESSOA").city("JOAO PESSOA").state("PB").build();
        AgencyGatewayResponse Agency2 = AgencyGatewayResponse.AgencyGatewayResponseBuilder
                .anAgency().bank("BANCO DO BRASIL S.A.").name("EMPRESA JOAO PESSOA").city("JOAO PESSOA").state("RJ").build();
        return Arrays.asList(Agency1, Agency2);
    }
}
