package network;

import java.util.BitSet;

public class Generator {

    private String generatorFn;

    public Generator(String generatorFn) {
        this.generatorFn = generatorFn;
    }




    private String calculateRemainder(String x){
        BitSet Message =new BitSet();
        BitSet generatorBitset =new BitSet();
        int G_length=generatorFn.length();
        int M_length=x.length();
        String Rem="";
        // convert msg string 2 bitset
        for(int i=0;i<M_length;i++)
            if(x.charAt(i)=='1')
                Message.set(M_length-i-1);

        // convert generator string 2 bitset
        for(int i=0;i<G_length;i++)
            if(generatorFn.charAt(i)=='1')
                generatorBitset.set(M_length-i-1);

        // xor operation

        while(Message.length()>=G_length)
        {
            if(Message.length()==generatorBitset.length())
                Message.xor(generatorBitset);

            for(int i=0;i<generatorBitset.length();i++)
                generatorBitset.set(i,generatorBitset.get(i+1));

        }
        // convert reminder bit set  2 string
         for(int i=1;i<G_length;i++)
            if(Message.get(G_length-i-1))
                Rem=Rem+"1";
            else
                Rem=Rem+"0";



        return Rem;
    }

    public boolean verify(String messageString) {
        String remainder = calculateRemainder(messageString);

        return Integer.valueOf(remainder)==0;
    }

    public String generateMessage(String message) {
        String zero = "0";
        String remainder = calculateRemainder(message+new String(new char[generatorFn.length()-1]).replace("\0", zero));
        return message+remainder;
    }
}
