package io.netgsm.sdk;

import io.netgsm.sdk.client.NetGsmClient;
import io.netgsm.sdk.client.NetGsmClientFactory;
import io.netgsm.sdk.client.NetGsmSmsRequest;
import io.netgsm.sdk.client.NetGsmSmsResponse;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        NetGsmClient client = NetGsmClientFactory.createClient("123123123", "X4-19123", "Netgsm");
        NetGsmSmsRequest request = NetGsmSmsRequest.builder()
                .title("Title")
                .message("test")
                .telephoneNumbers(List.of("555555555"))
                .build();
        NetGsmSmsResponse netGsmSmsResponse = client.sendSingleMessage(request);
        System.out.println(netGsmSmsResponse.isSuccess());
        System.out.println(netGsmSmsResponse.getMessage());
        System.out.println(netGsmSmsResponse.getErrorCode());
        System.out.println(netGsmSmsResponse.getErrorMessage());
    }
}