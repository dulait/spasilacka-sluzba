package sistemska_operacija;

import db.DbBroker;

public abstract class OpstaSO {

    public final void izvrsiSistemskuOperaciju() {
        try {
            DbBroker.getInstanca().otvoriKonekciju();
            izvrsiSpecificnuOperaciju();
            DbBroker.getInstanca().commit();
            DbBroker.getInstanca().zatvoriKonekciju();
        } catch (Exception ex) {
            DbBroker.getInstanca().rollback();
            DbBroker.getInstanca().zatvoriKonekciju();
        }
    }

    protected abstract void izvrsiSpecificnuOperaciju();

}
