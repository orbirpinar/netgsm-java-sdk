package io.netgsm.sdk.client;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "mainbody")
@XmlAccessorType(XmlAccessType.FIELD)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
class NetGsmSmsPayload {

    @XmlElement(name = "header")
    private Header header;

    @XmlElement(name = "body")
    private Body body;

    @XmlAccessorType(XmlAccessType.FIELD)
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Header {

        @XmlElement(name = "company")
        private String company;

        @XmlElement(name = "usercode")
        private String userCode;

        @XmlElement(name = "password")
        private String password;

        @XmlElement(name = "type")
        private String type;

        @XmlElement(name = "msgheader")
        private String messageHeader;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Body {

        @XmlElement(name = "msg")
        private String message;

        @XmlElement(name = "no")
        private String[] telephoneNumbers;
    }
}
