package io.netgsm.sdk.client;

import lombok.Getter;

@Getter
 enum NetGsmErrorCode {
    CODE_20("20", "Message failed due to content issues or exceeding character limit (max 917 chars)."),
    CODE_30("30", "Invalid username, password, or API access denied."),
    CODE_40("40", "Message header not registered."),
    CODE_50("50", "IYS controlled sends are not allowed for this account."),
    CODE_51("51", "IYS brand information is missing for this account."),
    CODE_70("70", "Incorrect or missing parameters in request."),
    CODE_80("80", "Message send limit exceeded."),
    CODE_85("85", "Duplicate send limit exceeded; cannot create more than 20 tasks per minute to the same number."),
    CODE_100("100","System error"),
    CODE_101("101","System error");

    private final String code;
    private final String description;

    NetGsmErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    static NetGsmErrorCode fromCode(String code) {
        for (NetGsmErrorCode errorCode : values()) {
            if (errorCode.code.equals(code)) {
                return errorCode;
            }
        }
        return null;
    }
}
