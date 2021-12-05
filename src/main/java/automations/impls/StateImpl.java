package automations.impls;

import automations.State;
import helpers.DefaultInputConverter;
import helpers.InputConverter;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class StateImpl implements State {
    private final String stateName;
    private final String description;
    private final InputConverter inputConverter;
    private HashMap<String, String> transitions;

    public StateImpl(String stateName, String description) {
        this.stateName = stateName;
        this.description = description;
        this.inputConverter = new DefaultInputConverter();
    }

    public StateImpl(String stateName, String description, InputConverter customConverter) {
        this.stateName = stateName;
        this.description = description;
        this.inputConverter = customConverter;
    }

    @Override
    public void addTransitions(HashMap<String, String> transitionMap) {
        transitions = transitionMap;
    }

    @Override
    public String executeNext(char input) {
        String key = inputConverter.convert(input);
        return transitions.get(key);
    }
}
