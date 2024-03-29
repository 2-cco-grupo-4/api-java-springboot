package com.example.picmejava.instagram.service;

import com.example.picmejava.instagram.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@Service
@Tag(name = "Instagram Service", description = "Serviço responsável por integração com o Instagram")
public class InstagramService {

    private final WebClient webClient;

    public InstagramService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.build();
    }

    @Operation(summary = "Obter token de acesso do usuário do Instagram")
    public Mono<AccessToken> postUsuarioInsta(String codigo){
        String cURL = "http://10.0.0.244:8080/instagram/access_token";

        return webClient.post()
                .uri(cURL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData("codigo", codigo))
                .retrieve()
                .bodyToMono(AccessToken.class)
                .onErrorResume(e -> {
                    if (e instanceof WebClientResponseException) {
                        WebClientResponseException responseException = (WebClientResponseException) e;
                        String responseBody = new String(responseException.getResponseBodyAsByteArray());
                        System.out.println(responseBody);
                    }
                    return Mono.error(e);
                })
                .map(resposta -> {
                    AccessToken retorno = new AccessToken();
                    retorno.setAccess_token(resposta.getAccess_token());
                    retorno.setUser_id(resposta.getUser_id());
                    return retorno;
                });

    }

    @Operation(summary = "Obter imagens do usuário no Instagram")
    public Mono<ListData> getImagensInsta(String accessToken) {
        String cURL = "http://10.0.0.244:8080/instagram/lista_imagens?accessToken=" + accessToken;

        ListData response = webClient.get()
                .uri(cURL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ListData.class)
                .block();

        return Mono.just(response);
    }

    @Operation(summary = "Obter detalhes de uma imagem do Instagram")
    public Mono<Media> getImagem(String idImagem, String accessToken) {
        String cURL = String.format("http://10.0.0.244:8080/instagram/imagem?idImagem=%s&accessToken=%s", idImagem, accessToken);

        Media response = webClient.get()
                .uri(cURL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Media.class)
                .block();

        return Mono.just(response);
    }

    @Operation(summary = "Obter detalhes de diversa imagens do Instagram")
    public List<Media> getListImagens(String accessToken) {

        Mono<ListData> listMediaInstagramMono = getImagensInsta(accessToken);

        ListData listaMediaInstagram = listMediaInstagramMono.block();

        List<ItemData> listData = listaMediaInstagram.getData();

        List<Media> listaImagens = new ArrayList<>();

        for (ItemData itemData : listData) {
            Mono<Media> mediaInstagramMono = getImagem(itemData.getId(), accessToken);
            listaImagens.add(mediaInstagramMono.block());
        }

        return listaImagens;

    }

    // Chamar endpoints para o long access token e fazer testes de inserção no BD
    @Operation(summary = "Obter access token de longa duração", description = "Endpoint utilizado para gerar um acces token de longa duração (Válido por 60 dias)")
    public Mono<LongAccessToken> getLongAccessToken(String accessToken) {

        String cURL = String.format("http://10.0.0.244:8080/instagram/long_access_token?accessToken=%s", accessToken);

        LongAccessToken longAccessToken = webClient.get()
                .uri(cURL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(LongAccessToken.class)
                .block();

        return Mono.just(longAccessToken);

    }

    @Operation(summary = "Renovar access token de longa duração", description = "Endpoint utilizado para obter um long access token renovado por mais 60 dias")
    public Mono<LongAccessToken> getRefreshedLongAccessToken(String accessToken) {

        String cURL = String.format("http://10.0.0.244:8080/instagram/refresh_access_token?accessToken=%s", accessToken);

        LongAccessToken longAccessToken = webClient.get()
                .uri(cURL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(LongAccessToken.class)
                .block();


        return Mono.just(longAccessToken);

    }

}
