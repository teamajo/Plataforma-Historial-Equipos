
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2109117
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //log.info("My First Apache Shiro Application");

        //1.
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("src/main/webapp/WEB-INF/shiro.ini");
       
        //2.
        SecurityManager securityManager = factory.getInstance();
        
        //3.
        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();
        
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "12345");
        //UsernamePasswordToken token = new UsernamePasswordToken("admin", "1235");
        token.setRememberMe(true);
      
        currentUser.login(token);
        
       
    }
    
}
