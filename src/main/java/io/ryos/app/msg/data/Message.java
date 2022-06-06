package io.ryos.app.msg.data;

public class Message {
    private final String user;
    private final String message;
    private final long timestamp;

    public Message(String user, String message, long timestamp) {
        this.user = user;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
