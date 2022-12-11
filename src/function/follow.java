package function;

import chatting.protocol;
import chatting.chatting_client;

public class follow {
    public follow(String user_id, String following, chatting_client client){
        protocol p = new protocol(7, user_id, following);
        client.chat_message(p);
    }
}
