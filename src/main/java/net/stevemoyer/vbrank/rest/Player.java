package net.stevemoyer.vbrank.rest;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.math.RoundingMode;

@XmlRootElement
@PersistenceCapable
public class Player {
    @PrimaryKey @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
    @Persistent private String emailAddress;
    @Persistent private String name;
    @Persistent private int wins=0;
    @Persistent private int losses=0;
    @Persistent private BigDecimal winningPercentage;

    public Player() {

    }

    public Player(Long id, String emailAddress, String name, int wins, int losses) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.name = name;
        this.wins = wins;
        this.losses = losses;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setId(Long idIn) {
        this.id = idIn;
    }

    public void setName(String nameIn) {
        this.name = nameIn;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getWins(){

        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
        updateWinningPercentage();
    }

    public int getLosses(){
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
        updateWinningPercentage();
    }

    public BigDecimal getWinningPercentage() {
        if(winningPercentage == null) {
            updateWinningPercentage();
        }
        return winningPercentage;
    }

    private void updateWinningPercentage() {
        if(wins == 0) {
            winningPercentage = new BigDecimal(0);
            return;
        }
        if(losses == 0) {
            winningPercentage = new BigDecimal(1);
            return;
        }

        winningPercentage = new BigDecimal(wins).divide(new BigDecimal(wins+losses),3,RoundingMode.HALF_UP);
    }

}

