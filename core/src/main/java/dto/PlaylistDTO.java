package dto;

import entities.Playlist;

public class PlaylistDTO {

    private String namePlaylist;

    public PlaylistDTO(String namePlaylist){
        this.namePlaylist = namePlaylist;
    }

    public String getNamePlaylist() {   return namePlaylist;    }
}
