package network;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;
import java.util.Scanner;

public class Main {

    private static NetworkClient sender = new NetworkClient();
    private static NetworkClient receiver = new NetworkClient();

    public static void main(String[] args) throws IOException {
        printUsage();
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        String message = new String();
        String generator = new String();
        while (!command.equals("exit")) {
            String[] commandTokens = command.split(" ");
            if(commandTokens[0].equals("generate")){
                File inputFile = new File(commandTokens[1]);
                String fileInput = readFile(new File(inputFile.getAbsolutePath()));
                // assuming input file will have no errors
                String [] fileInputTokens = fileInput.split("\n");
                message = fileInputTokens[0];
                generator = fileInputTokens[1];
                generate(message, generator);
            }
            else if(commandTokens[0].equals("verify")){
                verify();
            }
            else if(commandTokens[0].equals("alter")){
                try {
                    int index = Integer.parseInt(commandTokens[1]);
                    //alter
                    sender.getMessageToBeSent().alter(index);
                } catch (NumberFormatException e) {
                    System.out.println(commandTokens[1]+" is not a valid index");
                }
            }
            else{
                System.out.println(commandTokens[0]+" is not a valid command!");
            }
            command = input.nextLine();
        }
    }
    public static String  getrem (String generator,String msg)
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

    private static void generate(String message, String generator) {
        Generator g= new Generator(generator);
        sender.setGenerator(g);
        sender.connectTo(receiver);
        System.out.println("Transmitted Message : "+sender.setMessageToBeSent(message));
    }

    public static void verify(){
        boolean sentSuccessfully = sender.sendMessage();
        if(sentSuccessfully){
            System.out.println("Message is sent correct");
        }
        else {
            System.out.println("Message is not correct");
        }
    }
    public static void printUsage(){
        System.out.println("usage: ");
        System.out.println("generate  inputFilePath               (use this command to generate message to be sent)");
        System.out.println("verify                                (use this command to verify message sent)");
        System.out.println("alter indexToAlter                    (use this command to alter message to be sent)");
        System.out.println("exit                                  (use this command to exit)");
    }
    public static String readFile(File fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        }
        finally {
            br.close();
        }
    }
}
