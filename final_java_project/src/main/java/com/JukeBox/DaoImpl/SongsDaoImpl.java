package com.JukeBox.DaoImpl;

import com.JukeBox.DaoInterface.SongsDao;
import com.JukeBox.Generate.Generate;
import com.JukeBox.Modal.Album;
import com.JukeBox.Modal.Artist;
import com.JukeBox.Modal.Songs;
import com.JukeBox.Modal.User;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SongsDaoImpl implements SongsDao {

    User user = new User();
    Songs songs = new Songs();
    Artist artist = new Artist();
    Album album = new Album();
    Generate generate = new Generate();
    //---------------------------- creating new user-----------------------------------------------------------------
    public void newUser(String mobileNo) {
        Scanner sc = new Scanner(System.in);
        String str = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(mobileNo);
        if (matcher.find() == true) {
            user.setPhonenNo(mobileNo);
            System.out.println("YOUR PHONE NUMBER HAS BEEN ADDED SUCCESSFULLY");
            int userid = 0;
            Random random = new Random();
            userid = random.nextInt(99999);
            user.setUserid(userid);
            System.out.println("PLEASE ENTER YOUR NAME");
            String name = sc.nextLine();
            user.setName(name);
            System.out.println(name + "\n" + "YOUR REGISTERED PHONE NUMBER--:>" + mobileNo + "\n" + "YOUR USER ID--:>" + userid);
            this.createNewUser(user);
        } else {
            System.out.println("PLEASE CHECK YOUR PHONE NUMBER");
        }


    }
    //------------------------------------calling private method ---------------------------------------------------------
    public boolean Login_credential1(String mobileNo, int userid){
         boolean value = this.Login_Credential(mobileNo,userid);
         return value;
    }
    //------------------------------------login credential-checking--------------------------------------------------------

    private boolean Login_Credential(String mobileNo,int useridd)
    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/makhan_muzic", "root", "sid@123");
            String querry = "select phoneNo from user where userid=?";
            PreparedStatement pst = con.prepareStatement(querry);
            pst.setInt(1, useridd);
            ResultSet rs = pst.executeQuery();
            if (rs.next() == false) {
                return false;
            } else {
                while (rs.next()) {
                    String phoneno = rs.getString("phoneNo");
                    if (mobileNo.equals(phoneno)) {
                        System.out.println("login successful !!! Enjoy Music");
                    }
                }
                return true;
            }
        }catch (SQLException e){
            //System.out.println("please check phone no and userid");
            System.out.println("please check your phone no user id");
        }
        return true;
    }




    //-------------------------------------create new user sql querry--------------------------------------------------


    public void createNewUser(User user) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/makhan_muzic", "root", "sid@123");
            String querry ="insert into user values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(querry);
            pst.setInt(1,user.getUserid());
            pst.setString(2, user.getPhonenNo());
            pst.setString(3,user.getName());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            pst.setTimestamp(4,timestamp);
            int rows = pst.executeUpdate();
            System.out.println("NEW USER CREATED");
            con.close();
        }catch(SQLException e){

        }
    }


    //--------------------------------user input for inserting songs------------------------------------------------


    public void insertsong(){
        Scanner sc = new Scanner(System.in);
        int songid=generate.generateSongid();
        songs.setSongid(songid);
        System.out.println("Please Give Song Name ");
        String songName=sc.nextLine();
        songs.setSongName(songName);
        System.out.println("Please Provide The Path From Your Computer");
        String location=sc.nextLine();
        songs.setLocation(location);
        System.out.println("Please Provide The Duration Of Your Song");
        String duration=sc.next();
        sc.nextLine();
        songs.setDuration(duration);
        System.out.println("Please Provide The Genere Of Your Song");
        String genre =sc.nextLine();
        songs.setGenre(genre);
        int artistid=generate.generateArtistid();
        artist.setArtistid(artistid);
        System.out.println("Please Provide The Name Of Your Artist");
        String artistname =sc.nextLine();
        artist.setArtistname(artistname);
        int albumid = generate.generateAlbumid();
        album.setAlbumid(albumid);
        System.out.println("Please Provide The Name To Your Album");
        String albumname =sc.nextLine();
        album.setAlbumName(albumname);

        this.insertArtist(artist);
        this.insertIntoAlbum(album);
        this.insertMuzicDB(songs,artist.getArtistid(),album.getAlbumid());

    }


    //-------------------------sql queer to insert song into sql------------------------------------------------------
    public void insertArtist(Artist artist){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/makhan_muzic", "root", "sid@123");
            String querry = "insert into artist values(?,?)";
            PreparedStatement pst = con.prepareStatement(querry);
            pst.setInt(1,artist.getArtistid() );
            pst.setString(2, artist.getArtistname());
            int rows = pst.executeUpdate();

        }catch (Exception e){

        }
    }
    public void insertIntoAlbum(Album album){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/makhan_muzic", "root", "sid@123");
            String querry1 = "insert into album values(?,?)";
            PreparedStatement pst = con.prepareStatement(querry1);
            pst.setInt(1, album.getAlbumid());
            pst.setString(2, album.getAlbumName());
            int rows1 = pst.executeUpdate();
        }catch(Exception e){

        }
    }

    public void insertMuzicDB(Songs songs,int artistid,int albumid){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/makhan_muzic", "root", "sid@123");
            String querry ="insert into songs values(?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(querry);
            pst.setInt(1,songs.getSongid());
            pst.setString(2,songs.getSongName());
            pst.setString(3,songs.getLocation());
            pst.setString(4,songs.getDuration());
            pst.setString(5,songs.getGenre());
            pst.setInt(6,artistid);
            pst.setInt(7,albumid);
            int rows = pst.executeUpdate();
            System.out.println("SONG HAS BEEN ADDED TO MUSIC PLAYER");


        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    //---------------------------------------------------------------------------------------------

    public List<Songs> displaySongs()
    {
        List<Songs> songsList = new ArrayList<>();

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/makhan_muzic", "root", "sid@123");
            String querry ="select * from songs";
            PreparedStatement pst = con.prepareStatement(querry);
            ResultSet rs = pst.executeQuery();
            System.out.format("%10s %20s", "Song Id", "Song Name");
            System.out.println();
            while(rs.next()){
                System.out.println("   "+rs.getInt(1)+"            "+rs.getString(2));
                int songid = rs.getInt(1);
                String songName= rs.getString(2);
                String location = rs.getString(3);
                String duration = rs.getString(4);
                String genere = rs.getString(5);
                int artistid = rs.getInt(6);
                int albumid =  rs.getInt(7);
                songsList.add(new Songs(songid,songName,location,duration,genere));
            }
            con.close();
        }catch(Exception e){
           e.printStackTrace();
        }
        return songsList;

    }



}
