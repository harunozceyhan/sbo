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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smart.sbo.common.CommonLibrary;
import com.smart.sbo.domain.beden.Operation;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class SiparisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSiparisEndpoint() throws Exception {
        String operationId = addOperation();
        getOperation();
        String siparisId = addSiparis(operationId);
        getSiparis();
        deleteSiparis(siparisId);
        deleteOperation(operationId);
    }

    public String addOperation() throws Exception {
        JsonObject responseJson = JsonParser.parseString(this.mockMvc.perform(post("/operations").content(CommonLibrary.asJsonString(new Operation("Operation 1", "O1"))).accept(MediaType.ALL)).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString()).getAsJsonObject();
        return responseJson.get("id").getAsString();
    }

    public String addSiparis(String operationId) throws Exception {
        String siparisData = "{\"adi\":\"Sipariş 1\",\"kodu\":\"S1\",\"operation\":{\"id\":\"" + operationId + "\"}}";
        JsonObject responseJson = JsonParser.parseString(this.mockMvc.perform(post("/siparis").content(siparisData).accept(MediaType.ALL)).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString()).getAsJsonObject();
        return responseJson.get("id").getAsString();
    }

    public void getSiparis() throws Exception {
        this.mockMvc.perform(get("/siparis")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    }

    public void getOperation() throws Exception {
        this.mockMvc.perform(get("/operations")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    }

    public void deleteOperation(String id) throws Exception {
        this.mockMvc.perform(delete("/operations/" + id)).andExpect(status().isNoContent());
    }

    public void deleteSiparis(String id) throws Exception {
        this.mockMvc.perform(delete("/siparis/" + id)).andExpect(status().isNoContent());
    }
}