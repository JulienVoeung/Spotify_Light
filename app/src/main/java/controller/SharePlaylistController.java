package controller;

import repository.PlaylistRepository;
import repository.UserRepository;
import userCase.PlaylistService;
import java.util.Scanner;

public class SharePlaylistController {
    PlaylistRepository playlistRepository;
    UserRepository userRepository;
    //this controller works with a playlistRepository;
    //because we have a memory repository, we must initialize it
    // outside the controller, otherwise its state is lost
    public SharePlaylistController(PlaylistRepository playlistRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
    }

    public void run() {
        String userInput;
        do
        {
            System.out.println("Share Playlist");
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter email of your friend or 'quit':");
            userInput = sc.nextLine();
            if (userInput.equals("quit")) return;
            String email = userInput;

            System.out.println("Enter playlist name to add or 'quit':");
            userInput = sc.nextLine();
            if (userInput.equals("quit")) return;
            String playlistName = userInput;

            //*************************************************************************************************
            // connecting the app with the Use Case Interactor
            //*************************************************************************************************

            //instantiate Contest Registration Use Case injecting gateways
            //the repository cannot be instantiated here because it would be emptied at each user input
            //so I put it as a private attribute and instantiate it in main
            var playlistCreationRequestUseCase = new PlaylistService(this.playlistRepository);


            //call the use case and store the response
            var responseMessage = playlistCreationRequestUseCase.sharePlaylist(email, this.userRepository, playlistName);

            //display the response
            System.out.println(responseMessage.getMessage());
            // 4. use a Presenter to format the use case response and display it
            // Presenter also displays to its associated view

        } while (true);
    }
}
