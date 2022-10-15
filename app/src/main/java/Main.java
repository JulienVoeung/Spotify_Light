import controller.*;
import repository.PlaylistRepository;
import repository.UserRepository;

public class Main {
    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        PlaylistRepository playlistRepository = new PlaylistRepository();

        UserCreationController creationController = new UserCreationController(userRepository);
        PlaylistCreationController playlistCreationController = new PlaylistCreationController(playlistRepository,userRepository);
        AddSongController addSongController = new AddSongController(playlistRepository,userRepository);
        DeleteSongController deleteSongController = new DeleteSongController(playlistRepository,userRepository);
        DeletePlaylistController deletePlaylistController = new DeletePlaylistController(playlistRepository,userRepository);
        SharePlaylistController sharePlaylistController = new SharePlaylistController(playlistRepository,userRepository);

        ControllerFacade controllerFacade = new ControllerFacade(creationController, playlistCreationController, addSongController, deleteSongController, deletePlaylistController, sharePlaylistController);
        controllerFacade.run();
    }
}
