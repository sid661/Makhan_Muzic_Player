package com.JukeBox.DaoImpl;

import com.JukeBox.Connector.Connector;
import com.JukeBox.DaoInterface.PodCastDao;
import com.JukeBox.Generate.Generate;
import com.JukeBox.Modal.PodCast;
import com.JukeBox.Modal.PodcastEpisodes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PodcastDaoImpl implements PodCastDao {
    Generate generate = new Generate();
    PodCast podCast = new PodCast();
    PodcastEpisodes podcastEpisodes = new PodcastEpisodes();
    Connector connector = new Connector();
    Connection con;

    @Override
    public void displayAllPodacst() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/makhan_muzic", "root", "sid@123");
            String querry = "select * from podcast";
            PreparedStatement pst = con.prepareStatement(querry);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "-->" + rs.getString(2));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertPodCast(int userid) {
        Scanner sc = new Scanner(System.in);
        int podcastid = generate.generatePodcastid();
        podCast.setPodcastid(podcastid);
        System.out.println("PLEASE GIVE TITLE TO YOUR PODCAST");
        String podcast = sc.nextLine();
        podCast.setPodcastTitle(podcast);
        System.out.println("PLEASE PROVIDE SYSTEM LOCATION OF YOUR PODCAST");
        String location = sc.next();
        sc.nextLine();
        podCast.setPodcastLocation(location);
        System.out.println("PLEASE PROVIDE NARRATOR OF PODCAST");
        String name = sc.nextLine();
        podCast.setPodcastNarrator(name);
        System.out.println("PLEASE PROVIDE TOPIC NAME TO YOUR PODCAST");
        String topic = sc.nextLine();
        podCast.setPodcastTopic(topic);
        System.out.println("PLEASE PROVIDE EPISODE NAME OR NUMBER TO YOUR PODCAST");
        String episodeName = sc.nextLine();
        podcastEpisodes.setEpisodes(episodeName);
        System.out.println("PLEASE PROVIDE DURATION OF YOUR PODCAST");
        String duration = sc.next();
        sc.nextLine();
        podcastEpisodes.setDuration(duration);


        this.insertPodCastDB(podCast, podcastEpisodes, userid);
        this.insertintoEpisode(podcastEpisodes, podCast, userid);

    }

    @Override
    public void insertPodCastDB(PodCast podCast, PodcastEpisodes podcastEpisodes, int userid) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/makhan_muzic", "root", "sid@123");
            String querry = "insert into podcast values(?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(querry);
            pst.setInt(1, podCast.getPodcastid());
            pst.setString(2, podCast.getPodcastTitle());
            pst.setString(3, podCast.getPodcastLocation());
            pst.setString(4, podCast.getPodcastNarrator());
            pst.setString(5, podCast.getPodcastTopic());
            pst.setString(6, podcastEpisodes.getDuration());
            int rows = pst.executeUpdate();
            System.out.println("PODCAST HAS BEEN ADDED TO MUSIC PLAYER");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertintoEpisode(PodcastEpisodes podcastEpisodes, PodCast podCast, int userid) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/makhan_muzic", "root", "sid@123");
            String querry = "insert into podcastepisode values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(querry);
            pst.setInt(1, podCast.getPodcastid());
            pst.setString(2, podcastEpisodes.getEpisodes());
            pst.setInt(3, userid);
            pst.setString(4, podCast.getPodcastLocation());
            int rows = pst.executeUpdate();
            // System.out.println("PODCAST EPISODE HAS BEEN ADDED TO MUSIC PLAYER");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkidepisode(int userid) {
        Scanner sc = new Scanner(System.in);
        System.out.println("PLEASE ENTER PODCAST ID");
        int podcastid = sc.nextInt();


        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/makhan_muzic", "root", "sid@123");
            String querry = "select * from podcast where podcastid =?";
            PreparedStatement pst = con.prepareStatement(querry);
            pst.setInt(1, podcastid);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.insertNewEpisode(podcastid, userid);
    }

    public void insertNewEpisode(int podcastid, int userid) {
        Scanner sc = new Scanner(System.in);
        System.out.println("PLEASE PROVIDE LOCATION");
        String location = sc.next();
        sc.nextLine();
        System.out.println("PLEASE PROVIDE NAME TO YOUR EPISODE");
        String episodename = sc.nextLine();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/makhan_muzic", "root", "sid@123");
            String querry = "insert into podcastepisode values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(querry);
            pst.setInt(1, podcastid);
            pst.setString(2, episodename);
            pst.setInt(3, userid);
            pst.setString(4, location);
            int rows = pst.executeUpdate();
            System.out.println("NEW EPISODE HAS BEEN ADDED TO YOUR PODCAST");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<PodCast> displayAllPodcast(int userid) {
        List<PodCast> details = new ArrayList<>();
        int podcastid =0;
        String podcastTitle ="";
        String podcastNarrator ="";
        String podcastTopic ="";
        String duration ="";
        String location="";

        System.out.println("");
        try {
              con=connector.getConnection();
              String querry = "select * from podcast";
              PreparedStatement pst = con.prepareStatement(querry);
              ResultSet rs = pst.executeQuery();
              while(rs.next()){
                     podcastid = rs.getInt(1);
                     podcastTitle=rs.getString(2);
                     location = rs.getString(3);
                     podcastNarrator=rs.getString(4);
                     podcastTopic = rs.getString(5);
                     duration=rs.getString(6);
                     details.add(new PodCast(podcastid,podcastTitle,location,podcastNarrator,podcastTopic,duration));
              }
              details.forEach(x-> System.out.println(x.getPodcastid()+ "    " +x.getPodcastTitle()+ "    " +x.getPodcastTopic()+ "    " +x.getPodcastNarrator()+x.getPodcastTopic()+ "    " +x.getDuration()));
        } catch (Exception e) {
            e.printStackTrace();
        }return details;

    }

    public List<PodCast> displayPodcastEpisodes(int userid){
           this.displayAllPodcast(userid);
           List<PodCast> address = new ArrayList<>();
           String location ="";
           Scanner sc = new Scanner(System.in);
           System.out.println("PLEASE ENTER THE PODCAST ID TO VIEW ALL EPISODES");
           int podcastid = sc.nextInt();
        try {
            con=connector.getConnection();
            String querry = "select * from podcastepisode where podcastid=?";
            PreparedStatement pst = con.prepareStatement(querry);
            pst.setInt(1,podcastid);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                System.out.format("%10s %20s", "Podcast Id", "Podcast Name");
                System.out.println();

                System.out.println("   "+rs.getString(1)+"            "+rs.getString(2));
                location=rs.getString(2);
                address.add(new PodCast(location));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return address;
    }
}
