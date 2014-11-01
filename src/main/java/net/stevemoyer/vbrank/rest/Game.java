package net.stevemoyer.vbrank.rest;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.appengine.api.datastore.Key;

@XmlRootElement
@PersistenceCapable
public class Game {
    @PrimaryKey @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
        private Long id;
        private Player playerA;
    @Persistent
        private Player playerB;
    @Persistent
        private Player playerC;
    @Persistent
        private Player playerD;
    @Persistent
        private int teamABScore;
    @Persistent
        private int teamCDScore;

    public Game(){

    }

    public Game(Long id, Player a, Player b, Player c, Player d, int abScore, int cdScore){
        this.id=id;
        this.playerA=a;
        this.playerB=b;
        this.playerC=c;
        this.playerD=d;
        this.teamABScore=abScore;
        this.teamCDScore=cdScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idIn) {
        this.id = idIn;
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
}
