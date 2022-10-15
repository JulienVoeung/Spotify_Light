package controller;

import java.util.Scanner;

public class ControllerFacade {
    UserCreationController creationController;
    PlaylistCreationController playlistCreationController;
    AddSongController addSongController;
    DeleteSongController deleteSongController;
    DeletePlaylistController deletePlaylistController;
    SharePlaylistController sharePlaylistController;

    public ControllerFacade(UserCreationController creationController ,PlaylistCreationController playlistCreationController, AddSongController addSongController, DeleteSongController deleteSongController, DeletePlaylistController deletePlaylistController, SharePlaylistController sharePlaylistController) {
        this.creationController = creationController;
        this.playlistCreationController = playlistCreationController;
        this.addSongController = addSongController;
        this.deleteSongController = deleteSongController;
        this.deletePlaylistController = deletePlaylistController;
        this.sharePlaylistController = sharePlaylistController;
    }

    //controller handles user input and decides what to do with it
    public void run()  {
        //get user input

        String userInput;
        do
        {
            System.out.println("Welcome on Spotify Light");
            System.out.println("You might have a user account to use this application");
            System.out.println("Select one operation (1 or 2) or quit:");
            System.out.println("1. User Creation");
            System.out.println("2. Playlist Creation");
            System.out.println("3. Add song to a Playlist");
            System.out.println("4. Delete song from a Playlist");
            System.out.println("5. Delete a playlist");

            Scanner sc = new Scanner(System.in);
            userInput = sc.nextLine();

            switch (userInput) {
                case "1":
                    creationController.run();
                    break;
                case "2":
                    playlistCreationController.run();
                    break;
                case "3":
                    addSongController.run();
                    break;
                case "4":
                    deleteSongController.run();
                    break;
                case "5":
                    deletePlaylistController.run();
                    break;
                case "6":
                    sharePlaylistController.run();
                    break;
                case "quit":
                    break;
            }

        } while (!userInput.equals("quit"));
    }
}
