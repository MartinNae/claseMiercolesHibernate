/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hbm.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojo.Persona;
import pojo.TipoPersona;

/**
 *
 * @author RigoBono
 */
public class PersonaDAO {
    Session session;
    
    public PersonaDAO(){
        session=HibernateUtil.getLocalSession();
    }
    //funcion que regresa los valores que tiene un id en su renglon
    public  Persona getPersonaById(int id){
        return (Persona)session.load(Persona.class, id);
    }
    
    // funcion del tipo arrayList que te regresa los datos de la persona buscandola 
    //solo por su nombre
    public List<Persona>  getPersonaByName(String nombre){
        List<Persona> listaDePersonas=(List<Persona>)
                session.createCriteria(Persona.class)
                .add(Restrictions.eq("Nombre", nombre));
        return listaDePersonas;
    }
    //Funcion que actualiza la base de datos de acuerdo al numero de identificacion
    public boolean updateById(int id,Persona persona){
        // se crea un objeto para poder modificar 
        Persona personaAModificar=getPersonaById(id);
        //se utiliza el trycatch para verificar que la transaccion se realizo correctamente
        
        try{
            Transaction transaccion=session.beginTransaction();
            personaAModificar.setNombre(persona.getNombre());
            session.update(personaAModificar);
            //actualiza la base de datos
            transaccion.commit();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    
    //guarda los nuevos datos de una persona 
    public boolean savePersona(String nombre,String materno,String paterno,String telefono,int idTipoPersona){
        Persona personaDeTanque=new Persona();
        
        TipoPersona tipoDeTanque=(TipoPersona)session.load(TipoPersona.class, idTipoPersona);
        personaDeTanque.setNombre(nombre);
        personaDeTanque.setMaterno(materno);
        personaDeTanque.setPaterno(paterno);
        personaDeTanque.setTelefono(telefono);
        personaDeTanque.setTipoPersona(tipoDeTanque);
        try{
            Transaction transaccion=session.beginTransaction();
            session.save(personaDeTanque);
            transaccion.commit();
            return true;
        }catch(Exception e){
            
        }finally{
            
        }
        return true;
    }
    
}
