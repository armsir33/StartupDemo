package se.demo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.h2.server.web.WebServlet;

public class Main {

    public static void main(String[] args) throws Exception {
        startupServer();
    }

    private static void startupServer() throws Exception {
        final Server server = new Server(8080);

        final ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addServlet(new ServletHolder(new WebServlet()), "/console/*");
        context.addServlet(new ServletHolder(new SignUpServlet()), "/signup");

        server.setHandler(context);

        server.start();
        server.join();
    }
}
