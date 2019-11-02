package org.zbinxp.rmidemo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Client
 */
public class Client {

    private Client() {
    }
    public static void main(String[] args) {
        try {
            //get registry on localhost, default port
            Registry registry = LocateRegistry.getRegistry("localhost");
            //lookup remote object
            Hello stub = (Hello)registry.lookup("Hello");
            //call remote object
            String res = stub.sayHello();
            System.out.println("response:" + res);
        } catch (Exception e) {
            System.err.println("client exception:" + e.toString());
        }
    }
}