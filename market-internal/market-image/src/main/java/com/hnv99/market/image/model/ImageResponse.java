package com.hnv99.market.image.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ImageResponse(
        @JsonProperty
        ImageData data,

        @JsonProperty("success")
        boolean success,

        @JsonProperty("status")
        int status
) {
}
