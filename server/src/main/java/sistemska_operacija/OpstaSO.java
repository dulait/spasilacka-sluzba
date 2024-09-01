package sistemska_operacija;

import db.DbBroker;

/**
 * Apstraktna klasa koja predstavlja ugovor za sve konkretne klase sistemskih
 * operacija.
 *
 * @author dulait
 */
public abstract class OpstaSO {

    /**
     * Izvršava sistemsku operaciju, kao i specifičnu sistemsku operaciju.
     */
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

    /**
     * Izvršava specifičnu sistemsku operaciju definisanu u klasi koja
     * implementira {@link OpstaSO}
     */
    protected abstract void izvrsiSpecificnuOperaciju();
}
