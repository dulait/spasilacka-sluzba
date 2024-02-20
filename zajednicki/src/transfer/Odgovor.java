/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author drask
 */
public class Odgovor implements Serializable {

    private Object odgovor;
    private int uspeh;
    private String poruka;

    public Odgovor() {
    }

    public Odgovor(Object odgovor, int uspeh, String poruka) {
        this.odgovor = odgovor;
        this.uspeh = uspeh;
        this.poruka = poruka;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public int getUspeh() {
        return uspeh;
    }

    public void setUspeh(int uspeh) {
        this.uspeh = uspeh;
    }

}
