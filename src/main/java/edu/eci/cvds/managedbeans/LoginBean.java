/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.managedbeans;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
/**
 *
 * @author julian Arias
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends BasePageBean implements Serializable {
    private String user,password;
    
    public void login(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("src/main/webapp/WEB-INF/shiro.ini");          
        SecurityManager securityManager = factory.getInstance();      
        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();              
        UsernamePasswordToken token = new UsernamePasswordToken(user, password);             
        try{            
            currentUser.login(token);    
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().redirect("user/bienvenida.xhtml");
        }catch(AuthenticationException au){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credenciales erroneas", ""));
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void logout(){
        Subject currentUser = SecurityUtils.getSubject(); 
        FacesContext context = FacesContext.getCurrentInstance();     
        if(currentUser!=null){
            currentUser.logout();
        }     
        try {
            context.getExternalContext().redirect("login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    } 

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
     
       
}
