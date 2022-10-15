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
import java.util.UUID;

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

    public Response createPlaylist(PlaylistDTO playlistDTO, UserRepository userRepository, String email){
        List<ValidationError> validationErrors = validateCreate(playlistDTO);
        if (!validationErrors.isEmpty()) {
            //throw new ValidationErrorException(validationErrors);
            Response responseError = new Response(false, "Error of validation on creation of playlist", validationErrors);
            return responseError;
        }

        User user = userRepository.getUserByEmail(email);

        Playlist newPlaylist = new Playlist(playlistDTO.getNamePlaylist(), user);
        this.playlistRepository.deletePlaylist(newPlaylist);
        this.playlistRepository.addPlaylist(newPlaylist);

        Response responseValidate = new Response(true, "Validation on creation of playlist", validationErrors);
        return responseValidate;
    }

    public Response addSongToPlaylist(Song song, String email, String namePlaylist, UserRepository userRepository){

        List<ValidationError> validationErrors = validateSearchPlaylist(namePlaylist);
        if (!validationErrors.isEmpty()) {
            //throw new ValidationErrorException(validationErrors);
            Response responseError = new Response(false, "Error of search the playlist", validationErrors);
            return responseError;
        }

        User user = userRepository.getUserByEmail(email);
        Playlist p = this.playlistRepository.getOnePlaylistByName(namePlaylist, user);
        p.addSongToPlaylist(song);
        //this.playlistRepository.getPlaylistById(idPlaylist).addSongToPlaylist(song);

        Response responseValidate = new Response(true, "Validation on add song to playlist", validationErrors);
        return responseValidate;
    }

    public Response deleteSongFromPlaylist(Song song, String email, String namePlaylist, UserRepository userRepository){
        List<ValidationError> validationErrorsForSearchPlaylist = validateSearchPlaylist(email);
        if (!validationErrorsForSearchPlaylist.isEmpty()) {
            //throw new ValidationErrorException(validationErrors);
            Response responseError = new Response(false, "Error of search the playlist", validationErrorsForSearchPlaylist);
            return responseError;
        }

        List<ValidationError> validationErrorsForSongInPlaylist = validateSongIsInPlaylist(namePlaylist, song);
        if (!validationErrorsForSongInPlaylist.isEmpty()) {
            //throw new ValidationErrorException(validationErrors);
            Response responseError = new Response(false, "Error of search song in the playlist", validationErrorsForSongInPlaylist);
            return responseError;
        }

        this.playlistRepository.getPlaylistByName(namePlaylist).deleteSongFromPlaylist(song);

        Response responseValidate = new Response(true, "Validation on delete song from playlist", validationErrorsForSongInPlaylist);
        return responseValidate;
    }

    public Response deletePlaylist(String namePlaylist){
        List<ValidationError> validationErrors = validateSearchPlaylist(namePlaylist);
        if (!validationErrors.isEmpty()) {
            //throw new ValidationErrorException(validationErrors);
            Response responseError = new Response(false, "Error of search the playlist", validationErrors);
            return responseError;
        }

        this.playlistRepository.deletePlaylist(this.playlistRepository.getPlaylistByName(namePlaylist));

        Response responseValidate = new Response(true, "Validation on delete playlist", validationErrors);
        return responseValidate;
    }

    public Response sharePlaylist(String email, UserRepository userRepository, String namePlaylist){
        List<ValidationError> validationErrors = validateSearchUser(userRepository, email);
        if (!validationErrors.isEmpty()) {
            //throw new ValidationErrorException(validationErrors);
            Response responseError = new Response(false, "Error of search the user", validationErrors);
            return responseError;
        }

        User user = userRepository.getUserByEmail(email);

        this.playlistRepository.getPlaylistByName(namePlaylist).addUserToPlaylist(user);

        Response responseValidate = new Response(true, "Validation on add user to playlist", validationErrors);
        return responseValidate;
    }

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
