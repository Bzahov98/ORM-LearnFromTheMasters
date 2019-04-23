import com.bzahov.musalasoftDatabase.ProductsFactory;
import com.bzahov.musalasoftDatabase.SessionHolder;
import com.bzahov.musalasoftDatabase.Vendors;
import org.hibernate.Session;

import java.math.BigDecimal;

public class Main {

    public static void main(final String[] args) throws Exception {
        final Session session = SessionHolder.getINSTANCE();
        try {

            session.beginTransaction();
            Vendors vendors = new Vendors();
            vendors.setName("Go111t81");
            session.save(vendors);
            session.getTransaction().commit();

            ProductsFactory.createProducts("Voda6",BigDecimal.ONE,
                    BigDecimal.TEN,BigDecimal.ONE,"NAVI-DOLNO_NANAGORNISHTE_OOD");

            /*System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }*/
        } finally {
          session.close();
        }
    }
}