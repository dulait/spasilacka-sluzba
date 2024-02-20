/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemska_operacija.spasilac;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Spasilac;
import java.util.List;
import sistemska_operacija.OpstaSO;

/**
 *
 * @author drask
 */
public class SOUcitajListuSpasioca extends OpstaSO {

    private List<OpstiDomenskiObjekat> spasioci;

    public List<OpstiDomenskiObjekat> getSpasioci() {
        return spasioci;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        spasioci = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Spasilac());
    }

}
