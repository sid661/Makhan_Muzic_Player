package com.JukeBox.Modal;

public class PodCast {
    private int podcastid;
    private String podcastTitle;
    private String podcastLocation;
    private String podcastNarrator;
    private String podcastTopic;
    private String duration;
    private PodcastEpisodes podcastEpisodes;

    public PodCast() {
    }

    public PodCast(String podcastLocation) {
        this.podcastLocation = podcastLocation;
    }

    public PodCast(int podcastid, String podcastTitle, String podcastLocation, String podcastNarrator, String podcastTopic, String duration) {
        this.podcastid = podcastid;
        this.podcastTitle = podcastTitle;
        this.podcastLocation = podcastLocation;
        this.podcastNarrator = podcastNarrator;
        this.podcastTopic = podcastTopic;
        this.duration = duration;
    }

    public PodcastEpisodes getPodcastEpisodes() {
        return podcastEpisodes;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setPodcastEpisodes(PodcastEpisodes podcastEpisodes) {
        this.podcastEpisodes = podcastEpisodes;
    }

    public String getPodcastLocation() {
        return podcastLocation;
    }

    public void setPodcastLocation(String podcastLocation) {
        this.podcastLocation = podcastLocation;
    }

    public int getPodcastid() {
        return podcastid;
    }

    public void setPodcastid(int podcastid) {
        this.podcastid = podcastid;
    }

    public String getPodcastTitle() {
        return podcastTitle;
    }

    public void setPodcastTitle(String podcastTitle) {
        this.podcastTitle = podcastTitle;
    }

    public String getPodcastNarrator() {
        return podcastNarrator;
    }

    public void setPodcastNarrator(String podcastNarrator) {
        this.podcastNarrator = podcastNarrator;
    }

    public String getPodcastTopic() {
        return podcastTopic;
    }

    public void setPodcastTopic(String podcastTopic) {
        this.podcastTopic = podcastTopic;
    }


}
