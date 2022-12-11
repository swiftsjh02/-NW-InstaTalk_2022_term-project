package display;

import chatting.ListeningThread;
import chatting.chatting_client;
import chatting.file_client;
import chatting.protocol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;


public class chat extends JFrame{
    private JPanel main;
    private JPanel message;
    private JTextField textField1;
    private JButton send;
    private JPanel message_user;
    private JScrollPane log_Scroll;
    private JPanel scroll_panel;
    private JTextArea jta;
    private JButton file;
    private JButton exit;
    private JTextField file_down_text;
    private JButton file_down;
    private JButton button1;
    private JButton button2;
    private String room_id;
    private String my_id;

    private chatting_client client;

    boolean running = true;
    BufferedInputStream reader = null;

    private ListeningThread t1;

    private class read extends Thread{

        public void run() {
            int i=0;
            byte[] b = new byte[100000];
            while (running) {
                try {
                    if (reader.available() > 0) {
                        byte tmp = (byte)reader.read();
                        if(tmp==13 || tmp==10){
                            String s = new String(b, StandardCharsets.UTF_8);
                            s=s.substring(0,i);
                            System.out.println(s);
                            String w[] = s.split(":");
                            if(w[3].contains("false")){
                                jta.append(w[0].substring(8,10) +":"+ w[0].substring(10,12) + "["+ w[1]+"]>>  "+ w[2] + "\n");
                            }
                            else{
                                jta.append(w[0].substring(8,10) +":"+ w[0].substring(10,12) + "["+ w[1]+"]>>  "+ "파일 : " + w[4] + "\n");
                            }
                            jta.setCaretPosition(jta.getDocument().getLength());
                            // jta.requestFocus();
                            b= new byte[100000];
                            i=0;
                        }else{
                            b[i]=tmp;
                            i++;
                        }
                    } else {
                        try {
                            sleep(100);
                        } catch (InterruptedException ex) {
                            running = false;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public chat(int session, chatting_client client, String my_id, String room_id, ListeningThread t1){


        this.t1 = t1;
        try {
            reader = new BufferedInputStream(new FileInputStream("chatting_data/"+room_id+".txt"));
        }catch (Exception e){
            try {
                reader = new BufferedInputStream(new FileInputStream("chatting_data/" + room_id + ".txt"));
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }

        new read().start();

        textField1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    send.doClick();
                    textField1.setText("");
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        this.client = client;
        this.room_id = room_id;
        this.my_id = my_id;

        setContentPane(main);

        setSize(850, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0,0,850,1000);

        jta.setEditable(false);
        jta.setSize(850, 1000);

        log_Scroll.getVerticalScrollBar().setUnitIncrement(15);

        GridBagLayout Gbag = new GridBagLayout();
        scroll_panel.setLayout(Gbag);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        JPanel a = new JPanel();
        a.setLayout(new FlowLayout(FlowLayout.LEFT));
        a.setSize(850,1000);
        a.add(jta);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 850;
        gbc.ipady = 1000;
        gbc.gridx = 0;
        gbc.gridy = 0;
        Gbag.setConstraints(a,gbc);
        scroll_panel.add(a);
        scroll_panel.updateUI();

        log_Scroll.setViewportView(scroll_panel);
        log_Scroll.setVisible(true);
        scroll_panel.setVisible(true);

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textField1.getText();
                protocol time = new protocol();
                time.setTime();
                //client에 message와 room_id보내기
                client.send_messege(4,room_id,my_id,message,time.getTime(),false,time.getTime());
            }
        });

        file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = new String();
                //file_client B = new file_client(null,fileName ,room_id,null,2, client);
                file_client A = new file_client(null,null,room_id,null,1,client);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dm a = new dm(session,client,my_id,t1);
                a.setVisible(true);
                setVisible(false);
            }
        });
        file_down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(file_down_text.isEnabled() == false){
                    file_down_text.setEnabled(true);
                    file_down_text.setText("파일 주소 입력");
                }
                else{
                    String a = file_down_text.getText();
                    a.trim();
                    file_client B = new file_client(null, a ,room_id,null,2, client);
                }
            }
        });
        file_down_text.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭했을때
                file_down_text.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                if(file_down_text.getText().equals("")){
                    file_down_text.setText("파일 주소 입력");
                }
            }
        });
    }
}

