package network;

public class Message {
    private String messageString;

    public Message(String messageString) {
        this.messageString = messageString;
    }
    public boolean alter(int position){
        //receive indexing from one
        position--;

        if(position>= messageString.length())
            return false;
        if(messageString.charAt(position)=='0'){
            messageString = messageString.substring(0,position)+'1'+messageString.substring(position+1);
        }
        else{
            messageString = messageString.substring(0,position)+'0'+messageString.substring(position+1);
        }


        return true;
    }

    public String getMessageString() {
        return messageString;
    }

    public void setMessageString(String messageString) {
        this.messageString = messageString;
    }
}
