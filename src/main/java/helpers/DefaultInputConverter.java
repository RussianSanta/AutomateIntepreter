package helpers;

public class DefaultInputConverter implements InputConverter {
    public String convert(char input) {
        return String.valueOf(input);
    }
}
