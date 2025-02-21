public class Song {
    private String title;
    private String artist;
    private String album;
    private int duration;

    // Constructor
    public Song(String title, String artistName, String albumName, int duration) {
        this.title = title;
        this.artist = artistName;
        this.album = albumName;
        this.duration = duration;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getDuration() {
        return duration;
    }

    // Method to display song details
    public void display() {
        int minutes = duration / 60;
        int seconds = duration % 60;
        String formattedSeconds = (seconds < 10) ? "0" + seconds : String.valueOf(seconds);

        System.out.println("Title: " + title + ", Artist: " + artist + ", Album: " + album + ", Duration: " + minutes + ":" + formattedSeconds);
    }
}