package entities;

public class Song extends Entity{

    private final String title;
    private final String album;
    private final String artist;

    public Song(String title, String album, String artist){
        this.title = title;
        this.album = album;
        this.artist = artist;
    }

    public String getTitle(){   return this.title;  }

    public String getAlbum(){   return this.album;  }

    public String getArtist(){   return this.artist;  }
}
