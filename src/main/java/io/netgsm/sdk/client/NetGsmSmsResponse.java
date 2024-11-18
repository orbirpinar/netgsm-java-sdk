package io.netgsm.sdk.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class NetGsmSmsResponse {
    private final boolean success;
    private final String message;
    private final String errorMessage;
    private final String errorCode;

    public static NetGsmSmsResponse success(String message) {
        return NetGsmSmsResponse.builder()
                .success(true)
                .message(message)
                .build();
    }

    public static NetGsmSmsResponse failed(String errorCode, String errorMessage) {
        return NetGsmSmsResponse.builder()
                .success(false)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }
}
