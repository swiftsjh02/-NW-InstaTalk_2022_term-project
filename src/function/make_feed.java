package function;

import chatting.protocol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class make_feed extends Thread{

    public void request(protocol content){
        try{
            this.oos.writeObject(content); // 프로토콜로 담은 내용 전송
            this.oos.flush();
        }
        catch(Exception e2){
            System.out.println(e2);
        }
    }
    private InputStream is;
    private OutputStream os;
    private DataInputStream di;
    private DataOutputStream ds;
    private PrintWriter pw;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private DataOutputStream dos;
    private BufferedOutputStream bos;
    private String feed_id;
    private String message;
    private ArrayList<String> Tag_list;
    private String file_name;

    public make_feed(String feed_id, String message, ArrayList<String> Tag_list, String file_name){
        this.feed_id = feed_id;
        this.message = message;
        this.Tag_list = Tag_list;
        this.file_name = file_name;
    }
    public void run(){
        try {
            Socket socket = new Socket("swiftsjh.tplinkdns.com", 9998);
            System.out.println("서버 연결 성공");
            this.is = socket.getInputStream();
            this.os = socket.getOutputStream();
            this.di = new DataInputStream(is);
            this.ds = new DataOutputStream(os);
            this.pw = new PrintWriter(os);
            this.oos = new ObjectOutputStream(os);
            this.dos = new DataOutputStream(os);
            this.bos = new BufferedOutputStream(os);
            this.pw = new PrintWriter(bos);
            this.ois = new ObjectInputStream(is);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        protocol p = new protocol(18, feed_id);
        request(p);
        while(true){
            try{
                protocol t = (protocol) ois.readObject();
                if(t.getTypeofrequest() == 18) {
                    feed_id = t.getSender();
                    message = t.getMessage();
                    Tag_list = t.getList();
                    file_name = t.getFile_name();
                    System.out.println("feed_id: " + feed_id + "\tmessage: " + message + "\tTag_list: " + Tag_list + "\tfile_name: " + file_name);

                    break;
                }
            } catch(Exception e1){
                e1.printStackTrace();
            }
        }
    }
}