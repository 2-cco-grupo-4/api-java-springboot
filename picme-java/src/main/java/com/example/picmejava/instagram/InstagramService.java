package com.example.picmejava.instagram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;


@Service
@Tag(name = "Instagram Service", description = "Serviço responsável por integração com o Instagram")
public class InstagramService {

    String client_id = "1556911098134262";
    String client_secret = "9e25f1e49cf74a41f362317268f06c5c";
    String grant_type = "authorization_code";
    String redirect_uri = "https://2-cco-grupo-4.github.io/site-instituicional/";

//    @Autowired
//    private final InstagramClient instagramClient;

    private final WebClient webClient;

    public InstagramService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.build();
    }

//    public UsuarioInstagram postUsuarioInsta(String codigo) {
//        String clientId = "1556911098134262";
//        String clientSecret = "9e25f1e49cf74a41f362317268f06c5c";
//        String grantType = "authorization_code";
//        String redirectUri = "https://2-cco-grupo-4.github.io/site-instituicional/";
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("client_id", clientId);
//        params.add("client_secret", clientSecret);
//        params.add("grant_type", grantType);
//        params.add("redirect_uri", redirectUri);
//        params.add("code", codigo);
//
//        UsuarioInstagram response = instagramClient.getAcessToken(params);
//
//        UsuarioInstagram usuarioInstagram = new UsuarioInstagram();
//        usuarioInstagram.setAccess_token(response.getAccess_token());
//        usuarioInstagram.setUser_id(response.getUser_id());
//
//        return usuarioInstagram;
//    }

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

        ListaImagensInstagram response = webClient.get()
                .uri(cURL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ListaImagensInstagram.class)
                .block();

        return Mono.just(response);
    }

    @Operation(summary = "Obter detalhes de uma imagem do Instagram")
    public Mono<MediaInstagram> getImagem(String idImagem, String accessToken) {
        String cURL = String.format("https://graph.instagram.com/%s?fields=id,media_type,media_url,permalink,caption,username,timestamp&access_token=%s", idImagem, accessToken);

        MediaInstagram response = webClient.get()
                .uri(cURL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MediaInstagram.class)
                .block();

        return Mono.just(response);
    }

}
