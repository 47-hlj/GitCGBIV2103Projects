package com.cy.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Tomcat {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        System.out.println("server is starting");
        boolean isFlag=true;
        while(isFlag){
            Socket socket=server.accept();
            System.out.println(socket);
        }

    }
}
