public class SongNode {
    private Song song;
    private SongNode next;

    // Constructor
    public SongNode(Song song) {
        this.song = song;
        this.next = null;
    }

    // Getter and setter for song
    public Song getSong() {
        return song;
    }

    // Getter and setter for next
    public SongNode getNext() {
        return next;
    }

    public void setNext(SongNode next) {
        this.next = next;
    }
}
