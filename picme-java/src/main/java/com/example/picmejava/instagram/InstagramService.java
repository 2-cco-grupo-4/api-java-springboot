package com.example.picmejava.instagram;

import com.example.picmejava.instagram.UsuarioInstagram;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class InstagramService {

    String client_id = "1556911098134262";
    String client_secret = "9e25f1e49cf74a41f362317268f06c5c";
    String grant_type = "authorization_code";
    String redirect_uri = "https://2-cco-grupo-4.github.io/site-instituicional/";

    private final InstagramClient instagramClient;

    public InstagramService(InstagramClient instagramClient) {
        this.instagramClient = instagramClient;
    }

    public UsuarioInstagram postUsuarioInsta(String codigo) {
        String clientId = "1556911098134262";
        String clientSecret = "9e25f1e49cf74a41f362317268f06c5c";
        String grantType = "authorization_code";
        String redirectUri = "https://2-cco-grupo-4.github.io/site-instituicional/";

        UsuarioInstagram response = instagramClient.getAcessToken(clientId, clientSecret, grantType, redirectUri, codigo);

        UsuarioInstagram usuarioInstagram = new UsuarioInstagram();
        usuarioInstagram.setAccess_token(response.getAccess_token());
        usuarioInstagram.setUser_id(response.getUser_id());

        return usuarioInstagram;
    }

//    public Mono<UsuarioInstagram> postUsuarioInsta(String codigo){
//        String cURL = "https://api.instagram.com/oauth/access_token";
//
//        return webClient.post()
//                .uri(cURL)
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .body(BodyInserters.fromFormData("client_id", client_id)
//                        .with("client_secret", client_secret)
//                        .with("grant_type", grant_type)
//                        .with("redirect_uri", redirect_uri)
//                        .with("code", codigo))
//                .retrieve()
//                .bodyToMono(UsuarioInstagram.class)
//                .onErrorResume(e -> {
//                    if (e instanceof WebClientResponseException) {
//                        WebClientResponseException responseException = (WebClientResponseException) e;
//                        String responseBody = new String(responseException.getResponseBodyAsByteArray());
//                        System.out.println(responseBody);
//                    }
//                    return Mono.error(e);
//                })
//                .map(resposta -> {
//                    UsuarioInstagram retorno = new UsuarioInstagram();
//                    retorno.setAccess_token(resposta.getAccess_token());
//                    retorno.setUser_id(resposta.getUser_id());
//                    return retorno;
//                });
//
//    }

}
