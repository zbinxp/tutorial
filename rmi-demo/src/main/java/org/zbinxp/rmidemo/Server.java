package org.zbinxp.rmidemo;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Server
 */
public class Server implements Hello {

    @Override
    public String sayHello() throws RemoteException {
        return "Hello, world!";
    }

    public static void main(String[] args) {
        try {
            Server obj = new Server();
            
            //export remote object to receive incoming calls
            Hello stub = (Hello)UnicastRemoteObject.exportObject(obj, 0);
            //start rmi registry on port 1099
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            //get registry on default port
            Registry registry = LocateRegistry.getRegistry();
            //bind remote object
            registry.bind("Hello", stub);
            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception:" + e.toString());
            // e.printStackTrace();
        }
    }
    
}