package com.example.calcdemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Config.class, CalculatorController.class})
@WebAppConfiguration
@SpringBootTest
class CalcDemoApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void basicControllerTest() {
        ServletContext servletContext = webApplicationContext.getServletContext();
        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("calculatorController"));
    }

    @Test
    public void statusTest() throws Exception {
        this.mockMvc.perform(get("/add").param("a", "1").param("b", "2")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void contentTypeTest() throws Exception {
        this.mockMvc.perform(get("/add")
                        .param("a", "1").param("b", "2")).andDo(print()).
                andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    public void addTest() throws Exception {
        this.mockMvc.perform(get("/add")
                .param("a", "1").param("b", "2")).andDo(print()).andExpect(content().string("3.0"));
    }


    @Test
    public void defaultAddTest() throws Exception {
        this.mockMvc.perform(get("/add")).andDo(print()).andExpect(content().string("0.0"));
    }

    @Test
    public void oneParameterAddTest() throws Exception {
        this.mockMvc.perform(get("/add").param("a", "34")).andDo(print()).andExpect(content().string("34.0"));
    }

    @Test
    public void subtractTest() throws Exception {
        this.mockMvc.perform(get("/subtract")
                .param("a", "34").param("b", "2")).andDo(print()).andExpect(content().string("32.0"));
    }

    @Test
    public void multiplyTest() throws Exception {
        this.mockMvc.perform(get("/multiply")
                .param("a", "17").param("b", "2")).andDo(print()).andExpect(content().string("34.0"));
    }

    @Test
    public void defaultMultiplyTest() throws Exception {
        this.mockMvc.perform(get("/multiply")).andDo(print()).andExpect(content().string("1.0"));
    }

    @Test
    public void divideTest() throws Exception {
        this.mockMvc.perform(get("/divide")
                .param("a", "9").param("b", "4")).andDo(print()).andExpect(content().string("2.25"));
    }

    @Test
    public void zeroDivideTest() throws Exception {
        this.mockMvc.perform(get("/divide")
                .param("a", "9").param("b", "0")).andDo(print()).andExpect(content().string("Can't divide by 0"));
    }

}
