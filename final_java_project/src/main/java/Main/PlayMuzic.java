package Main;


import com.JukeBox.Connector.Connector;
import com.JukeBox.Modal.Status;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PlayMuzic {

    boolean value = true;
    Long currentFrame;
    Clip clip;
    String filepath;
    String status;
    AudioInputStream audioInputStream;
    Connection con ;

    public PlayMuzic(){}
    public PlayMuzic(String location)  throws IOException, LineUnavailableException, UnsupportedAudioFileException {

        Connector connector = new Connector();
        connector.getConnection();

        this.filepath=location;
        audioInputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());

        clip = AudioSystem.getClip();

        clip.open(audioInputStream);

    }

    public void playSong()
    {

        try {
            play();
            Scanner sc = new Scanner(System.in);
            while (value)
            {
                System.out.println("------------------------MAKHAN MUSIC PLAYER----------------------");
                System.out.println("-----------------------------^(*_*)^-----------------------------");
                System.out.println("OPTION  ^(*_*)^  \n   PAUSE \t  RESUME \t  RESTART \t  STOP \t  JUMP");
                String choice=sc.next();
                if(Status.PAUSE.toString().equalsIgnoreCase(choice)) {
                    pause();
                }
                else if(Status.RESUME.toString().equalsIgnoreCase(choice)) {
                    resumeAudio();
                }
                else if(Status.RESTART.toString().equalsIgnoreCase(choice)) {
                    restart();
                }
                else if(Status.STOP.toString().equalsIgnoreCase(choice)) {
                    stop();
                    value=false;
                }
                else if(Status.JUMP.toString().equalsIgnoreCase(choice)) {
                    System.out.println("Enter time (" + 0 +
                            ", " + clip.getFrameLength() + ")");
                    Scanner scan = new Scanner(System.in);
                    long c1 = scan.nextLong();
                    jump(c1);
                }
            }
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // Method to play the audio
    public void play()
    {
        //start the clip
        clip.start();
        status = "play";
    }

    // Method to pause the audio
    public void pause()
    {
        if (status.equals("paused"))
        {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        if (status.equals("play"))
        {
            System.out.println("Audio is already "+
                    "being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        play();

    }

    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    // Method to stop the audio
    public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    // Method to jump over a specific part
    public void jump(long c) throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        if (c > 0 && c < clip.getMicrosecondLength())
        {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }
    }

    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(filepath).getAbsoluteFile());
        clip.open(audioInputStream);
    }


  /*  public void musicPlayer(String location){
        try{
            File musicLocation = new File(location);
            if(musicLocation.exists()){
                AudioInputStream audioinput = AudioSystem.getAudioInputStream(musicLocation);
                Clip clip=AudioSystem.getClip();
                clip.open(audioinput);
                clip.start();
                switch()

                    long clipTimePosition = clip.getMicrosecondPosition();
                    clip.stop();
                    JOptionPane.showMessageDialog(null, "resume");
                    clip.setMicrosecondPosition(clipTimePosition);
                    clip.start();
                }
            }
            else{
                System.out.println("music loaction not found");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void pause(){

    }

    public void fetchSongLocation(String location)
    {
        // List<Songs> songsList = new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/makhan_muzic", "root", "sid@123");
            String querry ="select * from songs";
            PreparedStatement pst = con.prepareStatement(querry);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt(1)+"-->"+rs.getString(2));
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/
}