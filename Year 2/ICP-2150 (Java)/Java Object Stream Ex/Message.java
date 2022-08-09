/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objmsg;
import java.io.*;
/**
 *
 * @author dave
 */
public class Message implements Serializable{
    
    private String msg;
    
    public Message (String s)
    {
        msg = s;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
}
