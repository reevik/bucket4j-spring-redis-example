package io.ryos.app.msg.repo;

import io.ryos.app.msg.data.Message;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class MessageRepository {

    public List<Message> getMessageListFor(String userId) {
        Message msg = new Message("notingolmo", "Hello world!", 0L);
        return Collections.singletonList(msg);
    }
}
