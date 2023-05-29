package com.example.picmejava.instagram;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "instagram-api", url = "https://api.instagram.com")
public interface InstagramClient {

    @PostMapping("/oauth/access_token")
    UsuarioInstagram getAcessToken(@RequestBody MultiValueMap<String, String> params);

}
