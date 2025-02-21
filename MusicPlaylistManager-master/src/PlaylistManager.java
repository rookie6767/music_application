import java.util.HashMap;

public class PlaylistManager {
    private HashMap<String, Playlist> playlists; // Map to store playlists

    // Constructor
    public PlaylistManager() {
        this.playlists = new HashMap<>();
    }

    // Method to create a new playlist
    public void createPlaylist(String name) {
        if (!playlists.containsKey(name)) {
            playlists.put(name, new Playlist(name));
            System.out.println("Playlist '" + name + "' created successfully.");
        } else {
            System.out.println("Playlist with name '" + name + "' already exists.");
        }
    }

    // Method to get a playlist by name from the playlist manager
    public Playlist getPlaylist(String playlistName) {
        return playlists.get(playlistName);
    }

    // Method to add a song to a playlist
    public void addSongToPlaylist(String playlistName, Song song) {
        Playlist playlist = playlists.get(playlistName);
        playlist.addSong(song);
        System.out.println("Song '" + song.getTitle() + "' by '" + song.getArtist() + "' added to playlist '" + playlistName + "'.");
    }

    // Method to remove a song from a playlist
    public void removeSongFromPlaylist(String playlistName, String songTitle) {
        Playlist playlist = playlists.get(playlistName);

        // Find the song in the playlist
        Song songToRemove = playlist.findSongByTitle(songTitle);
        if (songToRemove != null) {
            // Remove the song from the playlist
            playlist.removeSong(songToRemove);
            System.out.println("Song \"" + songTitle + "\" removed from playlist \"" + playlistName + "\".");
        } else {
            System.out.println("Song \"" + songTitle + "\" not found in playlist \"" + playlistName + "\".");
        }
    }

    // Method to display all playlists
    public void displayPlaylists() {
        System.out.println("Available Playlists:");
        for (String playlistName : playlists.keySet()) {
            System.out.println("- " + playlistName);
        }
    }

    // Method to check if a playlist with the given name exists
    public boolean containsPlaylist(String playlistName) {
        return playlists.containsKey(playlistName);
    }
}