import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectServlet extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
  protected void doGet(HttpServletRequest req,
                       HttpServletResponse resp)
          throws ServletException, IOException {

      System.out.println("----- Get Request for /test ---------");
      //resp.setStatus(HttpServletResponse.SC_FOUND);//302
      //resp.setHeader("Location", "https://sample.app.pcfdev.one.west.com");
      resp.sendRedirect("https://icrm-r-serve-static-content-qa.app.pcfdev.one.west.com/resources/images/icall_logo_tagline.gif");
     // resp.sendRedirect("http://localhost:8080/example/test2");
  }
}