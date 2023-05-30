package com.example.picmejava.instagram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Service
@Tag(name = "Instagram Service", description = "Serviço responsável por integração com o Instagram")
public class InstagramService {

    String client_id = "1556911098134262";
    String client_secret = "9e25f1e49cf74a41f362317268f06c5c";
    String grant_type = "authorization_code";
    String redirect_uri = "https://2-cco-grupo-4.github.io/site-instituicional/";

    private final WebClient webClient;

    @Autowired
    public InstagramService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.build();
    }

    @Operation(summary = "Obter token de acesso do usuário do Instagram")
    public Mono<UsuarioInstagram> postUsuarioInsta(String codigo){
        String cURL = "https://api.instagram.com/oauth/access_token";

        return webClient.post()
                .uri(cURL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("client_id", client_id)
                        .with("client_secret", client_secret)
                        .with("grant_type", grant_type)
                        .with("redirect_uri", redirect_uri)
                        .with("code", codigo))
                .retrieve()
                .bodyToMono(UsuarioInstagram.class)
                .onErrorResume(e -> {
                    if (e instanceof WebClientResponseException) {
                        WebClientResponseException responseException = (WebClientResponseException) e;
                        String responseBody = new String(responseException.getResponseBodyAsByteArray());
                        System.out.println(responseBody);
                    }
                    return Mono.error(e);
                })
                .map(resposta -> {
                    UsuarioInstagram retorno = new UsuarioInstagram();
                    retorno.setAccess_token(resposta.getAccess_token());
                    retorno.setUser_id(resposta.getUser_id());
                    return retorno;
                });

    }

    @Operation(summary = "Obter imagens do usuário no Instagram")
    public Mono<ListaImagensInstagram> getImagensInsta(String accessToken) {
        String cURL = "https://graph.instagram.com/me/media?fields=id,caption&access_token=" + accessToken;

        return webClient.get()
                .uri(cURL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ListaImagensInstagram.class);
    }

    @Operation(summary = "Obter detalhes de uma imagem do Instagram")
    public Mono<MediaInstagram> getImagem(String idImagem, String accessToken) {
        String cURL = String.format("https://graph.instagram.com/%s?fields=id,media_type,media_url,permalink,caption,username,timestamp&access_token=%s", idImagem, accessToken);

        return webClient.get()
                .uri(cURL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MediaInstagram.class);
    }
}
