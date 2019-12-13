package com.smart.sbo.controller;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SiparisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void all() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI5V1lCalZzbEtBU2o2Sk92QzN2UEd6RGdnbl9yNHdiWDI3ejRHd1VJWGlJIn0.eyJqdGkiOiJhMWViOThlNi02MThmLTQ5YTUtOGI1Mi1mNWNlYzYwZjkxZWIiLCJleHAiOjE1NzYxOTg3MjMsIm5iZiI6MCwiaWF0IjoxNTc2MTk4NDIzLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWFsbXMvVGVzdCIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI1YTA2YWI4MC04NzJhLTQ5NzEtYjUxNS1hNjlkODM0ZWM5NmIiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJzdG9rIiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiZmIzYjBmZjQtYjJhZi00MzYxLTk3MzgtYmQ0ZWI3NTc3N2ZhIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJzdG9rLWFkbWluIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJuYW1lIjoiSGFydW4gw5Z6Y2V5aGFuIiwicHJlZmVycmVkX3VzZXJuYW1lIjoiaGFydW4iLCJnaXZlbl9uYW1lIjoiSGFydW4iLCJmYW1pbHlfbmFtZSI6IsOWemNleWhhbiIsImVtYWlsIjoiaGFydW5vemNleWhhbkBnbWFpbC5jb20ifQ.Su_S5pWqnoMceGTQF4N3jiCmmytm8MYB1J8HD2l12lkWM_gSH_qNkGTYkPZgtD7RkMNCZ1Pj5QTGohFb6cmmmOGZ8MbkZcwlff1QiLAi6T2X_gjo6hy54KhUtjQKMo_tQWpHBVAV9pSG_-HZ-j2fA0DFLR06-5rMXdBuUIcTZsm53rZC7UofZB11xD1NwTMV138rY252s90jNKH6py3WcwiMsi3cAZ5Dvp94nx_uahULLWxd_6AVufGAtncBr8LKZcdaJM6gvGSiqsBqrgvpGZ52983u3ingd9wM9OkZKCM2NODhR91p79LpefshjuRHsXwoSqRFOrONUSsSTkChCw"; 
        String data = "{\"adi\":\"Sipariş 1\",\"kodu\":\"S1\",\"operation\":{\"id\":1}}";
        this.mockMvc.perform(get("/siparis").header("Authorization", "Bearer " + token)).andExpect(status().isOk());
        this.mockMvc.perform(post("/siparis").contentType(MediaType.APPLICATION_JSON).content(data).header("Authorization", "Bearer " + token)).andExpect(status().isCreated()).andDo(mvcResult -> {
            String json = mvcResult.getResponse().getContentAsString();
            this.mockMvc.perform(delete("/siparis/" + new JSONObject(json).get("id")).header("Authorization", "Bearer " + token)).andExpect(status().isNoContent());
        });
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