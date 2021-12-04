package com.gmail.elbaglikov.palmetto;

import com.gmail.elbaglikov.palmetto.config.JsonUtil;
import com.gmail.elbaglikov.palmetto.config.TestData;
import com.gmail.elbaglikov.palmetto.model.OrderStatus;
import com.gmail.elbaglikov.palmetto.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DirtiesContext
@AutoConfigureMockMvc
@SpringBootTest
public class ControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService mockService;

    @Test
    public void testGetController() throws Exception {
        given(mockService.getById(1)).willReturn(Optional.of(TestData.ORDER));
        mvc.perform(get("/get-order-status/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(OrderStatus.DELIVER.toString())));
    }

    @Test
    public void testPostController() throws Exception {
        given(mockService.create(Mockito.any())).willReturn(TestData.ORDER);
        mvc.perform(post("/send-order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(TestData.ORDER))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)));
    }
}
