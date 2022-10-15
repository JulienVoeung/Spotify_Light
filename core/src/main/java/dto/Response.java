package dto;

import error.ValidationError;

import java.util.ArrayList;
import java.util.List;

public final class Response {
    private String message;
    private boolean success;
    private List<ValidationError> errorList = new ArrayList<>();

    public Response(boolean success, String message, List<ValidationError> errors) {
        this.message = message;
        this.errorList = errors;
        this.success = success; //true or false
    }

    public void addError(ValidationError error) {
        this.errorList.add(error);
    }

    public String getMessage() {
        return message;
    }

    public boolean getSuccess() {
        return success;
    }

    public List<ValidationError> getErrors() {
        return errorList;
    }


}

