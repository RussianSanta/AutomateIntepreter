package automations;

import automations.impls.StateImpl;

import java.util.ArrayList;

public interface Automaton {
    String execute(String input);

    void getLogs(String filePath);

    ArrayList<StateImpl> getStates();
}
