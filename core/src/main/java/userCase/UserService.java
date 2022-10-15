package userCase;

import dto.UserDTO;
import entities.User;
import error.ValidationError;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> listAllUser(){
        return this.userRepository.listUser();
    }

    public void createUser(UserDTO userDTO){
        List<ValidationError> validationErrors = validate(userDTO);
        if (!validationErrors.isEmpty()) {
            //throw new ValidationErrorException(validationErrors);
        }

        User newUser = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getPseudo(), userDTO.getEmail(), userDTO.getDateBirth());
    }

    private List<ValidationError> validate(UserDTO userDTO) {
        List<ValidationError> listError = new ArrayList<>();

        if (userDTO.getFirstName() == null || userDTO.getFirstName().equals("") || userDTO.getFirstName().equals("null")) {
            ValidationError e1 = new ValidationError("First Name is missing");
            listError.add(e1);
        }
        if (userDTO.getLastName() == null || userDTO.getLastName().equals("") || userDTO.getLastName().equals("null")) {
            ValidationError e2 = new ValidationError("Last Name is missing");
            listError.add(e2);
        }
        if (userDTO.getPseudo() == null || userDTO.getPseudo().equals("") || userDTO.getPseudo().equals("null")) {
            ValidationError e3 = new ValidationError("Pseudo is missing");
            listError.add(e3);
        }
        if (userDTO.getEmail() == null || userDTO.getEmail().equals("") || userDTO.getEmail().equals("null")) {
            ValidationError e4 = new ValidationError("Email is missing");
            listError.add(e4);
        }
        if (userDTO.getDateBirth() == null || userDTO.getDateBirth().equals("") || userDTO.getDateBirth().equals("null")) {
            ValidationError e5 = new ValidationError("Date birth is missing");
            listError.add(e5);
        }

        return listError;
    }
}
