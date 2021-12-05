package helpers;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class DefaultAutomateListener implements AutomateListener {
    private String resultListening = "";

    @Override
    public void execute(String stateEvent) {
        resultListening += stateEvent + ";\n";
    }

    @Override
    public void getResult(String filePath) {
        try {
            PrintWriter writer = new PrintWriter(filePath, StandardCharsets.UTF_8);
            writer.println(resultListening);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
