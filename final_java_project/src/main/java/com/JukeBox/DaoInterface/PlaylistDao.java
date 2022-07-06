package com.JukeBox.DaoInterface;

import com.JukeBox.Modal.Playlist;

import java.util.List;

public interface PlaylistDao {
    void createNewPlaylist(int userid);
    void addSongToPlaylist(int userid);
    List<Playlist> viewAllSongsInPlaylist(int userid);
    void viewAllPlaylistOfSongs(int userid);
}
