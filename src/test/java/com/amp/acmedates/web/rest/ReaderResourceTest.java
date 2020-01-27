package com.amp.acmedates.web.rest;

import com.amp.acmedates.AcmeDatesApp;
import com.amp.acmedates.web.rest.errors.ExceptionTranslator;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.amp.acmedates.web.rest.TestUtil.createFormattingConversionService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = AcmeDatesApp.class)
public class ReaderResourceTest {

    @Autowired
    private Validator validator;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
    @Autowired
    private ExceptionTranslator exceptionTranslator;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private MockMvc mockMvc;

    private ReaderResource readerResource;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        readerResource = Mockito.spy(new ReaderResource());
        this.mockMvc = MockMvcBuilders.standaloneSetup(readerResource).setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    private String readFile(String name) throws IOException {
        return IOUtils.toString(new FileInputStream("src/test/resources/" + name), StandardCharsets.UTF_8);
    }
    @Test
    @Transactional
    public void testNoBody() throws Exception {
        this.mockMvc.perform(post("/api/dates").contentType(TestUtil.APPLICATION_JSON_UTF8).content("")).andExpect(status().is4xxClientError());
    }

    @Test
    @Transactional
    public void testCorrect() throws Exception {
        String goodProductString = readFile("goodJson.json");
        this.mockMvc.perform(post("/api/dates").contentType(TestUtil.APPLICATION_JSON_UTF8).content(new Gson().toJson(goodProductString)))
            .andExpect(status().is2xxSuccessful());
    }
}
