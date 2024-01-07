package com.hnv99.market.application.image;

import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

public interface ImageUploader {

    CompletableFuture<String> upload(MultipartFile image);
}
