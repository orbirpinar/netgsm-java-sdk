package io.netgsm.sdk.client;

public class NetGsmClientFactory {
    public static NetGsmClient createClient(String user, String password, String company) {
        return new NetGsmHttpClient(user, password, company);
    }
}
