package edu.eci.cvds.guice;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import edu.eci.cvds.persistence.ElementoDAO;
import edu.eci.cvds.persistence.EquipoDAO;
import edu.eci.cvds.persistence.NovedadElementoDAO;
import edu.eci.cvds.persistence.NovedadEquipoDAO;
import edu.eci.cvds.persistence.UsuarioDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisElementoDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisEquipoDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisNovedadElementoDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisNovedadEquipoDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisUsuarioDAO;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.impl.LaboratorioServicesImpl;

import com.google.inject.Guice;
import com.google.inject.Injector;


public class GuiceContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		servletContext.removeAttribute(Injector.class.getName());
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		Injector injector = Guice.createInjector(new XMLMyBatisModule() {
			@Override
			protected void initialize() {

				install(JdbcHelper.PostgreSQL);

				setEnvironmentId("development");

				setClassPathResource("mybatis-config.xml");

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
		}

		);

		ServletContext servletContext = servletContextEvent.getServletContext();
		servletContext.setAttribute(Injector.class.getName(), injector);
	}

}