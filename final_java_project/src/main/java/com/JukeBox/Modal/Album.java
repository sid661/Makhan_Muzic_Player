package com.JukeBox.Modal;

public class Album {
    private int albumid;
    private String albumName;

    public Album() {
    }

    public Album(int albumid, String albumName) {
        this.albumid = albumid;
        this.albumName = albumName;
    }

    public int getAlbumid() {
        return albumid;
    }

    public void setAlbumid(int albumid) {
        this.albumid = albumid;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
