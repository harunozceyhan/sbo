package com.smart.sbo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class SiparisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void all() throws Exception {
        String data = "{\"adi\":\"Sipariş 1\",\"kodu\":\"S1\",\"operation\":{\"id\":\"fec9d7ec-681e-42f6-916f-ef57b36498ec\"}}";
        this.mockMvc.perform(get("/siparis")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        JsonObject responseJson = JsonParser.parseString(this.mockMvc.perform(post("/siparis").content(data).accept(MediaType.ALL)).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString()).getAsJsonObject();
        this.mockMvc.perform(delete("/siparis/" + responseJson.get("id").getAsString())).andExpect(status().isNoContent());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}