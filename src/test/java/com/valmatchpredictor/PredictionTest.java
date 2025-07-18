package com.valmatchpredictor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valmatchpredictor.controller.PredictorController;
import com.valmatchpredictor.model.PredictionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PredictorController.class)
public class PredictionTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testMakePredictionSuccess() throws Exception {
        PredictionRequest request = new PredictionRequest();
        request.setTeam1("G2 Esports");
        request.setTeam2("Sentinels");

        mockMvc.perform(post("/api/predict")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winner").exists())
                .andExpect(jsonPath("$.probability").exists());
    }
}