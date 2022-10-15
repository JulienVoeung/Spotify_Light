package presenter;

import dto.Response;
import error.ValidationError;
import view.View;

public class ConsolePresenter {
    final private View consoleView;

    public ConsolePresenter() {
        consoleView = new View();
    }
    public void handleResponse(Response responseMessage) {
        if (responseMessage.getSuccess()) {
            consoleView.display(responseMessage.getMessage());
        }
        else {
            StringBuilder stringBuilder = new StringBuilder();
            for (ValidationError error : responseMessage.getErrors()) {
                stringBuilder.append(error.getMessage());
                stringBuilder.append("\n");
            }
            consoleView.display(stringBuilder.toString());
        }
    }
}
