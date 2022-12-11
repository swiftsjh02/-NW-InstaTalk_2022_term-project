package display;

import chatting.*;
import function.get_data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.File;
import java.util.ArrayList;

public class dm extends JFrame{
    private static ArrayList<String> room_id;
    private static ArrayList<String> member_list;
    private JPanel main;
    private JButton createRoom;
    private JScrollPane roomPanel;
    private JPanel room;
    private JButton exit;
    private String user_id;
    private ListeningThread t1;
    private chatting_client client;

    private int session;
    public dm(int session, chatting_client client, String user_id, ListeningThread t1){
        this.session = session;
        this.t1 = t1;
        this.user_id = user_id;
        this.client = client;
        // 방 목록 업데이트
        // client에서 방목록을 불러오기 room_id 형태 arrayList<string>

        get_data getData = new get_data();
        getData.setType11(11, user_id);
        getData.start();
        room_id = getData.getMy_room_list();

        for(int i = 0;i<room_id.size();i++){
            System.out.println(room_id.get(i));
            new cache_download(null,room_id.get(i),room_id.get(i),"1",2,client);
        }
        setContentPane(main);
        exit.setBackground(new Color(255,255,255));
        setSize(850, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0,0,850,1000);
        //
        roomPanel.getVerticalScrollBar().setUnitIncrement(15);

        GridBagLayout Gbag = new GridBagLayout();
        room.setLayout(Gbag);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        for(int i = 0;i<room_id.size();i++){
            getData.setType12(12, user_id, room_id.get(i));
            getData.start();
            member_list = getData.get_users_in_room();
            roomPanel pane = new roomPanel(member_list,room_id.get(i));
            gbc.fill = GridBagConstraints.BOTH;
            gbc.ipadx = 850;
            gbc.ipady = 100;
            gbc.gridx = 0;
            gbc.gridy = i*100;
            Gbag.setConstraints(pane,gbc);
            room.add(pane);
            room.updateUI();
        }
        roomPanel.setViewportView(room);
        roomPanel.setVisible(true);
        room.setVisible(true);

        createRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invite a = new invite(session,client,user_id,t1);
                setVisible(false);
                a.setVisible(true);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFeed a = new mainFeed(session,user_id,client,t1);
                setVisible(false);
                a.setVisible(true);
            }
        });
    }
    public class roomPanel extends JPanel{
        private  ArrayList<String> member_list = new ArrayList<>();
        private String room_id;
        private JLabel member;
        private JButton in;
        private JButton out;
        public roomPanel(ArrayList<String> member , String id){
            this.member_list = member;
            this.room_id = id;
            this.member = new JLabel();
            String a = new String();
            for(int i =0;i<member_list.size();i++){
                a = a + " " + member_list.get(i);
                System.out.println(member_list.get(i));
            }
            this.member.setText(a);
            this.in = new JButton("in");
            this.out = new JButton("out");

            setLayout(new FlowLayout(FlowLayout.LEFT));
            setSize(850,100);

            this.in.setBackground(new Color(255,255,255));
            this.out.setBackground(new Color(255,255,255));
            this.in.setSize(100,100);
            this.out.setSize(100,100);
            this.member.setSize(750,100);
            add(this.in);
            add(this.out);
            add(this.member);
            in.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chat a = new chat(session,client,user_id,room_id,t1);
                    a.setVisible(true);
                    dispose();
                }
            });
            out.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    client.exit_room(3,user_id,room_id);
                    File b = new File("chatting_data/" + room_id + ".txt");
                    System.out.println("chatting_data/" + room_id + ".txt");
                    if(b.exists()){
                        System.out.println("있다");

                        b.delete();
                    }
                    else{
                        System.out.println("없다");
                    }

                    dm a = new dm(session, client, user_id,t1);
                    a.setVisible(true);
                    dispose();
                }
            });
        } // 생성자
    } // roomPanel
} // dm
