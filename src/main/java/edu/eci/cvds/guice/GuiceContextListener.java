package edu.eci.cvds.guice;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import edu.eci.cvds.persistence.ElementoDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisElementoDAO;
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

				install(JdbcHelper.MySQL);

				setEnvironmentId("development");

				setClassPathResource("mybatis-config.xml");

				//TODO Colocar bind para elemento 

				//Laboratorio 
				bind(LaboratorioServices.class).to(LaboratorioServicesImpl.class);

				//elemento
				bind(ElementoDAO.class).to(MyBatisElementoDAO.class);
				
				//Usuario
				bind(UsuarioDAO.class).to(MyBatisUsuarioDAO.class);
				
			}
		}

		);

		ServletContext servletContext = servletContextEvent.getServletContext();
		servletContext.setAttribute(Injector.class.getName(), injector);
	}

}