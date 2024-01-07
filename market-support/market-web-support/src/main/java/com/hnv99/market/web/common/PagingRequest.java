package com.hnv99.market.web.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.springframework.data.domain.Sort.Direction;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

public record PagingRequest (
        @Positive(message = "The page number must be positive.")
        @Schema(description = "Page number")
        int page,

        @Schema(description = "Sorting direction", requiredMode = NOT_REQUIRED, allowableValues = {"DESC", "ASC"})
        Direction sort
) {

    private static final int INDEX_GAP = 1;
    private static final int DEFAULT_SIZE = 10;
    private static final Direction DEFAULT_DIRECTION = Direction.DESC;
    private static final String SORTING_CRITERIA = "createAt";

    public Pageable toPageable() {
        return PageRequest.of(
                page - INDEX_GAP,
                DEFAULT_SIZE,
                Sort.by(
                        sort == null ? DEFAULT_DIRECTION : sort,
                        SORTING_CRITERIA
                )
        );
    }
}
