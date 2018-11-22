package network;

public class NetworkClient {

    private Generator generator;
    private Message messageToBeSent;
    private NetworkClient clientConnectedTo;

    public NetworkClient() {
    }

    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public String setMessageToBeSent(String message) {
        String remainder = generator.calculateRemainder(message);
        String messageToBeSentString = message + remainder;
        this.messageToBeSent =new Message(messageToBeSentString);
        return messageToBeSentString;
    }

    public void connectTo(NetworkClient client){
        clientConnectedTo = client;
        client.setGenerator(this.generator);
    }
    public boolean sendMessage(){

        return  clientConnectedTo.receive(messageToBeSent);

    }

    private boolean receive(Message message) {
       return  generator.verify(message.getMessageString());

    }

    public Message getMessageToBeSent() {
        return messageToBeSent;
    }
}
