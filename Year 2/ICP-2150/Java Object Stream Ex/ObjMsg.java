/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objmsg;

import java.io.*;
import java.net.ServerSocket;
import java.net.*;
import java.util.*;

/**
 *
 * @author dave
 */
public class ObjMsg {

    public static void main(String[] args){
        Socket sock = null;
        Message msg = null;
        try
        {
            try
            {
                ServerSocket s = new ServerSocket(8888);
                System.out.println("Waiting for connection....");

                sock = s.accept();
                // I would adde a thread here to deal with the sockets
                System.out.println("Connected");
                
                InputStream is = sock.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                msg = (Message)ois.readObject();
                String message = msg.getMsg();
                System.out.println(message);
                
                
                
            }
            finally
            {
                 sock.close();
            }
            
            
            
        }
        catch(Exception e){
            System.out.println(e);
        }
     
    }
}