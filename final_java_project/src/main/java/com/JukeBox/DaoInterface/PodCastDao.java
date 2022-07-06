package com.JukeBox.DaoInterface;

import com.JukeBox.Modal.PodCast;
import com.JukeBox.Modal.PodcastEpisodes;
import com.JukeBox.Modal.Songs;

public interface PodCastDao {
    void displayAllPodacst();
    void insertPodCast(int userid);
    void insertPodCastDB(PodCast podCast,PodcastEpisodes podcastEpisodes,int userid);
    void insertintoEpisode(PodcastEpisodes podcastEpisodes, PodCast podCast, int userid);
    void checkidepisode(int userid);
    void insertNewEpisode(int podcastid,int userid);
}
