package automations;

import java.util.HashMap;

public interface State {
    void addTransitions(HashMap<String, String> transitionMap);

    String executeNext(char input);
}
