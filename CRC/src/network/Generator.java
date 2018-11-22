package network;

import java.util.BitSet;

public class Generator {

    private String generatorFn;

    public Generator(String generatorFn) {
        this.generatorFn = generatorFn;
    }




    private String calculateRemainder(String generatorString,String messageString){
        BitSet messageBitSet =new BitSet();
        BitSet generatorBitset =new BitSet();
        int generatorLength=generatorString.length();
        int messageLength=messageString.length();
        String remainder="";



        // convert msg string 2 bitset
        for(int i=0;i<messageLength;i++)
            if(messageString.charAt(i)=='1')
                messageBitSet.set(messageLength-i-1);

        // convert generator string 2 bitset
        for(int i=0;i<generatorLength;i++)
            if(generatorString.charAt(i)=='1')
                generatorBitset.set(messageLength-i-1);

        // xor operation

        while(messageBitSet.length()>=generatorLength)
        {
            if(messageBitSet.length()==generatorBitset.length())
                messageBitSet.xor(generatorBitset);

            for(int i=0;i<generatorBitset.length();i++)
                generatorBitset.set(i,generatorBitset.get(i+1));

        }
        // convert reminder bit set  2 string
         for(int i=1;i<generatorLength;i++)
            if(messageBitSet.get(generatorLength-i-1))
                remainder=remainder+"1";
            else
                remainder=remainder+"0";



        return remainder;
    }


    /**
     *  used by the receiver of the message to verify it's correct or no
     * @param messageString
     * @return true if there's no error in message
     */

    public boolean verify(String messageString) {
        String remainder = calculateRemainder(generatorFn,messageString);

        return Integer.valueOf(remainder)==0;
    }

    /**
     * used by sender to produce the message to be sent after adding the remainder of the generator
     *
     */

    public String generateMessage(String message) {
        String zero = "0";
        String messageWithZerosConcatenated = message+new String(new char[generatorFn.length()-1]).replace("\0", zero);
        String remainder = calculateRemainder(generatorFn,messageWithZerosConcatenated);
        return message+remainder;
    }
}
