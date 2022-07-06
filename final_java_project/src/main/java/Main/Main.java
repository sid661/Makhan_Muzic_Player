package Main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import com.JukeBox.DaoImpl.PlaylistDaoImpl;
import com.JukeBox.DaoImpl.PodcastDaoImpl;
import com.JukeBox.DaoImpl.SearchAndFilterImpl;
import com.JukeBox.DaoImpl.SongsDaoImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.JukeBox.DaoInterface.PodCastDao;
import com.JukeBox.DaoInterface.SearchAndFilter;
import com.JukeBox.Modal.Album;
import com.JukeBox.Modal.Playlist;
import com.JukeBox.Modal.PodCast;
import com.JukeBox.Modal.Songs;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
            PlayMuzic playmuzic = null;
            PlayMuzic playPodcast=null;
            SearchAndFilterImpl searchAndFilter = new SearchAndFilterImpl();
            PodcastDaoImpl podcastDao = new PodcastDaoImpl();
            SongsDaoImpl songsDao = new SongsDaoImpl();
            PlaylistDaoImpl playlistDao = new PlaylistDaoImpl();
            System.out.println("*----------------WELCOME TO MAKHANN MUZIC----------------*");
            Scanner sc = new Scanner(System.in);
            songsDao.displaySongs();

            int ch =0;
            do{
                System.out.println("PRESS .1. CREATE ACCOUNT  :-->\nPRESS .2. LOGIN  :-->\nPRESS .3. EXIT MUZIC PLAYER  :-->");
                ch=  sc.nextInt();
                switch(ch)
                {
                    case 1:
                            System.out.println("PLEASE ENTER YOUR PHONE NUMBER");
                            String mobileNo = sc.next();
                            songsDao.newUser(mobileNo);//  creating new user
                            break;

                    case 2:
                              System.out.println("PLEASE ENTER YOUR PHONE NUMBER");
                              String mobile_No = sc.next();
                              System.out.println("PLEASE ENTER YOUR USERID");
                              int userid = sc.nextInt();

                               if(!songsDao.Login_credential1(mobile_No, userid)){
                                   System.out.println("Pls enter valid id or number");
                               }
                               else {

                                   int choice = 0;
                                   System.out.println("");
                                   do {
                                       System.out.println("PRESS .1.-->ADD SONGS TO YOUR MUSIC PLAYER");//COMPLETED
                                       System.out.println("PRESS .2.-->ADD PODCAST TO YOUR MUSIC PLAYER");//COMPLETED
                                       System.out.println("PRESS .3.-->VIEW YOUR All SONG's PLAYLIST");//COMPLETED
                                       System.out.println("PRESS .4.-->VIEW YOUR  SONGS IN  PLAYLIST");//COMPLETED
                                       System.out.println("PRESS .5.-->VIEW YOUR All YOUR PODCAST's PLAYLIST");//COMPLETED
                                       System.out.println("PRESS .6.-->VIEW YOUR  PODCAST's IN PLAYLIST");//COMPLETED
                                       System.out.println("PRESS .7.-->ADD SONGS TO YOUR PLAYLIST");//COMPLETED
                                       System.out.println("PRESS .8.-->search or sort or shuffel then play");
                                       System.out.println("PRESS .9.-->ADD PODCAST EPISODE TO YOUR EXISTING PODCAST");//COMPLETED
                                       System.out.println("PRESS .10.-->CREATE PLAYLIST FOR YOUR SONGS");//completed
                                       System.out.println("PRESS .11.-->PLAY PODCAST");//completed
                                       System.out.println("PRESS .12.-->DELETE PLAYLIST");//completed
                                       System.out.println("PRESS .13.-->PLAY MUSIC");//completed
                                       System.out.println("PRESS .14 -->BACK TO MENU");
                                       choice = sc.nextInt();
                                       if (choice == 1) {
                                           songsDao.insertsong();
                                       } else if (choice == 2) {
                                           podcastDao.insertPodCast(userid);
                                       } else if (choice == 3) {
                                           playlistDao.viewAllPlaylistOfSongs(userid);

                                       } else if (choice == 4) {
                                           playlistDao.viewAllSongsInPlaylist(userid);
                                       } else if (choice == 5) {
                                           podcastDao.displayAllPodcast(userid);
                                       } else if (choice == 6) {
                                           // need to develope modal forplaying episodes
                                           podcastDao.displayPodcastEpisodes(userid);
                                       } else if (choice == 7) {
                                           playlistDao.addSongToPlaylist(userid);
                                       } else if (choice == 8) {
                                           System.out.println(".1.Search By Artist");
                                           System.out.println(".2.Search By Album");
                                           System.out.println(".3.Search By Song");
                                           System.out.println(".4.Search By PlayList");
                                           Scanner scanner = new Scanner(System.in);
                                           int cho = 0;
                                           cho = sc.nextInt();
                                           if (cho == 1) {
                                               System.out.println("please enter the arist name");
                                               String artist = scanner.next();
                                               List<Songs> mylist = searchAndFilter.artistSongSearched(artist);
                                               searchAndFilter.nextAndPrev(mylist);
                                           } else if (cho == 2) {
                                               System.out.println("please album name");
                                               String album = sc.next();
                                               List<Album> myAlbumlist = searchAndFilter.searchByAlbumName(album);
                                               myAlbumlist.forEach(x -> System.out.println(x.getAlbumName() + "  " + x.getAlbumid()));
                                               List<Songs> address = searchAndFilter.albumSongSearched(album);
                                               searchAndFilter.nextAndPrev(address);

                                           } else if (cho == 3) {
                                               System.out.println("please enter the song name");
                                               String song = sc.next();
                                               List<Songs> address1 = searchAndFilter.searchBySongName(song);
                                               searchAndFilter.nextAndPrev(address1);
                                           } else if (cho == 4) {
                                               System.out.println("Please enter playlist name");
                                               String songlist = sc.next();
                                               List<Songs> normallist = searchAndFilter.searchSongsInPlayListWithName(songlist);
                                               System.out.println(".1. you want play your playlist in sequence");
                                               System.out.println(".2. or do you want to shuffel your music");
                                               System.out.println("3 sort your songs list");
                                               System.out.println("4 play direct song with Songid");
                                               int enter = sc.nextInt();
                                               if (enter == 1) {
                                                   searchAndFilter.nextAndPrev(normallist);
                                               } else if (enter == 2) {
                                                   searchAndFilter.nextAndPrev(normallist);
                                               } else if (enter == 3) {
                                                   System.out.println(" sort your Song list");

                                                   List<Songs> add = searchAndFilter.sortedArtistSong();
                                                   searchAndFilter.nextAndPrev(add);

                                               } else if (enter == 4) {
                                                   System.out.println("please eneter song id");
                                                   int songid = sc.nextInt();
                                                   List<Songs> location = searchAndFilter.playdirectSong(songid);
                                                   searchAndFilter.nextAndPrev(location);
                                               }

                                           }


                                       } else if (choice == 9) {
                                           podcastDao.checkidepisode(userid);
                                       } else if (choice == 10) {
                                           playlistDao.createNewPlaylist(userid);
                                       } else if (choice == 11) {
                                           List<PodCast> details = podcastDao.displayAllPodcast(userid);

                                           int i = 0, j = 10;
                                           int step = 2;
                                           while (i < details.size()) {
                                               if (j == 10) {
                                                   details.forEach(x -> System.out.println(x.getPodcastLocation()));
                                                   playPodcast = new PlayMuzic(details.get(i).getPodcastLocation());
                                                   playPodcast.playSong();
                                                   j++;
                                               }
                                               System.out.println("Press 1.Previous \t Press 2.Next \t Press 3.Exit");
                                               step = sc.nextInt();
                                               if (step == 1) {
                                                   if (i == 0) {
                                                       playPodcast = new PlayMuzic(details.get(i).getPodcastLocation());
                                                       playPodcast.playSong();
                                                   } else if (i != 0) {
                                                       i--;
                                                       playPodcast = new PlayMuzic(details.get(i).getPodcastLocation());
                                                       playPodcast.playSong();
                                                   }
                                               } else if (step == 2) {
                                                   if (i == details.size() - 1) {
                                                       playPodcast = new PlayMuzic(details.get(i).getPodcastLocation());
                                                       playPodcast.playSong();
                                                   } else {
                                                       i++;
                                                       playPodcast = new PlayMuzic(details.get(i).getPodcastLocation());
                                                       playPodcast.playSong();
                                                   }
                                               } else if (step == 3) {
                                                   break;
                                               }
                                           }

                                       } else if (choice == 12) {
                                           playlistDao.deletePlaylist(userid);
                                       } else if (choice == 13) {
                                           List<Songs> songsList = songsDao.displaySongs();
                                           System.out.println("1.do you want to shuffel the songs \n 2. normal sequence");
                                           int sh = sc.nextInt();

                                           // implemting shuffel//
                                           if (sh == 1) {
                                               List<Songs> shuffeledlist = searchAndFilter.ShufelAnyMusic(songsList);
                                               System.out.format("%10s %20s", "Song Id", "Song Name");
                                               System.out.println();
                                               shuffeledlist.forEach(x -> System.out.format("%10s %20s \n", x.getSongid(), x.getSongName()));
                                               int i = 0, j = 10;
                                               int step = 2;
                                               while (i < shuffeledlist.size()) {
                                                   if (j == 10) {
                                                       playmuzic = new PlayMuzic(shuffeledlist.get(i).getLocation());
                                                       playmuzic.playSong();
                                                       j++;
                                                   }
                                                   System.out.println("Press 1.Previous \t Press 2.Next \t Press 3.Exit");
                                                   step = sc.nextInt();
                                                   if (step == 1) {
                                                       if (i == 0) {
                                                           playmuzic = new PlayMuzic(shuffeledlist.get(i).getLocation());
                                                           playmuzic.playSong();
                                                       } else if (i != 0) {
                                                           i--;
                                                           playmuzic = new PlayMuzic(shuffeledlist.get(i).getLocation());
                                                           playmuzic.playSong();
                                                       }
                                                   } else if (step == 2) {
                                                       if (i == shuffeledlist.size() - 1) {
                                                           playmuzic = new PlayMuzic(shuffeledlist.get(i).getLocation());
                                                           playmuzic.playSong();
                                                       } else {
                                                           i++;
                                                           playmuzic = new PlayMuzic(shuffeledlist.get(i).getLocation());
                                                           playmuzic.playSong();
                                                       }
                                                   } else if (step == 3) {
                                                       break;
                                                   }
                                               }
                                           } else if (ch == 2) {
                                               System.out.format("%10s %20s", "Song Id", "Song Name");
                                               System.out.println();
                                               songsList.forEach(x -> System.out.format("%10s %20s \n", x.getSongid(), x.getSongName()));
                                               int i = 0, j = 10;
                                               int step = 2;
                                               while (i < songsList.size()) {
                                                   if (j == 10) {
                                                       playmuzic = new PlayMuzic(songsList.get(i).getLocation());
                                                       playmuzic.playSong();
                                                       j++;
                                                   }
                                                   System.out.println("Press 1.Previous \t Press 2.Next \t Press 3.Exit");
                                                   step = sc.nextInt();
                                                   if (step == 1) {
                                                       if (i == 0) {
                                                           playmuzic = new PlayMuzic(songsList.get(i).getLocation());
                                                           playmuzic.playSong();
                                                       } else if (i != 0) {
                                                           i--;
                                                           playmuzic = new PlayMuzic(songsList.get(i).getLocation());
                                                           playmuzic.playSong();
                                                       }
                                                   } else if (step == 2) {
                                                       if (i == songsList.size() - 1) {
                                                           playmuzic = new PlayMuzic(songsList.get(i).getLocation());
                                                           playmuzic.playSong();
                                                       } else {
                                                           i++;
                                                           playmuzic = new PlayMuzic(songsList.get(i).getLocation());
                                                           playmuzic.playSong();
                                                       }
                                                   } else if (step == 3) {
                                                       break;
                                                   }

                                               }
                                           }
                                       }
                                   } while (choice != 14);
                                   break;
                               }
                    case 3:
                        System.out.println("ENJOY MUSIC");
                        break;
                    default:
                        System.out.println("INVALID INPUT PLEASE CHECK YOUR INPUT ");
                        break;
                }
            } while(ch != 3);
            {
                System.out.println("Thanks for Visiting Makhan Muzic");
            }

    }
    }




