package com.nttdata.projeto.escola.repository.rest;

import com.nttdata.projeto.escola.dto.DisciplinaRestDto;
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

   /* public Optional<List<DisciplinaRestDto>> findAllDisciplinas () {
        try {
            Mono<List<DisciplinaRestDto>> response = webClient.get()
                    .uri("/disciplinas")
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(new ParameterizedTypeReference<List<DisciplinaRestDto>>() {
                    })
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            List<DisciplinaRestDto> lista = response.block();
            return Optional.of(lista);
        }catch( Exception e) {
            return Optional.empty();
        }
    }*/

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


   /* public Optional<DisciplinaRestDto> findDisciplina(int id) {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8088")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8088"))
                .clientConnector( new ReactorClientHttpConnector( HttpClient.create(ConnectionProvider.newConnection())) )
                .build();

        DisciplinaRestDto disciplinaRestDTO;
        try {
            disciplinaRestDTO = webClient
                    .get()
                    .uri("/disciplinas/" + id)
                    .retrieve()

                    .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })

                    .bodyToMono(DisciplinaRestDto.class)

                    .onErrorReturn( new DisciplinaRestDto("t", "f") )

                    .doOnError(throwable -> { System.out.println( throwable.getMessage() );} )
                    .block();
        }
        catch( Exception e) {

            disciplinaRestDTO = null;
        }

        if( disciplinaRestDTO != null )
            return Optional.of(disciplinaRestDTO);
        else
            return Optional.empty();
    }*/
}


