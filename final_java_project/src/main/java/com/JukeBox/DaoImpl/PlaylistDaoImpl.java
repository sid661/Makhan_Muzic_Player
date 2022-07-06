package com.JukeBox.DaoImpl;

import com.JukeBox.Connector.Connector;
import com.JukeBox.DaoInterface.PlaylistDao;
import com.JukeBox.Modal.Playlist;
import com.JukeBox.Modal.Songs;
import com.mysql.cj.MysqlConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaylistDaoImpl implements PlaylistDao {
    SongsDaoImpl songsDao = new SongsDaoImpl();

    public void createNewPlaylist(int userid)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("PLEASE ENTER THE PLAYLIST --> IT SHOULD BE UNIQUE");
        int playlistid = sc.nextInt();
        sc.nextLine();
        System.out.println("PLEASE ENTER THE NAME OF YOUR PLAYLIST");
        String playlistname = sc.nextLine();
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/makhan_muzic", "root", "sid@123");
            String querry ="insert into allplaylist values(?,?,?)";
            PreparedStatement pst = con.prepareStatement(querry);
            pst.setInt(1,playlistid);
            pst.setString(2,playlistname);
            pst.setInt(3,userid);
            int row = pst.executeUpdate();
            System.out.println("inserted");
        }catch(Exception e){

        }

    }
    public void addSongToPlaylist(int userid){
        songsDao.displaySongs();
        List<Playlist> songsCurrentInPLaylist=this.viewAllSongsInPlaylist(userid);
        songsCurrentInPLaylist.forEach(x-> System.out.println(x.getPlaylistid()+"--->"+x.getPlaylistname()));
        int playlistid= songsCurrentInPLaylist.get(0).getPlaylistid();
        String playlistname = songsCurrentInPLaylist.get(0).getPlaylistname();
        Scanner sc = new Scanner(System.in);
        System.out.println("PLEASE ENTER THE SONG ID FROM LIST");
        int songid = sc.nextInt();
        sc.nextLine();

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/makhan_muzic", "root", "sid@123");
            String querry ="insert into playlist values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(querry);
            pst.setInt(1,playlistid);
            pst.setString(2,playlistname);
            pst.setInt(3,songid);
            pst.setInt(4,userid);
            int row = pst.executeUpdate();
            con.close();
            System.out.println("insertion successful");
        }catch(Exception e){

        }


    }
    public List<Playlist> viewAllSongsInPlaylist(int userid){
        List<Playlist> playlistSongs = new ArrayList<>();
        this.viewAllPlaylistOfSongs(userid);
        System.out.println("PLEASE ENTER PLAYLIST ID");
        Scanner sc = new Scanner(System.in);
        int playlistid = sc.nextInt();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/makhan_muzic", "root", "sid@123");
            String querry = "select * from allplaylist where playlistid= ?";
            PreparedStatement pst = con.prepareStatement(querry);
            pst.setInt(1,playlistid);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int playlistid1 = rs.getInt(1);// working on playing songs of playlist
                String nameOfPlaylist = rs.getString(2);//
                playlistSongs.add(new Playlist(playlistid1,nameOfPlaylist));
                System.out.println("PLAYLIST ID <-->  PLAYLIST NAME ");
                System.out.println("    "+rs.getInt(1)+"     <-->   "+rs.getString(2));
            }

        }catch(SQLException e)
        {
            System.out.println("Playlist is empty !");
        }
        return playlistSongs;
    }

    public void viewAllPlaylistOfSongs(int userid){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/makhan_muzic", "root", "sid@123");
            String querry = "select * from allplaylist where userid= ?";
            PreparedStatement pst = con.prepareStatement(querry);
            pst.setInt(1,userid);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt(1)+"  <-->  "+rs.getString(2)+"   <-->   "+rs.getInt(3));
            }

        }catch(SQLException e)
        {

        }
    }
    public void deletePlaylist(int userid){
            this.viewAllPlaylistOfSongs(userid);
            Scanner sc = new Scanner(System.in);
            System.out.println("PLAY LIST ID");
            int playlistid = sc.nextInt();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/makhan_muzic", "root", "sid@123");
            String querry = "delete from allplaylist where playlistid =?";
            PreparedStatement pst = con.prepareStatement(querry);
            pst.setInt(1, playlistid);
            int row = pst.executeUpdate();
            System.out.println("PLAYLIST DELETED SUCCESSFULLY");

            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
