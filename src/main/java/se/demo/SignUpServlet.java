package se.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SignUpServlet extends HttpServlet {

    private SessionFactory sessionFactory;

    public SignUpServlet() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " +
                "transitional//en\">\n";
        response.getWriter().println(docType +
                "<html>\n" +
                "<head><title>Sign Up</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">Add new user</h1>\n" +
                "<form method='POST'>\n" +
                "Name: <input type='text' name='name' />\n" +
                "Phone number: <input type='text' name='phone' />\n" +
                "<input type='submit' value='Submit' />\n" +
                "</form></body></html>");
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        final String name = request.getParameter("name");
        final String phone = request.getParameter("phone");

        final Session session = sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        final Users user = new Users();
        user.setName(name);
        user.setPhone(phone);
        session.save(user);
        tx.commit();
        session.close();
        sessionFactory.close();

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " +
                "transitional//en\">\n";
        response.getWriter().println(docType +
                "<html>\n" +
                "<head><title>Success</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + name + " is added successfully</h1>\n" +
                "</body></html>");
    }
}