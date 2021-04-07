package com.bank.agencies.converter;

import com.bank.agencies.domain.AgencyResponse;
import com.bank.agencies.fixture.AgenciesFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

class AgencyResponseConverterTest {

    private AgencyResponseConverter converter;

    @BeforeEach
    public void before() {
        initMocks(this);
        this.converter = new AgencyResponseConverter();
    }

    @Test
    void getAgencyResponse_shouldReturnAListWithOneAndConverted() {
        List<AgencyResponse> response = converter.getAgencyResponse(AgenciesFixture.gimmeBasicAgencies());
        assertEquals(1, response.size());
        assertEquals("PB", response.get(0).getState());
        assertEquals("BANCO DO BRASIL S.A.", response.get(0).getBank());
        assertEquals("EMPRESA JOAO PESSOA", response.get(0).getName());
        assertEquals("JOAO PESSOA", response.get(0).getCity());
    }

    @Test
    void getAgencyResponseGrouped() {
        List<AgencyResponse> response = converter.getAgencyResponse(AgenciesFixture.gimmeBasicAgenciesWithTwo());
        assertEquals(2, response.size());
        assertTrue(response.stream().findFirst().isPresent());
        assertEquals("PB", response.stream().findFirst().get().getState());
    }
}
