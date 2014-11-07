package net.stevemoyer.vbrank.rest;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@PersistenceCapable
public class Game {
    @PrimaryKey @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
        private Long id;
    @Persistent 
        private PlayerRef playerA;
    @Persistent 
        private PlayerRef playerB;
    @Persistent 
        private PlayerRef playerC;
    @Persistent 
        private PlayerRef playerD;
    // TODO: THis denormailization makes me sad
    @Persistent 
        private Long playerAId;
    @Persistent 
        private Long playerBId;
    @Persistent 
        private Long playerCId;
    @Persistent 
        private Long playerDId;

    @Persistent
        private int teamABScore;
    @Persistent
        private int teamCDScore;
    @Persistent
        private Date gameDate;

    public Game(){

    }

    public Game(Long id, PlayerRef a, PlayerRef b, PlayerRef c, PlayerRef d, int abScore, int cdScore, Date gameDate){
        this.id=id;
        this.playerA = a;
        this.playerB = b;
        this.playerC = c;
        this.playerD = d;
        this.teamABScore = abScore;
        this.teamCDScore = cdScore;
        this.gameDate = gameDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idIn) {
        this.id = idIn;
    }

    public PlayerRef getPlayerA() {
        return playerA;
    }

    public PlayerRef getPlayerB() {
        return playerB;
    }

    public PlayerRef getPlayerC() {
        return playerC;
    }

    public PlayerRef getPlayerD() {
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
    public void updatePlayerIds() {
        if(playerA != null) {playerAId=playerA.getId();}
        if(playerB != null) {playerBId=playerB.getId();}
        if(playerC != null) {playerCId=playerC.getId();}
        if(playerD != null) {playerDId=playerD.getId();}
    }

}
