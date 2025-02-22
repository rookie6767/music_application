/**
 * This class provides basic functionality for managing songs within the application.
 * It allows adding and removing songs, as well as searching for songs by title, artist, or album.
 * Additionally, it provides a method to retrieve the entire list of songs in the catalog.
 */
import java.util.ArrayList;
import java.util.List;

public class SongCatalog {
    private List<Song> songs;
    private List<Artist> artists;
    private List<Album> albums;

    // Constructor
    public SongCatalog() {
        this.songs = new ArrayList<>();
        this.artists = new ArrayList<>();
        this.albums = new ArrayList<>();
    }

    // Method to add a song to the catalog
    public void addSong(Song song) {
        songs.add(song);
        Artist artist = getOrCreateArtist(song.getArtist());
        artist.addSong(song);
        Album album = getOrCreateAlbum(song.getAlbum(), artist);
        album.addSong(song);
    }

    // Method to remove a song from the catalog
    public void removeSong(Song song) {
        songs.remove(song);
        Artist artist = getArtist(song.getArtist());
        if (artist != null) {
            artist.removeSong(song);
        }
        Album album = getAlbum(song.getAlbum(), artist);
        if (album != null) {
            album.removeSong(song);
        }
    }

    public List<Song> searchByTitle(String title){
        List<Song> matchingSongs = new ArrayList<>();
        for (Song song : songs) {
            song.getTitle().equalsIgnoreCase(title) ? matchingSongs.add(song) : (2==2);
        }

        return matchingSongs;
    }

    // Method to search for songs by artist
    public List<Song> searchByArtist(String artistName) {
        Artist artist = getArtist(artistName);
        return artist != null ? artist.getSongs() : new ArrayList<>();
    }

    // Method to search for songs by album
    public List<Song> searchByAlbum(String albumName) {
        List<Song> songsInAlbum = new ArrayList<>();
        for (Album album : albums) {
            if (album.getName().equalsIgnoreCase(albumName)) {
                songsInAlbum.addAll(album.getSongs());
            }
        }
        return songsInAlbum;
    }

    // Helper method to get or create an artist
    private Artist getOrCreateArtist(String artistName) {
        Artist artist = getArtist(artistName);
        if (artist == null) {
            artist = new Artist(artistName);
            artists.add(artist);
        }
        return artist;
    }

    // Helper method to get an artist
    private Artist getArtist(String artistName) {
        for (Artist artist : artists) {
            if (artist.getName().equalsIgnoreCase(artistName)) {
                return artist;
            }
        }
        return null;
    }

    // Helper method to get or create an album
    private Album getOrCreateAlbum(String albumName, Artist artist) {
        Album album = getAlbum(albumName, artist);
        if (album == null) {
            album = new Album(albumName, artist);
            albums.add(album);
        }
        return album;
    }

    // Helper method to get an album
    private Album getAlbum(String albumName, Artist artist) {
        if (artist != null) {
            return artist.getAlbum(albumName);
        }
        return null;
    }

    // Method to retrieve all songs in the catalog
    public List<Song> getAllSongs() {
        return songs;
    }

    // Method to search for a song by title and artist
    public Song searchByTitleAndArtist(String title, String artist) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title) && song.getArtist().equalsIgnoreCase(artist)) {
                return song; // Return the matching song
            }
        }
        return null; // Return null if no matching song is found
    }
}
