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

    //TO ADD A SONG TO REPOSITORY
    public void addSong(Song song){ this.songRepository.add(song);  }

    //TO DELETE A SONG FROM REPOSITORY
    public void deleteSong(Song song){
        for(Song s : this.songRepository){
            if(s.getId().equals(song.getId())){
                this.songRepository.remove(s);
            }
        }
    }

    //TO GET THE REPOSITORY
    public List<Song> listSong(){
        return this.songRepository;
    }

    //TO GET A SONG BY ID SONG
    public Song getSongById(UUID id){
        for(Song s : this.songRepository){
            if(s.getId().equals(id)){
                return s;
            }
        }
        return null;
    }
}
