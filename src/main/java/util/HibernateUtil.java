package util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static StandardServiceRegistry registry;
	private static final SessionFactory sessionFactory = createSessionFactory();

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private static SessionFactory createSessionFactory() {
		 try {
			 System.out.println("createSessionFactory");
			 
			 try {
				 //at util.HibernateUtil.createSessionFactory(HibernateUtil.java:25)
				    Class.forName("org.hibernate.boot.registry.StandardServiceRegistry");
				    System.out.println("Hibernate libraries are available.");
				} catch (ClassNotFoundException e) {
				    System.out.println("Hibernate libraries are not found. Please check your classpath.");
				}
			 StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
			 serviceRegistryBuilder.configure("hibernate.cfg.xml");
			 StandardServiceRegistry registry = serviceRegistryBuilder.build();

		        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		        System.out.println("SessionFactory created successfully!");
		        return sessionFactory;
		    } catch (Exception e) {
		        System.err.println("Error creating SessionFactory: " + e.getMessage());
		        if (registry != null) {
		            StandardServiceRegistryBuilder.destroy(registry);
		        }
		        throw new RuntimeException("Failed to initialize Hibernate", e);
		    }
	}

	public static void shutdown() {
		if (registry != null)
			StandardServiceRegistryBuilder.destroy(registry);
	}

//	public static void main(String[] args) {
//	
////		// 配置 Hibernate
//		SessionFactory sessionFactory = HibernateConfigAndTest.getSessionFactory();
//
//	}
//		try {
//			SellerVO seller = new SellerVO();
//			seller.setSellerEmail("test@example.com");
//			seller.setSellerCompany("Test Company");
//
//			Session session = sessionFactory.openSession();
//			Transaction transaction = session.beginTransaction();
//
//			session.save(seller);
//
//			transaction.commit();
//
//			session.close();
//
//			System.out.println("Hibernate startup and test successful!");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.err.println("Hibernate startup and test failed!");
//		} finally {
//			HibernateConfigAndTest.shutdown();
//		}
//	}


}