package com.JukeBox.Modal;

public class Playlist {

    public int playlistid;
    private String playlistname;

    public Playlist() {
    }

    public Playlist(int playlistid) {
        this.playlistid = playlistid;
    }

    public Playlist(int playlistid, String playlistname) {
        this.playlistid = playlistid;
        this.playlistname = playlistname;
    }

    public int getPlaylistid() {
        return playlistid;
    }

    public void setPlaylistid(int playlistid) {
        this.playlistid = playlistid;
    }

    public String getPlaylistname() {
        return playlistname;
    }

    public void setPlaylistname(String playlistname) {
        this.playlistname = playlistname;
    }
}
