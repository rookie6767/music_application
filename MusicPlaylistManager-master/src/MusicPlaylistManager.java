/**
 * This main class provides a basic menu-driven interface for users to interact with the music playlist manager.
 * Each menu option corresponds to a specific action, such as creating playlists, adding songs, removing songs,
 * displaying playlist details, searching for songs, and exiting the application.
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static java.lang.Thread.sleep;

public class MusicPlaylistManager {
    private static Scanner scanner = new Scanner(System.in);
    private static SongCatalog songCatalog = new SongCatalog();
    private static PlaylistManager playlistManager = new PlaylistManager();

    public static void main(String[] args) throws InterruptedException {
        // Load songs from the text document
        loadSongsFromFile("songs.txt");

        // Display welcome message
        System.out.println("Welcome to the Music Playlist Manager!");

        // Main menu loop
        boolean exit = false;
        while (!exit) {
            // Display main menu options
            System.out.println("\nWhat do you want to do?");
            System.out.println("1. Create Playlist");
            System.out.println("2. Add Song to Playlist");
            System.out.println("3. Remove Song from Playlist");
            System.out.println("4. Display Playlist Details");
            System.out.println("5. Search Songs");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            // Get user choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Process user choice
            switch (choice) {
                case 1 -> createPlaylist();
                case 2 -> addSongToPlaylist();
                case 3 -> removeSongFromPlaylist();
                case 4 -> displayPlaylistDetails();
                case 5 -> searchSongs();
                case 6 -> {
                    exit = true;
                    System.out.println("Exiting Music Playlist Manager...");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void loadSongsFromFile(String filename) {
        try {
            File file = new File("src/" + filename);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] songDetails = line.split(",");

                if (songDetails.length >= 4) { // Ensure there are enough details
                    String title = songDetails[0].trim();
                    String artist = songDetails[1].trim();
                    String album = songDetails[2].trim();
                    int duration = Integer.parseInt(songDetails[3].trim());

                    // Create Song object and add to SongCatalog
                    Song song = new Song(title, artist, album, duration);
                    songCatalog.addSong(song);
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            System.exit(1);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing duration in file: " + filename);
            System.exit(1);
        }
    }


    // Method to create a new playlist
    private static void createPlaylist() {
        System.out.print("Enter the name of the new playlist: ");
        String playlistName = scanner.nextLine().trim();

        // Call the method to create a playlist in the PlaylistManager class
        playlistManager.createPlaylist(playlistName);

        // Call the method to add a 2-second delay
        sleep();
    }

    // Method to add a song to a playlist
    private static void addSongToPlaylist() {
        System.out.print("Enter the name of the playlist: ");
        String playlistName = scanner.nextLine().trim();

        // Check if the playlist exists
        if (!playlistManager.containsPlaylist(playlistName)) {
            System.out.println("Playlist with name '" + playlistName + "' not found.");
            return;
        }

        System.out.print("Enter the title of the song: ");
        String title = scanner.nextLine().trim();

        System.out.print("Enter the artist of the song: ");
        String artist = scanner.nextLine().trim();

        // Search for the song details based on the provided title and artist
        Song selectedSong = songCatalog.searchByTitleAndArtist(title, artist);

        if (selectedSong != null) {
            // If a matching song is found, add it directly to the playlist
            playlistManager.addSongToPlaylist(playlistName, selectedSong);
        } else {
            // If no matching song is found, display an error message
            System.out.println("No matching song found.");
        }

        // Call the method to add a 2-second delay
        sleep();
    }

    // Method to remove a song from a playlist
    private static void removeSongFromPlaylist() {
        System.out.print("Enter the name of the playlist: ");
        String playlistName = scanner.nextLine().trim();

        // Check if the playlist exists
        if (playlistManager.containsPlaylist(playlistName)) {
            System.out.print("Enter the title of the song: ");
            String songTitle = scanner.nextLine().trim();

            // Remove the song from the playlist using the PlaylistManager
            playlistManager.removeSongFromPlaylist(playlistName, songTitle);
        } else {
            System.out.println("Playlist \"" + playlistName + "\" not found.");
        }

        // Call the method to add a 2-second delay
        sleep();
    }

    // Method to display details of a playlist
    private static void displayPlaylistDetails() {
        // Display available playlists
        playlistManager.displayPlaylists();

        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine().trim();

        // Check if the playlist exists in the playlist manager
        if (playlistManager.containsPlaylist(playlistName)) {
            // Get the playlist from the playlist manager
            Playlist playlist = playlistManager.getPlaylist(playlistName);

            playlist.showPlaylist();
        } else {
            System.out.println("Playlist with name '" + playlistName + "' not found.");
        }

        // Call the method to add a 2-second delay
        sleep();
    }


    // Method to search for songs
    private static void searchSongs() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nSearch Parameter:");
        System.out.println("1. Title");
        System.out.println("2. Artist");
        System.out.println("3. Album");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        String searchTerm;
        switch (choice) {
            case 1:
                System.out.print("Enter title to search: ");
                searchTerm = scanner.nextLine().trim();
                displaySearchResults(songCatalog.searchByTitle(searchTerm));
                break;
            case 2:
                System.out.print("Enter artist to search: ");
                searchTerm = scanner.nextLine().trim();
                displaySearchResults(songCatalog.searchByArtist(searchTerm));
                break;
            case 3:
                System.out.print("Enter album to search: ");
                searchTerm = scanner.nextLine().trim();
                displaySearchResults(songCatalog.searchByAlbum(searchTerm));
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
    }

    private static void displaySearchResults(List<Song> searchResults){
        if (searchResults.isEmpty()) {
            System.out.println("No songs found matching the search term.");
        } else {
            System.out.println("Search Results:");
            for (Song song : searchResults) {
                song.display(); // Utilize the display() method of the Song class
            }
        }

        // Call the method to add a 2-second delay
        sleep();
    }

    // Method to add a 2-second delay
    private static void sleep() {
        try {
            Thread.sleep(2000); // Sleep for 2000 milliseconds (2 seconds)
        } catch (InterruptedException e) {
            // Handle the exception if sleep is interrupted
            e.printStackTrace();
        }
    }
}
