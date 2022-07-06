package com.JukeBox.Generate;

import java.util.Random;

public class Generate {
    public int generateSongid(){
        int transactionid=0;
        Random random = new Random();
        transactionid =random.nextInt(9999999);
        return (transactionid);
    }

    public int generateArtistid(){
        int transactionid=0;
        Random random = new Random();
        transactionid =random.nextInt(9999);
        return transactionid;
    }

    public int generateAlbumid(){
        int transactionid=0;
        Random random = new Random();
        transactionid =random.nextInt(100000);
        return transactionid;
    }
    public int generatePodcastid(){
        int transactionid=0;
        Random random = new Random();
        transactionid =random.nextInt(80000);
        return transactionid;
    }
}
