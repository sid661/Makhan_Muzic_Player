package com.JukeBox.DaoImpl;

import Main.PlayMuzic;
import com.JukeBox.Connector.Connector;
import com.JukeBox.DaoInterface.SearchAndFilter;
import com.JukeBox.Modal.Album;
import com.JukeBox.Modal.Artist;
import com.JukeBox.Modal.Playlist;
import com.JukeBox.Modal.Songs;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchAndFilterImpl implements SearchAndFilter {
//    search by artist name
//    search by album  name
//    search by song  name
//    search by playlist name
//    sort name
//    sort id
//    after all this search and sort user should be able to play

    Connection con ;
    public  List<Artist> searchArtistname(String userinput)// acheived the data got till artist id
    {
        String artistname="";
        int artistid=0;
        List<Artist> mylist= new ArrayList<>();
        try {
          Connector connector = new Connector();
          con = connector.getConnection();
          String querry ="select * from artist where artistname like ?";
          PreparedStatement pst = con.prepareStatement(querry);
          pst.setString(1,userinput+"%");
          ResultSet rs = pst.executeQuery();
          while(rs.next()){
              artistname= rs.getString(2);
              artistid = rs.getInt(1);
              mylist.add(new Artist(artistid,artistname));
          }
            mylist.forEach(x-> System.out.println(x.getArtistname()));
        }catch(Exception e){
          e.printStackTrace();
        }return mylist;

    }

    public List<Songs> artistSongSearched(String userinput) {
        List<Songs> address= new ArrayList<>();
        List<Songs> mySonglist = new ArrayList<>();
        List<Artist> idlist = this.searchArtistname(userinput);
        int temp = 0;
        String songname ="";
        int songid =0;
        String genere ="";
        String duration ="";
        String location="";
        for (Artist x : idlist) {
            temp = x.getArtistid();

            try {
                Connector connector = new Connector();
                con = connector.getConnection();
                String querry = "select * from songs where artistid =?";
                PreparedStatement pst = con.prepareStatement(querry);
                pst.setInt(1,temp);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    songname = rs.getString(2);
                    songid = rs.getInt(1);
                    genere = rs.getString(5);
                    duration = rs.getString(4);
                    location = rs.getString(3);
                }
                mySonglist.add(new Songs(songid, songname,genere,duration));
                address.add(new Songs(location));
            } catch (Exception ex) {

            }

        }

        mySonglist.forEach(z-> System.out.println(z.getSongid()+"    "+z.getSongName()+"    "+z.getGenre()+"    "+z.getDuration()));
        return address;

    }


//----------------------------------------------------------------------------------------------------------------------------------------------------------------


// on basis of album need to show all songs of that album
    public   List<Album>  searchByAlbumName(String userinput){
        String albumname="";
        int albumid=0;
        List<Album> myalbumlist= new ArrayList<>();
        try {
            Connector connector = new Connector();
            con = connector.getConnection();
            String querry ="select * from album where albumname like ?";
            PreparedStatement pst = con.prepareStatement(querry);
            pst.setString(1,userinput+"%");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
              albumname=rs.getString(2);
              albumid = rs.getInt(1);
              myalbumlist.add(new Album(albumid,albumname));
            }
        }catch(Exception e){
            e.printStackTrace();
        }return myalbumlist;
    }


    public List<Songs> albumSongSearched(String userinput) {
        List<Songs> mySonglist = new ArrayList<>();
        List<Songs> address= new ArrayList<>();
        List<Album> idlist = this.searchByAlbumName(userinput);
        int temp = 0;
        String songname ="";
        int songid =0;
        String genere ="";
        String duration ="";
        String location ="";
        for (Album x : idlist) {
            temp = x.getAlbumid();

            try {
                Connector connector = new Connector();
                con = connector.getConnection();
                String querry = "select * from songs where albumid =?";
                PreparedStatement pst = con.prepareStatement(querry);
                pst.setInt(1, temp);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    songname = rs.getString(2);
                    songid = rs.getInt(1);
                    genere = rs.getString(5);
                    duration = rs.getString(4);
                    location=rs.getString(3);

                }
                mySonglist.add(new Songs(songid, songname,genere,duration));
                address.add(new Songs(location));
            } catch (Exception ex) {

            }

        }

        mySonglist.forEach(z-> System.out.println(z.getSongid()+"    "+z.getSongName()+"    "+z.getGenre()+"    "+z.getDuration()));
       return address;

    }



    //---------------------------------------------------------------------------------------------------------------------------------------


    public List<Songs> searchBySongName(String userinput){
        List<Songs> mySonglist = new ArrayList<>();
        List<Songs> address= new ArrayList<>();
        String songname ="";
        int songid =0;
        String genere ="";
        String duration ="";
        String location ="";
        try {
            Connector connector = new Connector();
            con = connector.getConnection();
            String querry = "select * from songs where songName like ?";
            PreparedStatement pst = con.prepareStatement(querry);
            pst.setString(1, userinput + "%");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                songname = rs.getString(2);
                songid = rs.getInt(1);
                genere = rs.getString(5);
                duration = rs.getString(4);
                location=rs.getString(3);
                mySonglist.add(new Songs(songid, songname,genere,duration));
                address.add(new Songs(location));
            }
        }catch(Exception e){

        }
        mySonglist.forEach(z-> System.out.println(z.getSongid()+"    "+z.getSongName()+"    "+z.getGenre()+"    "+z.getDuration()));
        return address;

    }


    //------------------------------------------------------------------------------------------------------------------------------//



    public List<Playlist> serachYourPlaylistName(String userinput) {
         List<Playlist> playlistsSongsId = new ArrayList<>();
         int songid = 0;
         String Playlistname="";
        try {
            Connector connector = new Connector();
            con = connector.getConnection();
            String querry = "select * from playlist where playlistname like ?";
            PreparedStatement pst = con.prepareStatement(querry);
            pst.setString(1, userinput+"%");
            ResultSet rs = pst.executeQuery();
            /// CAN CREATE TROUBLE IN FUTURE//
            while(rs.next()){
                songid=rs.getInt(3);
                Playlistname=rs.getString(2);

                playlistsSongsId.add(new Playlist(songid,Playlistname));
            }

        } catch (Exception e) {
           e.printStackTrace();
        }
        playlistsSongsId.forEach(x-> System.out.println(x.getPlaylistname()));
        return playlistsSongsId;
    }

    public List<Songs> searchSongsInPlayListWithName(String userinput)
    {
        List<Playlist> carringlist = this.serachYourPlaylistName(userinput);
        List<Songs> songsList = new ArrayList<>();
        List<Songs> address = new ArrayList<>();
        int temp=0;
        String songname ="";
        int songid =0;
        String genere ="";
        String duration ="";
        String location ="";
        for(Playlist x : carringlist) {
            temp = x.getPlaylistid();


            try {
                Connector connector = new Connector();
                con = connector.getConnection();
                String querry = "select * from songs where songid =?";
                PreparedStatement pst = con.prepareStatement(querry);
                pst.setInt(1, temp);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    songname = rs.getString(2);
                    songid = rs.getInt(1);
                    genere = rs.getString(5);
                    duration = rs.getString(4);
                    location = rs.getString(3);

                }
                songsList.add(new Songs(songid, songname, genere, duration));
                address.add(new Songs(location));
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }

        }

        songsList.forEach(z -> System.out.println(z.getSongid() + "    " + z.getSongName() + "    " + z.getGenre() + "    " + z.getDuration()));
        return address;
    }

          public List<Songs>  ShufelAnyMusic (List<Songs> songs){

              Collections.shuffle(songs);
             return songs;
          }
