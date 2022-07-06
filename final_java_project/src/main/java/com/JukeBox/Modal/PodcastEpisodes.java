package com.JukeBox.Modal;

public class PodcastEpisodes {
    private String Episodes;
    private String duration;
    private String location;

    public PodcastEpisodes() {
    }

    public PodcastEpisodes(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEpisodes() {
        return Episodes;
    }

    public void setEpisodes(String episodes) {
        Episodes = episodes;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
