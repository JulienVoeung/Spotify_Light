package repository;

import entities.Playlist;
import entities.Song;
import entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlaylistRepository {

    private List<Playlist> playlistRepository;

    public PlaylistRepository(){
        this.playlistRepository = new ArrayList<>();
    }

    public void addPlaylist(Playlist playlist){
        this.playlistRepository.add(playlist);
    }

    public void deletePlaylist(Playlist playlist){
        for(Playlist p : this.playlistRepository){
            if(p.getId().equals(playlist.getId())){
                this.playlistRepository.remove(p);
            }
        }
    }

    public List<Playlist> listPlaylist(){
        return this.playlistRepository;
    }

    public Playlist getPlaylistById(UUID id){
        for(Playlist p : this.playlistRepository){
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    public Playlist getPlaylistByName(String namePlaylist){
        for(Playlist p : this.playlistRepository){
            if(p.getNamePlaylist().equals(namePlaylist)){
                return p;
            }
        }
        return null;
    }

    public List<Playlist> getPlaylistByUser(User user){
        List<Playlist> resultPlaylistByUser = new ArrayList<>();
        for(Playlist p : this.playlistRepository){
            for(User u : p.getUserPlaylist()) {
                if (u.getId().equals(user.getId())) {
                    resultPlaylistByUser.add(p);
                }
            }
        }
        return resultPlaylistByUser;
    }

    public Playlist getOnePlaylistByName(String namePlaylist, User user){
        for(Playlist p : getPlaylistByUser(user)){
            if(p.getNamePlaylist().equals(namePlaylist)){
                return p;
            }
        }
        return null;
    }

    public Song getSongFromPlaylist(Song song, UUID idPlaylist){
        for(Playlist p : this.playlistRepository){
            if(p.getId().equals(idPlaylist)){
                for(Song s : p.getSongPlaylist()){
                    if(s.equals(song.getId())){
                        return s;
                    }
                }
            }
        }
        return null;
    }
}
