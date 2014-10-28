package net.stevemoyer.vbrank.rest;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Player {

    public String name;
    public int wins;
    public int losses;
    

    public Player(){
    }

    public Player(String name,int wins, int losses) {
        this.name=name;
        this.wins=wins;
        this.losses=losses;
    }
}
