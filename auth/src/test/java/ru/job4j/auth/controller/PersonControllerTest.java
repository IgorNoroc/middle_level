package ru.job4j.auth.controller;


import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.auth.AuthApplication;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = AuthApplication.class)
@AutoConfigureMockMvc
class PersonControllerTest {
    @MockBean
    private PersonRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllPersonsAndStatusOk() throws Exception {
        mockMvc.perform(get("/person/"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(repository).findAll();
        PersonController controller = new PersonController(repository);
        when(repository.findAll()).thenReturn(List.of(new Person()));
        assertThat(controller.findAll(), is(List.of(new Person())));
    }

    @Test
    public void shouldReturnPersonAndStatusOk() throws Exception {
        Person person = new Person();
        person.setId(1);
        person.setLogin("igor");
        person.setPassword("123");
        when(repository.findById(1)).thenReturn(Optional.of(person));
        mockMvc.perform(get("/person/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(repository).findById(1);
        PersonController controller = new PersonController(repository);
        assertThat(controller.findById(1), is(new ResponseEntity<>(person, HttpStatus.OK)));
    }

    @Test
    public void shouldCreatePersonAndReturnStatusCreated() throws Exception {
        Person person = new Person();
        person.setLogin("igor");
        person.setPassword("123");
        mockMvc.perform(post("/person/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(person)))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(repository).save(person);
        when(repository.save(person)).thenReturn(person);
        PersonController controller = new PersonController(repository);
        assertThat(controller.create(person), is(new ResponseEntity<>(person, HttpStatus.CREATED)));
    }

    @Test
    public void shouldUpdatePersonAndReturnStatusOk() throws Exception {
        Person person = new Person();
        person.setId(1);
        person.setLogin("igor");
        person.setPassword("123");
        mockMvc.perform(put("/person/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new Gson().toJson(person)))
                .andDo(print())
                .andExpect(status().isOk());
        verify(repository).save(person);
        PersonController controller = new PersonController(repository);
        assertThat(controller.update(person), is(ResponseEntity.ok().build()));
    }

    @Test
    public void shouldRemovePersonAndReturnStatusOk() throws Exception {
        Person person = new Person();
        person.setId(1);
        mockMvc.perform(delete("/person/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(repository).delete(person);
        PersonController controller = new PersonController(repository);
        assertThat(controller.delete(1), is(ResponseEntity.ok().build()));
    }
}