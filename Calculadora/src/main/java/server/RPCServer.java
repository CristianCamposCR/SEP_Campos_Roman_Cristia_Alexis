package server;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.webserver.WebServer;

import java.io.IOException;

public class RPCServer {
    public static void main(String[] args) throws XmlRpcException, IOException {

        //HERRERA HERNANDEZ JOEL ALEJANDRO 4A

        System.out.println("Initializing server... /Inicializando servidor...");

        PropertyHandlerMapping mapping = new PropertyHandlerMapping();
        mapping.addHandler("Methods", Methods.class);

        WebServer server = new WebServer(1200);
        server.getXmlRpcServer().setHandlerMapping(mapping);
        server.start();
        System.out.println("Server running in http://localhost:1200");
        System.out.println("Waiting request...");

    }
}
