package ftt.ec;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.com.google.gson.Gson;

/*
 * WEB
 * 1. Criar conta no Gmail
 * 2. Criar conta no Google Cloud
 * 3. Criar projeto no Google Cloud
 * 4. Liberar acesso ao Google App Engine
 * 
 * Eclipse
 * 1. Configura o runtime Java 8 ou Java 11
 * 2. Installa a ferramenta do Google cloud no Marketplace (reboot Eclipse)
 * 3. Cria projeto novo... icone do Google no Eclipse
 * 
 * https://cloud.google.com/appengine
 * https://cloud.google.com/appengine/docs
 * 
 */

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {
	
  private Date created = new Date();
  private int count = 0;

  @Override
  public void doGet(HttpServletRequest request, 
		            HttpServletResponse response) 
  
      throws IOException {

    //response.setContentType("text/plain");
	response.setContentType("application/json");
	    
	response.setCharacterEncoding("UTF-8");
    
    HashMap<String, Object> doc = new HashMap<String, Object>(); //Chave e Valor
    
    doc.put("message", "Hello from Google App Engine");
    doc.put("now",     new Date());
    doc.put("started", this.created);
    doc.put("count",   nextValue());
    doc.put("Os",      System.clearProperty("os.name"));
    doc.put("OsArch",  System.clearProperty("os.arch"));
    doc.put("ClassPath", System.clearProperty("java.class.path"));
    doc.put("JavaVendor", System.clearProperty("java.vendor"));    

    response.getWriter().print(new Gson().toJson(doc));

  }
  
  private synchronized int nextValue( ) {
	  return count++;
  }
  
}
