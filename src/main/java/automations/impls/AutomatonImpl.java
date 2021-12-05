package automations.impls;

import automations.Automaton;
import helpers.AutomateListener;
import helpers.DefaultAutomateListener;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class AutomatonImpl implements Automaton {
    private final AutomateListener listener;
    private final ArrayList<StateImpl> states;
    private String message = "";

    public AutomatonImpl(ArrayList<StateImpl> states) {
        this.states = states;
        this.listener = new DefaultAutomateListener();
    }

    public AutomatonImpl(AutomateListener listener, ArrayList<StateImpl> states) {
        this.listener = listener;
        this.states = states;
    }

    @Override
    public String execute(String inputSting) {
        String resultMessage;
        char[] chars = inputSting.toCharArray();
        StateImpl currentState = states.get(0);
        StateImpl nextState = states.get(0);
        int step = 0;

        for (char aChar : chars) {
            currentState = nextState;
            step++;
            String nextStepValue = currentState.executeNext(aChar);
            listener.execute("Итерация - " + step + ". Текущий шаг - " + currentState.getStateName()
                    + ". Входное значение - " + aChar + ". Выходное значение - " + nextStepValue);
            if (nextStepValue.equals("#")) {
                resultMessage = "Шаг на котором автомат завершил свое выполнение - " + currentState.getStateName() + "\n";
                resultMessage += "Результат выполнения - No\n";
                if (!message.equals("")) {
                    resultMessage += "Полученное сообщение - " + message;
                }
                return resultMessage;
            }
            if (nextStepValue.contains(",")) {
                String[] values = nextStepValue.split(",");
                nextState = states.get(Integer.parseInt(values[0]) - 1);
                message += values[1];
            } else {
                nextState = states.get(Integer.parseInt(nextStepValue) - 1);
            }

        }

        resultMessage = "Шаг на котором автомат завершил свое выполнение - " + currentState.getStateName() + "\n";
        if (!currentState.getDescription().equals("")) {
            resultMessage += "Результат выполнения - " + currentState.getDescription() + "\n";
        }
        if (!message.equals("")) {
            resultMessage += "Полученное сообщение - " + message + "\n";
        }
        return resultMessage;
    }

    @Override
    public void getLogs(String filePath) {
        listener.getResult(filePath);
    }

    @Override
    public ArrayList<StateImpl> getStates() {
        return states;
    }
}
