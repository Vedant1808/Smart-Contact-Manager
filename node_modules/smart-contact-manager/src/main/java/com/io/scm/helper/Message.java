package com.io.scm.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// For message field
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    private String content;
    @Builder.Default
    private MessageType type = MessageType.blue;
}
