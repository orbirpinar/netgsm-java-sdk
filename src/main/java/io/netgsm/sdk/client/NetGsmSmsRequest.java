package io.netgsm.sdk.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class NetGsmSmsRequest {
    private String message;
    private String title;
    private List<String> telephoneNumbers;
}
