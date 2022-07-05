package com.y2829.whai.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ChatMessage implements Serializable {

    public enum Type {
        ENTER, TALK, QUIT
    }

    private Type type;
    private String roomId;
    private String sender;
    private String message;

}
