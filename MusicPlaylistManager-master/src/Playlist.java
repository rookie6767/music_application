/**
 * The Playlist class holds a collection of songs and provide methods to manage those songs within the playlist.
 */

import java.util.LinkedList;

public class Playlist {
    private String name;
    private int numberOfSongs; // Number of songs in the playlist
    private LinkedList<Song> songs;
    private int totalDuration; // Total duration of all songs in the playlist, in seconds
    private SongNode head; // Head of the linked list

    // Constructor
    public Playlist(String name) {
        this.name = name;
        this.numberOfSongs = 0;
        this.songs = new LinkedList<>();
        this.totalDuration = 0;
        this.head = null;
    }

    // Method to get all songs on the playlist
    public void showPlaylist() {
        int minutes = totalDuration / 60;
        int seconds = totalDuration % 60;
        String formattedSeconds = (seconds < 10) ? "0" + seconds : String.valueOf(seconds);

        System.out.println("\nPlaylist: " + name);
        System.out.println("Total Duration: " + minutes + ":" + formattedSeconds + " mins");
        System.out.println("Number of Songs: " + numberOfSongs);
        System.out.println("Songs:");

        SongNode current = head; // Start from the head of the playlist

        // Traverse the playlist and display each song
        while (current != null) {
            Song song = current.getSong(); // Get the song from the current node
            int duration = song.getDuration();
            int min = duration / 60;
            int sec = duration % 60;
            String formattedSec = (sec < 10) ? "0" + sec : String.valueOf(sec);

            System.out.println(song.getTitle() + " - " + song.getArtist() + " (" + min + ":" + formattedSec + ")");
            current = current.getNext(); // Move to the next node
        }
    }

    // Method to find a song by its title
    public Song findSongByTitle(String songTitle) {
        for (SongNode current = head; current != null; current = current.getNext()) {
            if (current.getSong().getTitle().equals(songTitle)) {
                return current.getSong();
            }
        }
        return null; // Song not found
    }
}