package network;

public class Generator {

    private String generatorFn;

    public Generator(String generatorFn) {
        this.generatorFn = generatorFn;
    }
    public String calculateRemainder(String x){
        return null;
    }

    public boolean verify(String messageString) {
        String remainder = calculateRemainder(messageString);

        return Integer.valueOf(remainder)==0;
    }
}
