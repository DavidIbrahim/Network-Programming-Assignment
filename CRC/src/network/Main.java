package network;
import java.util.BitSet;
public class Main {

public static String getRemainder(String msg,String generator)
    {
        BitSet Message =new BitSet();
        BitSet Generator =new BitSet();
        int G_length=generator.length();
        int M_length=msg.length();
        String Rem="";
        // convert msg string 2 bitset
        for(int i=0;i<M_length;i++)
            if(msg.charAt(i)=='1')
                Message.set(M_length+G_length-i-2);
        
        // convert generator string 2 bitset
        for(int i=0;i<G_length;i++)
            if(generator.charAt(i)=='1')
                Generator.set(M_length+G_length-i-2);
        
        // xor operation
        
       while(Message.length()>=G_length)
        {
            if(Message.length()==Generator.length())
                Message.xor(Generator);
            
            for(int i=0;i<Generator.length();i++)
            Generator.set(i,Generator.get(i+1));
            
        }
        // convert reminder bit set  2 string
        for(int i=0;i<Message.length();i++)
            if(Message.get(Message.length()-i-1))
                Rem=Rem+"1";
            else
                Rem=Rem+"0";
        
                
        
    return Rem;}


    public static void main(String[] args) {
        System.out.println("David is here");
    }
}
