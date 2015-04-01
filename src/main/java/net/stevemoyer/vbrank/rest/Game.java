package net.stevemoyer.vbrank.rest;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@PersistenceCapable
public class Game {
    @PrimaryKey @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
        private int gameid;
    @Column(name="playerA_id")
        private Player playerA;
    @Column(name="playerB_id")
        private Player playerB;
    @Column(name="playerC_id")
        private Player playerC;
    @Column(name="playerD_id")
        private Player playerD;
    @Persistent
        private int teamABScore;
    @Persistent
        private int teamCDScore;
    @Persistent
        private Date gameDate;
    @Persistent
        private String postedBy;


    public Game(){

    }

    public Game(Player a, Player b, Player c, Player d, int abScore, int cdScore, Date gameDate, String postedBy){
        this.playerA = a;
        this.playerB = b;
        this.playerC = c;
        this.playerD = d;
        this.teamABScore = abScore;
        this.teamCDScore = cdScore;
        this.gameDate = gameDate;
        this.postedBy = postedBy;
    }


    public Game(int gameid, Player a, Player b, Player c, Player d, int abScore, int cdScore, Date gameDate, String postedBy){
        this.gameid=gameid;
        this.playerA = a;
        this.playerB = b;
        this.playerC = c;
        this.playerD = d;
        this.teamABScore = abScore;
        this.teamCDScore = cdScore;
        this.gameDate = gameDate;
        this.postedBy = postedBy;
    }

    public int getId() {
        return gameid;
    }

    public void setId(int idIn) {
        this.gameid = idIn;
    }

    public void setPlayerA(Player player) {
        playerA = player;
    }

    public void setPlayerB(Player player) {
      playerB = player;
    }

    public void setPlayerC(Player player) {
      playerC = player;
    }

    public void setPlayerD(Player player) {
      playerD = player;
    }

    public Player getPlayerA() {
      return playerA;
    }

    public Player getPlayerB() {
      return playerB;
    }

    public Player getPlayerC() {
      return playerC;
    }

    public Player getPlayerD() {
        return playerD;
    }

    public int getTeamABScore() {
        return teamABScore;
    }

    public void setTeamABScore(int teamABScore) {
        this.teamABScore = teamABScore;
    }

    public int getTeamCDScore() {
        return teamCDScore;
    }

    public void setTeamCDScore(int teamCDScore) {
        this.teamCDScore = teamCDScore;
    }
    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }
    public Date getGameDate() {
        return gameDate;
    }
    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }
    public String getPostedBy() {
        return postedBy;
    }
}
