package com.nttdata.projeto.escola.repository.rest;

import com.nttdata.projeto.escola.dto.DisciplinaRestDto;
import com.nttdata.projeto.escola.dto.EscolaridadeRestDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class MinisterioRestRepository {

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8088")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8088"))
            .clientConnector(new ReactorClientHttpConnector(HttpClient.create(ConnectionProvider.newConnection())))
            .build();


    public Optional<List<DisciplinaRestDto>> findAllDisciplinas () {
        List<DisciplinaRestDto> listDisciplinas;
        try {
            listDisciplinas = webClient.get()
                    .uri("/disciplinas")
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(new ParameterizedTypeReference<List<DisciplinaRestDto>>() {
                    })
                    .onErrorReturn(new ArrayList<>())
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    }).block();

        }catch( Exception e) {
            listDisciplinas = null;
        }
        if(listDisciplinas!=null){
            return Optional.of(listDisciplinas);
        }else return Optional.empty();
    }

    public List<String> findAllDistinctArea () {
        List<String> listDistinctAreas;
        try {
            listDistinctAreas = webClient.get()
                    .uri("/disciplinas/areas")
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(new ParameterizedTypeReference<List<String>>() {
                    })
                    .onErrorReturn(new ArrayList<>())
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    }).block();

        }catch( Exception e) {
            listDistinctAreas = null;
        }
        if(listDistinctAreas!=null){
            return listDistinctAreas;
        }else return null;
    }

    public Optional<List<EscolaridadeRestDto>> findAllEscolaridades () {
        List<EscolaridadeRestDto> listEscolaridades;
        try {
            listEscolaridades = webClient.get()
                    .uri("/escolaridades")
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(new ParameterizedTypeReference<List<EscolaridadeRestDto>>() {
                    })
                    .onErrorReturn(new ArrayList<>())
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    }).block();

        }catch( Exception e) {
            listEscolaridades = null;
        }
        if(listEscolaridades!=null){
            return Optional.of(listEscolaridades);
        }else return Optional.empty();
    }


    public Optional<EscolaridadeRestDto> findEscolaridadeByIdade (int idade) {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8088")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8088"))
                .clientConnector( new ReactorClientHttpConnector( HttpClient.create(ConnectionProvider.newConnection())) )
                .build();

        EscolaridadeRestDto escolaridadeRestDto;
        try {
            escolaridadeRestDto = webClient
                    .get()
                    .uri("/escolaridades/" + idade)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })
                    .bodyToMono(EscolaridadeRestDto.class)
                    .block();
        }
        catch( Exception e) {

            escolaridadeRestDto = null;
        }

        if( escolaridadeRestDto != null )
            return Optional.of(escolaridadeRestDto);
        else
            return Optional.empty();
    }

    public Optional<DisciplinaRestDto> findDisciplinaByTitulo (String titulo) {

        DisciplinaRestDto disciplinaRestDto;
        try {
            disciplinaRestDto = webClient
                    .get()
                    .uri("/disciplinas/" + titulo)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })
                    .bodyToMono(DisciplinaRestDto.class)
                    .block();
        }
        catch( Exception e) {
            disciplinaRestDto = null;
        }
        if( disciplinaRestDto != null )
            return Optional.of(disciplinaRestDto);
        else
            return Optional.empty();
    }
}


