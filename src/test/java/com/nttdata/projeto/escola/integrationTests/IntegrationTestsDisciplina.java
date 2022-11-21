package com.nttdata.projeto.escola.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTestsDisciplina {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldShowViewHomePageDisciplinasWithModel() throws Exception {
        MvcResult resultGet = this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/disciplinas-model"))
                        .andExpect(status().isOk())
                        .andExpect(view().name("disciplinas/index"))
                        .andExpect(model().attributeExists("disciplinas"))
                        .andReturn();

        String resultContent = resultGet.getModelAndView().getViewName();

        assertEquals("disciplinas/index", resultContent);
    }

    @Test
    void shouldShowViewHomePageDisciplinasWithModelAndView() throws Exception {
        MvcResult resultGet = this.mockMvc.perform(MockMvcRequestBuilders
                                          .get("/disciplinas"))
                                          .andExpect(status().isOk())
                                          .andExpect(view().name("disciplinas/index"))
                                          .andExpect(model().attributeExists("disciplinas"))
                                          .andReturn();

        String resultContent = resultGet.getModelAndView().getViewName();

        assertEquals("disciplinas/index", resultContent);
    }

    @Test
    void shouldShowNewDisciplinaPageWithModel() throws Exception {
        MvcResult resultGet = this.mockMvc.perform(MockMvcRequestBuilders
                                        .get("/disciplina/new"))
                                        .andExpect(status().isOk())
                                        .andExpect(view().name("disciplinas/new_disciplina"))
                                        .andExpect(model().attributeExists("disciplina"))
                                        .andExpect(model().attributeExists("disciplinasMinisterio"))
                                        .andExpect(model().attributeExists("areasMinisterio"))
                                        .andReturn();

        String resultContent = resultGet.getModelAndView().getViewName();
        assertEquals("disciplinas/new_disciplina", resultContent);
    }

}
