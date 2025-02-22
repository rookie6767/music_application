/* Create a class named SongNode with private fields song and next
include a constructor , getter and setter methods and a set next node method */

public class SongNode {
    private Song song;
    private SongNode next;

    public SongNode(Song song) {
        this.song = song;
        this.next = null;
    }

    public Song getSong() {
        return song;
    }

    public SongNode getNext() {
        return next;
    }

    public void setNext(SongNode next) {
        this.next = next;
    }
}