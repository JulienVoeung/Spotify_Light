package entities;

import java.util.ArrayList;
import java.util.List;

public class Playlist extends Entity{
    private final String namePlaylist;
    private final List<Song> songPlaylist;
    private final List<User> userPlaylist;

    public Playlist(String namePlaylist, User user){
        this.namePlaylist = namePlaylist;
        this.songPlaylist = new ArrayList<>();
        this.userPlaylist = new ArrayList<>();
        this.userPlaylist.add(user);
    }

    public String getNamePlaylist(){    return this.namePlaylist;   }

    public List<Song> getSongPlaylist(){    return this.songPlaylist;   }

    public List<User> getUserPlaylist(){    return this.userPlaylist;   }

    public void addSongToPlaylist(Song song){   this.songPlaylist.add(song);    }

    public void deleteSongFromPlaylist(Song song){
        for(Song s : this.songPlaylist){
            if(s.getId().equals(song.getId())){
                this.songPlaylist.remove(s);
            }
        }
    }

    public void addUserToPlaylist(User user){
        this.userPlaylist.add(user);
    }
}
