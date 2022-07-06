package com.JukeBox.DaoInterface;

import com.JukeBox.Modal.Album;
import com.JukeBox.Modal.Artist;
import com.JukeBox.Modal.Playlist;
import com.JukeBox.Modal.Songs;

import java.util.List;

public interface SearchAndFilter {
    List<Artist> searchArtistname(String userinput);
    List<Songs> artistSongSearched(String userinput);
    List<Album>  searchByAlbumName(String userinput);
    List<Songs> albumSongSearched(String userinput);
    List<Songs> searchBySongName(String userinput);
    List<Playlist> serachYourPlaylistName(String userinput);
    List<Songs> searchSongsInPlayListWithName(String userinput);
    List<Songs>  ShufelAnyMusic(List<Songs> songs);
}
