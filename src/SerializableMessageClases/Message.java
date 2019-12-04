package SerializableMessageClases;

import java.io.Serializable;

public class Message implements Serializable {
    private Object message;
    private String typeMessage;

    public Message(Object message, String typeMessage) {
        this.message = message;
        this.typeMessage = typeMessage;
    }

    public Message() {
        this.message = null;
        this.typeMessage = null;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(String typeMessage) {
        this.typeMessage = typeMessage;
    }
}
