package io.netgsm.sdk.client;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

class NetGsmHttpClient implements NetGsmClient {
    private final String userCode;
    private final String password;
    private final String company;



    NetGsmHttpClient(String user, String password, String company) {
        this.userCode = user;
        this.password = password;
        this.company = company;
    }

    @Override
    public NetGsmSmsResponse sendSingleMessage(NetGsmSmsRequest request) {
        NetGsmSmsPayload.Header header = NetGsmSmsPayload.Header.builder()
                .company(company)
                .password(password)
                .messageHeader(request.getTitle())
                .userCode(userCode)
                .type("1:n")
                .build();
        NetGsmSmsPayload.Body body = NetGsmSmsPayload.Body.builder()
                .telephoneNumbers(request.getTelephoneNumbers().toArray(new String[0]))
                .message(request.getMessage())
                .build();
        NetGsmSmsPayload payload = NetGsmSmsPayload.builder()
                .header(header)
                .body(body)
                .build();
        // Construct the XML payload
        String xmlPayload;
        try {
            xmlPayload = buildXmlPayload(payload);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("https://api.netgsm.com.tr/sms/send/xml").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/xml");
            try (OutputStream os = connection.getOutputStream()) {
                os.write(xmlPayload.getBytes(StandardCharsets.UTF_8));
            }

            try (InputStream is = connection.getInputStream()) {
                return parseResponse(is);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to send SMS", e);
        }
    }

    String buildXmlPayload(NetGsmSmsPayload payload) throws JAXBException {
        StringWriter sw = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(NetGsmSmsPayload.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(payload, sw);
        return sw.toString();
    }

    private NetGsmSmsResponse parseResponse(InputStream responseStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream, StandardCharsets.UTF_8))) {
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            String response = responseBuilder.toString();
            System.out.println(response);

            NetGsmErrorCode errorCode = NetGsmErrorCode.fromCode(response);
            if (errorCode != null) {
                return NetGsmSmsResponse.failed(errorCode.getCode(), errorCode.getDescription());
            }

            return NetGsmSmsResponse.success(response);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse response from NetGSM", e);
        }
    }

}
