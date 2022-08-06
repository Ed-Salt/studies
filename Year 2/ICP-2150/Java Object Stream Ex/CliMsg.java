/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objmsg;

import java.io.IOException;
import java.io.OutputStream;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author dave
 */
public class CliMsg {

    public static void main(String[] args) throws IOException {
       
        Socket sock = new Socket("127.0.0.1", 8888);
        System.out.println("Connected");
        OutputStream os = sock.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
      
        
        Scanner kb = new Scanner(System.in);
        System.out.println("Please type message");
        
       
        String s = kb.nextLine();
        
        Message m = new Message(s);
        oos.writeObject(m);
        oos.flush();
       
        
        
        sock.close();
    }

}
