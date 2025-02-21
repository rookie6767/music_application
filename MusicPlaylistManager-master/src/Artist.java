import java.util.List;
import java.util.ArrayList;

public class Artist {
    private String name;
    private List<Song> songs;
    private List<Album> albums;

    // Constructor
    public Artist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
        this.albums = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    // Method to add a song to the artist's list of songs
    public void addSong(Song song) {
        songs.add(song);
    }

    // Method to remove a song from the artist's list of songs
    public void removeSong(Song song) {
        songs.remove(song);
    }

    // Method to add an album to the artist's collection
    public void addAlbum(Album album) {
        albums.add(album);
    }

    // Method to remove an album from the artist's collection
    public void removeAlbum(Album album) {
        albums.remove(album);
    }

    // Method to retrieve an album by name
    public Album getAlbum(String albumName) {
        for (Album album : albums) {
            if (album.getName().equalsIgnoreCase(albumName)) {
                return album;
            }
        }
        return null;
    }

    // Method to get all songs by the artist
    public List<Song> getSongs() {
        return songs;
    }
}
