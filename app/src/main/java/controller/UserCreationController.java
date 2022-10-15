package controller;

import dto.UserDTO;
import repository.PlaylistRepository;
import repository.UserRepository;
import userCase.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserCreationController {
    UserRepository userRepository;
    PlaylistRepository playlistRepository;
    //this controller works with a userRepository;
    //because we have a memory repository, we must initialize it
    // outside the controller, otherwise its state is lost
    public UserCreationController(UserRepository userRepository) {

        this.userRepository = userRepository;
        this.playlistRepository = new PlaylistRepository();
    }

    public void run() {
        String userInput;
        do
        {
            System.out.println("User Creation");
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter first name or 'quit':");
            userInput = sc.nextLine();
            if (userInput.equals("quit")) return;
            String firstName = userInput;

            System.out.println("Enter last name or 'quit':");
            userInput = sc.nextLine();
            if (userInput.equals("quit")) return;
            String lastName = userInput;

            System.out.println("Enter username or 'quit':");
            userInput = sc.nextLine();
            if (userInput.equals("quit")) return;
            String pseudo = userInput;

            System.out.println("Enter email or 'quit':");
            userInput = sc.nextLine();
            if (userInput.equals("quit")) return;
            String email = userInput;

            System.out.println("Enter birth of date as yyyy-MM-dd or 'quit':");
            userInput = sc.nextLine();
            if (userInput.equals("quit")) return;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthOfDate = LocalDate.now();
            try {
                birthOfDate = LocalDate.parse(userInput, formatter);
            } catch (DateTimeParseException ex) {
                ex.printStackTrace();
                //TODO try again
            }


            //*************************************************************************************************
            // connecting the app with the Use Case Interactor
            //*************************************************************************************************
            // 1. create the request message passing with the target user
            var userRequestMessage = new UserDTO(firstName, lastName, pseudo, email, birthOfDate);

            // 2. instantiate Contest Registration Use Case injecting gateways
            //the repository cannot be instantiated here because it would be emptied at each user input
            //so I put it as a private attribute and instantiate it in main
            var userCreationRequestUseCase = new UserService(this.userRepository);


            // 3. call the use case and store the response
            var responseMessage = userCreationRequestUseCase.createUser(userRequestMessage);

            //display the response
            System.out.println(responseMessage.getMessage());
            // 4. use a Presenter to format the use case response and display it
            // Presenter also displays to its associated view

        } while (true);
    }
}
