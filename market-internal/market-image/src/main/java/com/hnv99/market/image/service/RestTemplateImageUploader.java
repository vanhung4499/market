package com.hnv99.market.image.service;

import com.hnv99.market.application.image.ImageUploader;
import com.hnv99.market.image.common.CustomInputStreamResource;
import com.hnv99.market.image.exception.ImageConvertFailException;
import com.hnv99.market.image.exception.ImageUploadFailException;
import com.hnv99.market.image.model.ImageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;


public class RestTemplateImageUploader implements ImageUploader {
    private static final Logger log = LoggerFactory.getLogger(RestTemplateImageUploader.class);
    private static final String IMAGE_KEY = "image";
    private static final String API_KEY = "key";

    private final RestTemplate restTemplate;

    @Value("${market.image.server}")
    private String imageUrl;

    @Value("${market.image.key}")
    private String apiKey;

    public RestTemplateImageUploader(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    @Override
    public CompletableFuture<String> upload(MultipartFile image) {
        HttpEntity<MultiValueMap<String, Object>> request = generateHttpEntityRequest(image);
        String uploadUrl = sendRequestToImageServer(request);
        log.info("image uploaded : {}", uploadUrl);
        return CompletableFuture.completedFuture(uploadUrl);
    }

    private String sendRequestToImageServer(HttpEntity<MultiValueMap<String, Object>> request) {
        ImageResponse response = restTemplate.postForEntity(imageUrl, request, ImageResponse.class).getBody();
        validateUploadSuccess(response);
        return response.data().displayUrl();
    }

    private void validateUploadSuccess(ImageResponse response) {
        if (response == null || !response.success()) {
            throw new ImageUploadFailException();
        }
    }

    private HttpEntity<MultiValueMap<String, Object>> generateHttpEntityRequest(MultipartFile image) {
        HttpHeaders httpHeaders = multipartHeaders();
        MultiValueMap<String, Object> valueMap = getMultiValueMap(image);
        return new HttpEntity<>(valueMap, httpHeaders);
    }

    private MultiValueMap<String, Object> getMultiValueMap(MultipartFile image) {
        InputStreamResource resource = initResource(image);
        return new LinkedMultiValueMap<>() {{
            add(API_KEY, apiKey);
            add(IMAGE_KEY, resource);
        }};
    }

    private InputStreamResource initResource(MultipartFile image) {
        try {
            return new CustomInputStreamResource(image.getInputStream(), image.getOriginalFilename(), image.getSize());
        } catch (IOException e) {
            throw new ImageConvertFailException(e);
        }
    }

    private HttpHeaders multipartHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MULTIPART_FORM_DATA);
        return httpHeaders;
    }

}
