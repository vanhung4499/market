package com.hnv99.market.web.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static com.hnv99.market.web.common.UserRegex.PHONE_NUMBER_REGEXP;
import static com.hnv99.market.web.common.UserRegex.USER_NAME_REGEXP;
import static com.hnv99.market.web.user.UpdateAddress.Request;
import static com.hnv99.market.web.user.UpdateAddress.InfoRequest;

public sealed interface UpdateAddress permits Request, InfoRequest {

    record Request(
            @NotNull(message = "Please enter address id.")
            @Schema(description = "Address Id")
            Long addressId,

            @NotBlank(message = "Please enter your address description.")
            @Schema(description = "Address detail")
            String description,

            @NotBlank(message = "Please enter receiver name.")
            @Pattern(regexp = USER_NAME_REGEXP, message = "Please enter a valid name.")
            @Schema(description = "Receiver name")
            String receiver,

            @NotBlank(message = "Please enter receiver phone number.")
            @Pattern(regexp = PHONE_NUMBER_REGEXP, message = "Please enter a valid phone number.")
            @Schema(description = "Receiver phone number")
            String phone
    ) implements UpdateAddress {
    }

    record InfoRequest (
        @NotNull(message = "Please enter address id.")
        @Schema(description = "Address Id")
        Long addressId,

        @NotBlank(message = "Please enter receiver name.")
        @Pattern(regexp = USER_NAME_REGEXP, message = "Please enter a valid name.")
        @Schema(description = "Receiver name")
        String receiver,

        @NotBlank(message = "Please enter receiver phone number.")
        @Pattern(regexp = PHONE_NUMBER_REGEXP, message = "Please enter a valid phone number.")
        @Schema(description = "Receiver phone number")
        String contactPhone,

        @NotNull(message = "Please select receive area.")
        @Schema(description = "Receive area", allowableValues = {"DOOR", "OFFICE", "LOCKER", "ETC"})
        String receiveArea,

        @Schema(description = "Entrance password")
        String entrancePassword,

        @NotBlank(message = "Please select message alert time.")
        @Schema(description = "Delivery message alert time", allowableValues = {"ALWAYS", "MORNING", "AFTERNOON"})
        String messageAlertTime

    ) implements UpdateAddress {
    }
}
