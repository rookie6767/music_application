import java.util.List;
import java.util.ArrayList;

public class Album {
    private String name;
    private Artist artist;
    private List<Song> songs;
    private int totalDuration; // Total duration of all songs in seconds

    // Constructor
    public Album(String name, Artist artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<>();
        this.totalDuration = 0;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public Artist getArtist() {
        return artist;
    }

    // Method to add a song to the album
    public void addSong(Song song) {
        songs.add(song);
        totalDuration += song.getDuration();
    }

    // Method to remove a song from the album
    public void removeSong(Song song) {
        if (songs.contains(song)) {
            songs.remove(song);
            totalDuration -= song.getDuration();
        }
    }

    // Method to get all songs on the album
    public List<Song> getSongs() {
        return songs;
    }

    // Method to get the total duration of all songs on the album
    public int getTotalDuration() {
        return totalDuration;
    }
}
