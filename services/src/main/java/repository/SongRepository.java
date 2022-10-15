package repository;

import entities.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SongRepository {

    private List<Song> songRepository;

    public SongRepository(){
        this.songRepository = new ArrayList<>();
    }

    public void addSong(Song song){ this.songRepository.add(song);  }

    public void deleteSong(Song song){
        for(Song s : this.songRepository){
            if(s.getId().equals(song.getId())){
                this.songRepository.remove(s);
            }
        }
    }

    public List<Song> listSong(){
        return this.songRepository;
    }

    public Song getSongById(UUID id){
        for(Song s : this.songRepository){
            if(s.getId().equals(id)){
                return s;
            }
        }
        return null;
    }
}
