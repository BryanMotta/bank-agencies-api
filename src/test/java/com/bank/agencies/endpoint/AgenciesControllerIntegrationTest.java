package com.bank.agencies.endpoint;

import com.bank.agencies.Application;
import com.bank.agencies.converter.AgencyResponseConverter;
import com.bank.agencies.usecase.FindAllAgenciesUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AgenciesControllerIntegrationTest {

    private static final String API_BASE_URL = "/agencies";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mvc;
    @MockBean
    private FindAllAgenciesUseCase findAllAgenciesUseCase;
    @MockBean
    private AgencyResponseConverter agencyResponseConverter;


    @Test
    void findAllAgencies_shouldReturnOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get(API_BASE_URL)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findAllAgenciesGrouped_shouldReturnOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get(API_BASE_URL + "/grouped")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
