package function;

import chatting.protocol;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class like_check extends JFrame implements Runnable{
    private protocol t;
    private InputStream is;
    private OutputStream os;
    private DataInputStream di;
    private DataOutputStream ds;
    private PrintWriter pw;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private DataOutputStream dos;
    private BufferedOutputStream bos;
    private JButton like_button;
    private String user_id;
    private String feed_id;
    public like_check(JButton like_button, String user_id, String feed_id){
        this.like_button = like_button;
        this.user_id = user_id;
        this.feed_id = feed_id;
    }
    public void request(protocol content){
        try{
            System.out.println("typeofrequset : " + content.getTypeofrequest());
            System.out.println("feed_id : " + content.getFeed_id());
            System.out.println("user_id : " + content.getSender());
            this.oos.writeObject(content); // 프로토콜로 담은 내용 전송
            this.oos.flush();
        }
        catch(Exception e2){
            System.out.println(e2);
        }
    }
    public void run(){
        try {
            Socket socket = new Socket("swiftsjh.tplinkdns.com", 9998);
            System.out.println("like_check 쓰레드 실행");
            this.is = socket.getInputStream();
            this.os = socket.getOutputStream();
            this.di = new DataInputStream(is);
            this.ds = new DataOutputStream(os);
            this.pw = new PrintWriter(os);
            this.oos = new ObjectOutputStream(os);
            this.dos = new DataOutputStream(os);
            this.bos = new BufferedOutputStream(os);
            this.pw = new PrintWriter(bos);
            protocol p = new protocol(49, user_id, feed_id);
            request(p);
            this.ois = new ObjectInputStream(is);
            while(true){
                try{
                    t = (protocol) ois.readObject();
                    if(t.getTypeofrequest() == 49){
                        System.out.println(t.getHeart());
                        if(t.getHeart()  == true) {
                            System.out.println("좋아요 누름으로 들어오긴 하는거야??????");
                            like_button.setText("♥");
                            like_button.setForeground(Color.red);
                        }
                        else if(t.getHeart()  == false) {
                            like_button.setText("♡");
                            like_button.setForeground(Color.black);
                        }
                        else{
                            System.out.println("좋아요 누르기 실패");
                        }
                        break;
                    }
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
