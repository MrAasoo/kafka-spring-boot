package com.aasoo.kafka.dto;

import lombok.*;

/**
 * Data Transfer Object representing a chat message.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    /*
   {
    "sender": "Alice",
    "content": "Hello, World!"
   }
    * */
    private String sender;
    private String content;
    private Long timestamp;
}
