package com.example.picmejava.instagram;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "instagram-api", url = "https://api.instagram.com")
public interface InstagramClient {

    @PostMapping("/oauth/access_token")
    UsuarioInstagram getAcessToken(@RequestParam("client_id") String clientId,
                                        @RequestParam("client_secret") String clientSecret,
                                        @RequestParam("grant_type") String grantType,
                                        @RequestParam("redirect_uri") String redirectUri,
                                        @RequestParam("code") String code);

}
