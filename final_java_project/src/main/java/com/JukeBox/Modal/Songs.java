package com.JukeBox.Modal;

public class Songs {
    private int songid;
    private String songName;
    private String location;
    private String duration;
    private String genre;
    private Artist artist;
    private Album album;

    public Songs() {
    }

    public Songs(String location) {
        this.location = location;
    }

    public Songs(int songid, String songName, String duration, String genre) {
        this.songid = songid;
        this.songName = songName;
        this.duration = duration;
        this.genre = genre;
    }

    public Songs(int songid, String songName, String location, String duration, String genre) {
        this.songid = songid;
        this.songName = songName;
        this.location = location;
        this.duration = duration;
        this.genre = genre;
    }

    public Songs(int songid, String songName, String location, String duration, String genre, Artist artist, Album album) {
        this.songid = songid;
        this.songName = songName;
        this.location = location;
        this.duration = duration;
        this.genre = genre;
        this.artist = artist;
        this.album = album;
    }

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
