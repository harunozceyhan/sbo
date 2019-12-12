package com.smart.sbo.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@SpringBootTest
@WebMvcTest
public class SiparisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void all() throws Exception {
        MultiValueMap<String, Integer> operationParams = new LinkedMultiValueMap<>();
        operationParams.add("id", 1);
        MultiValueMap<String, String> siparisParams = new LinkedMultiValueMap<>();
        siparisParams.add("adi", "Sipariş 1");
        siparisParams.add("kodu", "S1");
        siparisParams.add("operation", asJsonString(operationParams));
        
        this.mockMvc.perform(get("/siparis")).andExpect(status().isOk());
        this.mockMvc.perform(post("/siparis").params(siparisParams).header("Content-Type", "application/json")).andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            System.out.println(jsonContent);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}