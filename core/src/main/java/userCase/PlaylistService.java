package userCase;

import dto.PlaylistDTO;
import dto.Response;
import entities.Playlist;
import entities.Song;
import entities.User;
import error.ValidationError;
import repository.PlaylistRepository;
import repository.UserRepository;
import java.util.ArrayList;
import java.util.List;

public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository){
        this.playlistRepository = playlistRepository;
    }

    public List<Playlist> listAllPlaylist(){
        return this.playlistRepository.listPlaylist();
    }

    public List<Playlist> listAllPlaylistOfUser(User user){
        return this.playlistRepository.getPlaylistByUser(user);
    }

    //Use case create a playlist
    public Response createPlaylist(PlaylistDTO playlistDTO, UserRepository userRepository, String email){

        //Verify errors about field are all filled and correct
        List<ValidationError> validationErrors = validateCreate(playlistDTO);
        if (!validationErrors.isEmpty()) {
            Response responseError = new Response(false, "Error of validation on creation of playlist", validationErrors);
            return responseError;
        }

        //Verify errors about searching if user exist (playlist is link to this user)
        List<ValidationError> validationErrorsForSearchUser = validateSearchUser(userRepository, email);
        if (!validationErrorsForSearchUser.isEmpty()) {
            Response responseError = new Response(false, "Error of search user", validationErrorsForSearchUser);
            return responseError;
        }

        //Getting the user by his email
        User user = userRepository.getUserByEmail(email);

        //Creation of user and delete it before add it (for no double)
        Playlist newPlaylist = new Playlist(playlistDTO.getNamePlaylist(), user);
        this.playlistRepository.deletePlaylist(newPlaylist);
        this.playlistRepository.addPlaylist(newPlaylist);

        //Response validation
        Response responseValidate = new Response(true, "Validation on creation of playlist", validationErrors);
        return responseValidate;
    }

    //Use case add song to a playlist
    public Response addSongToPlaylist(Song song, String email, String namePlaylist, UserRepository userRepository){

        //Verify errors about searching if playlist exist
        List<ValidationError> validationErrors = validateSearchPlaylist(namePlaylist);
        if (!validationErrors.isEmpty()) {
            Response responseError = new Response(false, "Error of search the playlist", validationErrors);
            return responseError;
        }

        //Verify errors about searching if user exist (playlist is link to this user)
        List<ValidationError> validationErrorsForSearchUser = validateSearchUser(userRepository, email);
        if (!validationErrorsForSearchUser.isEmpty()) {
            Response responseError = new Response(false, "Error of search user", validationErrorsForSearchUser);
            return responseError;
        }

        //Getting the user by his email
        User user = userRepository.getUserByEmail(email);

        //Getting the playlist by the name of playlist associate to user
        Playlist p = this.playlistRepository.getOnePlaylistByName(namePlaylist, user);

        //Add song to the playlist
        p.addSongToPlaylist(song);

        //Response validation
        Response responseValidate = new Response(true, "Validation on add song to playlist", validationErrors);
        return responseValidate;
    }

    //Use case delete song from a playlist
    public Response deleteSongFromPlaylist(Song song, String email, String namePlaylist, UserRepository userRepository){

        //Verify errors about searching if playlist exist
        List<ValidationError> validationErrorsForSearchPlaylist = validateSearchPlaylist(namePlaylist);
        if (!validationErrorsForSearchPlaylist.isEmpty()) {
            Response responseError = new Response(false, "Error of search the playlist", validationErrorsForSearchPlaylist);
            return responseError;
        }

        //Verify errors about searching if song exist in playlist
        List<ValidationError> validationErrorsForSongInPlaylist = validateSongIsInPlaylist(namePlaylist, song);
        if (!validationErrorsForSongInPlaylist.isEmpty()) {
            Response responseError = new Response(false, "Error of search song in the playlist", validationErrorsForSongInPlaylist);
            return responseError;
        }

        //Verify errors about searching if user exist (playlist is link to this user)
        List<ValidationError> validationErrorsForSearchUser = validateSearchUser(userRepository, email);
        if (!validationErrorsForSearchUser.isEmpty()) {
            Response responseError = new Response(false, "Error of search user", validationErrorsForSearchUser);
            return responseError;
        }

        //Getting the playlist by the name of playlist and the user
        //AND THEN
        //Delete the song from the playlist
        this.playlistRepository.getPlaylistByName(namePlaylist).deleteSongFromPlaylist(song);

        //Response validation
        Response responseValidate = new Response(true, "Validation on delete song from playlist", validationErrorsForSongInPlaylist);
        return responseValidate;
    }

    //Use case delete a playlist
    public Response deletePlaylist(String namePlaylist){

        //Verify errors about searching if playlist exist
        List<ValidationError> validationErrors = validateSearchPlaylist(namePlaylist);
        if (!validationErrors.isEmpty()) {
            Response responseError = new Response(false, "Error of search the playlist", validationErrors);
            return responseError;
        }

        //Getting the playlist by the name of playlist
        //AND THEN
        //Delete the playlist
        this.playlistRepository.deletePlaylist(this.playlistRepository.getPlaylistByName(namePlaylist));

        //Response validation
        Response responseValidate = new Response(true, "Validation on delete playlist", validationErrors);
        return responseValidate;
    }

    //Use case share a playlist
    public Response sharePlaylist(String email, UserRepository userRepository, String namePlaylist){

        //Verify errors about searching if user exist (playlist is link to this user)
        List<ValidationError> validationErrors = validateSearchUser(userRepository, email);
        if (!validationErrors.isEmpty()) {
            Response responseError = new Response(false, "Error of search the user", validationErrors);
            return responseError;
        }

        //Verify errors about searching if playlist exist
        List<ValidationError> validationErrorsSearchPlaylist = validateSearchPlaylist(namePlaylist);
        if (!validationErrorsSearchPlaylist.isEmpty()) {
            Response responseError = new Response(false, "Error of search the playlist", validationErrorsSearchPlaylist);
            return responseError;
        }

        //Getting the user by his email
        User user = userRepository.getUserByEmail(email);

        //Getting the playlist by the name of playlist
        //AND THEN
        //Add a user to this playlist
        this.playlistRepository.getPlaylistByName(namePlaylist).addUserToPlaylist(user);

        //Response validation
        Response responseValidate = new Response(true, "Validation on add user to playlist", validationErrors);
        return responseValidate;
    }

    //ALL VALIDATION METHODS BELOW
    private List<ValidationError> validateCreate(PlaylistDTO playlistDTO) {
        List<ValidationError> listError = new ArrayList<>();

        if (playlistDTO.getNamePlaylist() == null || playlistDTO.getNamePlaylist().equals("") || playlistDTO.getNamePlaylist().equals("null")) {
            ValidationError e1 = new ValidationError("Playlist Name is missing");
            listError.add(e1);
        }

        return listError;
    }

    private List<ValidationError> validateSearchPlaylist(String namePlaylist) {
        List<ValidationError> listError = new ArrayList<>();

        Playlist res = this.playlistRepository.getPlaylistByName(namePlaylist);
        if(res == null){
            ValidationError e1 = new ValidationError("Playlist do not exist");
            listError.add(e1);
        }

        return listError;
    }

    private List<ValidationError> validateSongIsInPlaylist(String namePlaylist, Song song) {
        List<ValidationError> listError = new ArrayList<>();

        Playlist res = this.playlistRepository.getPlaylistByName(namePlaylist);
        Song res2 = this.playlistRepository.getSongFromPlaylist(song, res.getId());
        if(res2 == null){
            ValidationError e1 = new ValidationError("Song do not exist in Playlist");
            listError.add(e1);
        }

        return listError;
    }

    private List<ValidationError> validateSearchUser(UserRepository userRepository, String email) {
        List<ValidationError> listError = new ArrayList<>();

        User res = userRepository.getUserByEmail(email);
        if(res == null){
            ValidationError e1 = new ValidationError("User do not exist");
            listError.add(e1);
        }

        return listError;
    }
}
