/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.services;

import static com.google.inject.Guice.createInjector;

import java.util.Optional;

import com.google.inject.Injector;

import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import edu.eci.cvds.persistence.ElementoDAO;
import edu.eci.cvds.persistence.EquipoDAO;
import edu.eci.cvds.persistence.NovedadElementoDAO;
import edu.eci.cvds.persistence.NovedadEquipoDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisElementoDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisEquipoDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisNovedadElementoDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisNovedadEquipoDAO;
import edu.eci.cvds.services.impl.LaboratorioServicesImpl;


/**
 *
 * @author julia
 */
public class LaboratorioServiciosFactory {
    private static LaboratorioServiciosFactory instance = new LaboratorioServiciosFactory();

   private static Optional<Injector> optInjector;

   private Injector myBatisInjector(String env, String pathResource) {
       return createInjector(new XMLMyBatisModule() {
           @Override
           protected void initialize() {
               install(JdbcHelper.PostgreSQL);
               setEnvironmentId(env);
               setClassPathResource(pathResource);
       
                //TODO Colocar bind para elemento 

                //Laboratorio 
				bind(LaboratorioServices.class).to(LaboratorioServicesImpl.class);

                //elemento
                bind(ElementoDAO.class).to(MyBatisElementoDAO.class);

                //equipo
                bind(EquipoDAO.class).to(MyBatisEquipoDAO.class);

                //NovedadEquipo 
				bind(NovedadEquipoDAO.class).to(MyBatisNovedadEquipoDAO.class);
				
				//NovedadElemento 
                bind(NovedadElementoDAO.class).to(MyBatisNovedadElementoDAO.class);

           }
       });
   }

   private LaboratorioServiciosFactory(){
       optInjector = Optional.empty();
   }

   public LaboratorioServices getServiciosAlquiler(){
       if (!optInjector.isPresent()) {
           optInjector = Optional.of(myBatisInjector("development","mybatis-config.xml"));
       }

       return optInjector.get().getInstance(LaboratorioServices.class);
   }


   public LaboratorioServices getServiciosAlquilerTesting(){
       if (!optInjector.isPresent()) {
           optInjector = Optional.of(myBatisInjector("test","mybatis-config-h2.xml"));
       }

       return optInjector.get().getInstance(LaboratorioServices.class);
   }


   public static LaboratorioServiciosFactory getInstance(){
       return instance;
   }
}
