create database makhan_muzic;
use makhan_muzic;
create table user(
userid int(5) primary key not null,
phoneNo numeric(10) not null,
name varchar(20) not null,
dates timestamp
);


create table songs(
songid int(5) primary key not null,
songName char(50),
location varchar(50),
duration time,
genre char(50),
artistid int,
albumid int,
foreign key(artistid) references artist(artistid) on update cascade on delete cascade, 
 foreign key(albumid) references album(album_id) on update cascade on delete cascade 
);

create table playlist(
playlistid int(5) not null,
playlistname varchar(30),
songid int(5),
userid int(5),
foreign key(songid) references songs(songid) on update cascade on delete cascade,
foreign key(userid) references user(userid) on update cascade on delete cascade
);

 create table podcast(
podcastid int(5) primary key not null,
podcastTitle char(50),
location varchar(50),
podcastNarrator varchar(50),
podcastTopic varchar(50),
totaltime time
);

create table podcastepisode(
podcastid int (5),
podcastepisode varchar (50),
userid int(5),
 foreign key(userid) references user(userid) on update cascade on delete cascade, 
 foreign key(podcastid) references podcast(podcastid) on update cascade on delete cascade 
);

create table allplaylist(
playlistid int(5) primary key not null,
playlistname varchar(30),
userid int(5),
foreign key(userid) references user(userid) on update cascade on delete cascade
);

create table album(
	album_id int primary key,
    albumname varchar(60) 
    );
create table artist(
  artistid int (5) primary key,
  artistname varchar(50)
   );