//-----------------------------------------------------------------------------------------------------//

    public List<Songs> sortedArtistSong(){
        SongsDaoImpl songsDao = new SongsDaoImpl();

        List<Songs> ids = new ArrayList<>();


        String songname ="";
        int songid =0;
        String genere ="";
        String duration ="";
        String location ="";

            try {
            Connector connector = new Connector();
            con = connector.getConnection();
            String querry = "select * from songs order by songName";
            PreparedStatement pst = con.prepareStatement(querry);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                songid=rs.getInt(1);
                songname=rs.getString(2);
                genere = rs.getString(4);
                duration=rs.getString(5);
                location=rs.getString(3);
                ids.add(new Songs(location));
                System.out.println(songid +"    "+songname+"    "+genere+"    "+location);
            }
        }catch (Exception e){
            e.printStackTrace();
        }return ids;
    }


      public List<Songs> playdirectSong(int songid){

           List<Songs>address = new ArrayList<>();
           String location="";
          try {
              Connector connector = new Connector();
              con = connector.getConnection();
              String querry = "select * from songs where songid=?";
              PreparedStatement pst = con.prepareStatement(querry);
              pst.setInt(1,songid);
              ResultSet rs = pst.executeQuery();
              while(rs.next()){
                  location = rs.getString(3);
                  address.add(new Songs(location));
              }

          }catch(Exception e){

          }return address;
      }



//-----------------------------------------------------------------------------------------------------------//
         public void nextAndPrev(List<Songs> songsList) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
              PlayMuzic playmuzic=null;
             Scanner sc = new Scanner(System.in);
             int i = 0 , j = 10;
             int step = 2;
             while(i < songsList.size())
             {
                 if(j==10){
                     playmuzic = new PlayMuzic(songsList.get(i).getLocation());
                     playmuzic.playSong();
                     j++;
                 }
                 System.out.println("Press 1.Previous \t Press 2.Next \t Press 3.Exit");
                 step = sc.nextInt();
                 if(step == 1){
                     if(i==0){
                         playmuzic = new PlayMuzic(songsList.get(i).getLocation());
                         playmuzic.playSong();
                     }
                     else if(i!=0){
                         i--;
                         playmuzic = new PlayMuzic(songsList.get(i).getLocation());
                         playmuzic.playSong();
                     }
                 }
                 else if(step == 2){
                     if(i == songsList.size()-1){
                         playmuzic = new PlayMuzic(songsList.get(i).getLocation());
                         playmuzic.playSong();
                     }
                     else{
                         i++;
                         playmuzic = new PlayMuzic(songsList.get(i).getLocation());
                         playmuzic.playSong();
                     }
                 }
                 else if (step == 3){
                     break;
                 }
             }

         }


}









