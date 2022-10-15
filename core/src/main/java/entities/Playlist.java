package entities;

public class Playlist extends Entity{
    private final String namePlaylist;
    private final User user;

    public Playlist(String namePlaylist, User user){
        this.namePlaylist = namePlaylist;
        this.user = user;
    }
}
