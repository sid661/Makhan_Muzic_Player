package com.JukeBox.Modal;

public class Artist {
    private int artistid;
    private String artistname;

    public Artist() {
    }

    public Artist(int artistid) {
        this.artistid = artistid;
    }

    public Artist(String artistname) {
        this.artistname = artistname;
    }

    public Artist(int artistid, String artistname) {
        this.artistid = artistid;
        this.artistname = artistname;
    }

    public int getArtistid() {
        return artistid;
    }

    public void setArtistid(int artistid) {
        this.artistid = artistid;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }
}
