# NetGSM Java SDK

A Java SDK for interacting with the NetGSM SMS API. This library provides a clean and simple interface for sending SMS messages, handling single message to multiple recipients (1-to-N), and preparing for multiple message scenarios.

## Features

- Send a single SMS message to multiple recipients.
- Ready to handle multi-message (N-to-N) scenarios.
- Flexible design for easy extension to other NetGSM API features.
- Lightweight, with minimal dependencies.

---

## Installation

Include the SDK in your Maven or Gradle project.

### Maven

```xml
<dependency>
    <groupId>io.netgsm.sdk</groupId>
    <artifactId>netgsm-java-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'io.netgsm.sdk:netgsm-java-sdk:1.0.0'
```

---

## Quick Start

### Create the `NetGsmHttpClient`

The `NetGsmHttpClient` is the main class used to send SMS messages. Initialize it with your NetGSM credentials:

```java
NetGsmClient client = new NetGsmHttpClient("yourUserCode", "yourPassword", "yourCompany");
```

---

### Example: Send a Single Message to Multiple Recipients (1-to-N)

```java
import io.netgsm.sdk.client.*;
import io.netgsm.sdk.dto.*;

import java.util.List;

public class SmsExample {
    public static void main(String[] args) {
        // Initialize the NetGSM client
        NetGsmClient client = new NetGsmHttpClient("yourUserCode", "yourPassword", "yourCompany");

        // Create the SMS request
        NetGsmSmsRequest request = NetGsmSmsRequest.builder()
                .title("MyApp") // Static SMS title configured in NetGSM
                .message("Hello from NetGSM Java SDK!")
                .telephoneNumbers(List.of("1234567890", "9876543210"))
                .build();

        // Send the SMS
        NetGsmSmsResponse response = client.sendSingleMessage(request);

        // Handle the response
        if (response.isSuccess()) {
            System.out.println("Message sent successfully: " + response.getMessage());
        } else {
            System.out.println("Failed to send message. Error Code: " + response.getErrorCode() +
                    ", Error Message: " + response.getErrorMessage());
        }
    }
}
```

---

## API Reference

### **Classes**

#### `NetGsmHttpClient`

Main client for sending SMS. Implements the `NetGsmClient` interface.

#### `NetGsmSmsRequest`

A DTO for creating SMS requests.

- `title`: The static title for your SMS (configured in NetGSM).
- `message`: The message content.
- `telephoneNumbers`: List of recipient phone numbers.

#### `NetGsmSmsResponse`

A DTO for handling SMS responses.

- `isSuccess()`: Returns `true` if the SMS was sent successfully.
- `getMessage()`: Contains a success message or additional details.
- `getErrorCode()`: Contains the error code if the message failed.
- `getErrorMessage()`: Contains the error description.

---

## Error Handling

Error codes from NetGSM are mapped and processed in the SDK. Some common error codes:

- `00`: Success
- `70`: Invalid request format
- `401`: Unauthorized access
- `500`: Internal server error

For additional error codes, see the official [NetGSM API Documentation](https://www.netgsm.com.tr/).

---

## Configuration

### Logging

The SDK does not include a built-in logger. You can wrap the `NetGsmHttpClient` for additional logging.

---

## Contributing

We welcome contributions! Please fork the repository and submit a pull request. See the `CONTRIBUTING.md` file for more details.

---

## License

This library is licensed under the MIT License. See the LICENSE file for details.

---

## Contact

For questions, issues, or feature requests, please open an issue on the GitHub repository.