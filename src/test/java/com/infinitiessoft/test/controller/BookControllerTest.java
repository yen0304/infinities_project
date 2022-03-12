package com.infinitiessoft.test.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@DisplayName("Controller層測試")
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("測試查詢所有書籍")
    @Test
    void read() throws Exception {
        RequestBuilder requestBuilder= MockMvcRequestBuilders
                .get("/book");

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$.message",equalTo("查詢成功")))
                .andExpect(jsonPath("$.data.[0].name",equalTo("哈利波特(6)混血王子的背叛")))
                .andExpect(jsonPath("$.data.[1].name",equalTo("元宇宙大投資")));
    }
}