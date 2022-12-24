package com.mryunqi.pixivtgbot.service;

import com.alibaba.fastjson2.JSONObject;
import com.mryunqi.pixivtgbot.config.RestTemplateConfig;
import com.mryunqi.pixivtgbot.entity.PixivConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author mryunqi
 * @date 2022/12/24
 */
@Slf4j
public class Visit {
    public static JSONObject getUrlData(String url) throws Exception {
        RestTemplate restTemplate = new RestTemplate(RestTemplateConfig.generateHttpRequestFactory());
        PixivConfig pixiv = new PixivConfig();
        String cookie = pixiv.getCookie();
        // Set the cookie in the request header
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Mobile Safari/537.36");
        headers.add("Cookie",cookie);
        HttpEntity<String> request = new HttpEntity<>(headers);
        // Make the GET request to the URL
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        // Get the JSON data from the response
        String json = response.getBody();
        return JSONObject.parseObject(json);
    }
}
