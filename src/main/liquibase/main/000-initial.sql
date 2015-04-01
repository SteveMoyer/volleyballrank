--liquibase formatted sql
--changeset smoyer:1
create table player (
  playerid integer not null auto_increment,
  emailaddress nvarchar(255) not null,
  player_name nvarchar(255) not null,
  losses integer not null default(0),
  wins integer not null default(0),
  PRIMARY KEY (playerid)
);
create table game(
  gameid int not null auto_increment,
  playera_id int not null,
  playerb_id int not null,
  playerc_id int not null,
  playerd_id int not null,
  gamedate timestamp not null,
  postedby nvarchar(255) not null,
  teamabscore int not null,
  teamcdscore int not null,
  PRIMARY KEY (gameid),
  FOREIGN KEY (playera_id) REFERENCES Player(PlayerId),
  FOREIGN KEY (playerb_id) REFERENCES Player(PlayerId),
  FOREIGN KEY (playerc_id) REFERENCES Player(PlayerId),
  FOREIGN KEY (playerd_id) REFERENCES Player(PlayerId)
);
