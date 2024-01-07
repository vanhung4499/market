package com.hnv99.market.web.user;


import io.swagger.v3.oas.annotations.media.Schema;

public sealed interface GetAddress permits GetAddress.Response {

    record Response(
            @Schema(description = "Is default address")
            boolean isDefault,
            @Schema(description = "Is express address")
            boolean isExpress,

            @Schema(description = "Address")
            String address,

            @Schema(description = "Receiver")
            String receiver,

            @Schema(description = "Phone number")
            String phoneNumber
    ) implements GetAddress {
    }
}
