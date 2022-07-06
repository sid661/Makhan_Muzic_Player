package com.JukeBox.DaoInterface;

import com.JukeBox.Modal.Album;
import com.JukeBox.Modal.Artist;
import com.JukeBox.Modal.Songs;
import com.JukeBox.Modal.User;

import java.util.List;

public interface SongsDao {
    void newUser(String mobileNo);
    boolean Login_credential1(String mobileNo, int userid);
    void createNewUser(User user);
    void insertsong();
    List<Songs> displaySongs();
    void insertArtist(Artist artist);
    void insertIntoAlbum(Album album);
    void insertMuzicDB(Songs songs,int artistid, int albumid);
}
