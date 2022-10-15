package controller;

import dto.PlaylistDTO;
import entities.Song;
import repository.PlaylistRepository;
import repository.UserRepository;
import userCase.PlaylistService;
import userCase.UserService;
import java.util.Scanner;

public class AddSongController {
    PlaylistRepository playlistRepository;
    UserRepository userRepository;
    //this controller works with a playlistRepository;
    //because we have a memory repository, we must initialize it
    // outside the controller, otherwise its state is lost
    public AddSongController(PlaylistRepository playlistRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
    }

    public void run() {
        String userInput;
        do
        {
            System.out.println("Add song to Playlist");
            Scanner sc = new Scanner(System.in);

            System.out.println("--------------------------------------");

            System.out.println("Titre : For Me Formidable");
            System.out.println("Auteur : Charles Aznavour");
            System.out.println("Album : QUI ?");
            Song song1 = new Song("Titre : For Me Formidable","Auteur : Charles Aznavour","Album : QUI ?");

            System.out.println("--------------------------------------");

            System.out.println("Titre : Non, je ne regrette rien");
            System.out.println("Auteur : Edith Piaf");
            System.out.println("Album : Eternelle");
            Song song2 = new Song("Titre : Non, je ne regrette rien","Auteur : Edith Piaf","Album : Eternelle");

            System.out.println("--------------------------------------");

            System.out.println("Titre : Billie Jean");
            System.out.println("Auteur : Michael Jackson");
            System.out.println("Album : Thriller");
            Song song3 = new Song("Titre : Billie Jean","Auteur : Michael Jackson","Album : Thriller");

            System.out.println("--------------------------------------");

            System.out.println("Titre : Blinding Ligths");
            System.out.println("Auteur : The Weeknd");
            System.out.println("Album : After Hours");
            Song song4 = new Song("Titre : Blinding Ligths","Auteur : The Weeknd","Album : After Hours");

            System.out.println("--------------------------------------");

            System.out.println("Titre : Somewhere Only We Know");
            System.out.println("Auteur : Keane");
            System.out.println("Album : Hopes And Fears");
            Song song5 = new Song("Titre : Somewhere Only We Know","Auteur : Keane","Album : Hopes And Fears");

            System.out.println("--------------------------------------");

            System.out.println("Enter song name to add (1,2,3,etc) or 'quit':");
            userInput = sc.nextLine();
            if (userInput.equals("quit")) return;
            String songName = userInput;

            Song songChosen = null;
            switch (userInput) {
                case "1":
                    songChosen = song1;
                    break;
                case "2":
                    songChosen = song2;
                    break;
                case "3":
                    songChosen = song3;
                    break;
                case "4":
                    songChosen = song4;
                    break;
                case "5":
                    songChosen = song5;
                    break;
                case "quit":
                    break;
            }


            System.out.println("Enter email or 'quit':");
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
            var responseMessage = playlistCreationRequestUseCase.addSongToPlaylist(songChosen, email, playlistName, this.userRepository);

            //display the response
            System.out.println(responseMessage.getMessage());
            // 4. use a Presenter to format the use case response and display it
            // Presenter also displays to its associated view

        } while (true);
    }
}
