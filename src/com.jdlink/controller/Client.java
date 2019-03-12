package com.jdlink.controller;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void  main(String args[]){

             try {
                     Socket socket=new Socket("172.16.1.92",9998);
                     OutputStream os=socket.getOutputStream();
                      PrintWriter print=new PrintWriter(os);
                     print.write("你好啊！服务器！");
                      print.close();
                        os.close();
                  } catch (Exception e) {
                      e.printStackTrace();
                 }

    }
}
