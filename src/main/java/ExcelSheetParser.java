import automations.Automaton;
import automations.State;
import automations.impls.AutomatonImpl;
import automations.impls.StateImpl;
import helpers.ExcelReader;
import helpers.InputConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.ArrayList;
import java.util.HashMap;

public class ExcelSheetParser {
    InputConverter inputConverter;

    public ExcelSheetParser() {
    }

    public ExcelSheetParser(InputConverter inputConverter) {
        this.inputConverter = inputConverter;
    }

    public Automaton parse(String filePath) {
        int countOfInputs;
        int countOfStates;
        XSSFSheet sheet = ExcelReader.readFile(filePath);
        ArrayList<StateImpl> states = new ArrayList<>();

        countOfInputs = sheet.getRow(0).getLastCellNum() - 3;
        countOfStates = sheet.getLastRowNum() - 1;

        for (int i = 0; i < countOfStates; i++) {
            XSSFRow row = sheet.getRow(i + 2);
            String name = row.getCell(1).toString();
            String description = row.getCell(0).toString();
            StateImpl state;

            if (inputConverter == null) {
                state = new StateImpl(name, description);
            } else {
                state = new StateImpl(name, description, inputConverter);
            }
            states.add(state);
        }

        for (State state : states) {
            XSSFRow stateRow = sheet.getRow(states.indexOf(state) + 2);
            XSSFRow inputsRow = sheet.getRow(0);

            HashMap<String, String> transitionsMap = new HashMap<>();
            for (int i = 0; i < countOfInputs; i++) {
                int columnNumber = i + 3;
                String input = inputsRow.getCell(columnNumber).toString();
                String cellValue = stateRow.getCell(columnNumber).toString().split("\\.")[0];

                transitionsMap.put(input, cellValue);
            }

            state.addTransitions(transitionsMap);
        }

        return new AutomatonImpl(states);
    }
}
