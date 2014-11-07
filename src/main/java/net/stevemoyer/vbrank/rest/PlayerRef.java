package net.stevemoyer.vbrank.rest;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class PlayerRef {
    @PrimaryKey private Long id;

    @Persistent private String name;

    public PlayerRef() {

    }
    public PlayerRef(Long id, String name) {
        this.id = id;
        this.name=name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id=id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }
}
