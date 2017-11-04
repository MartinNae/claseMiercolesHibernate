/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbm;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author RigoBono
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final ThreadLocal localSession;
    
    static {
        try {
            //utlilizamos la variable sessionFactory para poder guardar en ella todas 
            //las configuraciones que iremos haciendo 
            // tambien para crear modificar actualizar nuestra base de datos
            //iniciar sesion en la base de datos
            //en pocas palabras solo inicializamos el servidor 
            
           Configuration config = new Configuration();
            config.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
            applySettings(config.getProperties());
            sessionFactory = config.buildSessionFactory(builder.build());
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        // nos ayuda a crear hilos para cada cosa que vayamos a hacer.
        localSession = new ThreadLocal();
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    //iniciar una seccion entre el codigo y la base de datos
     public static Session getLocalSession() {
        Session session = (Session) localSession.get();
        if (session == null) {
            session = sessionFactory.openSession();
            localSession.set(session);
            System.out.println("\nsesion iniciada");
        }
        return session;
    }
     //cerrar sesion entre el codigo y la base de datos
     public static void closeLocalSession() {
        Session session = (Session) localSession.get();
        if (session != null) session.close();
        localSession.set(null);
        System.out.println("sesion cerrada\n");
    }
}
