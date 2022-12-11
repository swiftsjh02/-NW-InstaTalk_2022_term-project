package display;

import chatting.ListeningThread;
import chatting.chatting_client;
import function.ImgSetSize;
import function.get_data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class searchTag extends JFrame {
    private static ArrayList<String> friend_list = new ArrayList<String>();
    private JButton homeButton;
    private JButton userHomeButton;
    private JButton searchButton;
    private JButton reelsButton;
    private JButton shopButton;
    private JButton popularButton;
    private JButton locationButton;
    private JButton accountButton;
    private JButton audioButton;
    private JButton tagButton;
    private JPanel bottom;
    private JPanel main;
    private JTextField search_text;
    private JScrollPane sroll_pane;
    private JPanel scoll;
    private JPanel pane;

    private int session;
    private String user_id;
    private chatting_client client;
    private ListeningThread t1;

    public searchTag(int session, String user_id, chatting_client client, ListeningThread t1) {

        this.session = session;
        this.user_id = user_id;
        this.client = client;
        this.t1 = t1;

        ImgSetSize home = new ImgSetSize("src/IMG/home.png", 50, 50);
        homeButton.setIcon(home.getImg());

        ImgSetSize search = new ImgSetSize("src/IMG/search.png", 50, 50);
        searchButton.setIcon(search.getImg());

        ImgSetSize reels = new ImgSetSize("src/IMG/reels.png", 50, 50);
        reelsButton.setIcon(reels.getImg());

        ImgSetSize shop = new ImgSetSize("src/IMG/shop.jpg", 50, 50);
        shopButton.setIcon(shop.getImg());

        ImgSetSize userHome = new ImgSetSize("src/IMG/userhomefeed.png", 50, 50);
        userHomeButton.setIcon(home.getImg());


        setContentPane(main);
        setSize(850, 1000);
        bottom.setSize(850, 50);
        setBounds(0, 0, 850, 1000);
        setVisible(true);

        get_data getData = new get_data();
        getData.setType15(15, user_id);
        getData.start();
        friend_list = getData.getAllUserList();

        sroll_pane.getVerticalScrollBar().setUnitIncrement(15);

        GridBagLayout Gbag = new GridBagLayout();
        scoll.setLayout(Gbag);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        for(int i = 0;i<friend_list.size();i++){
            if(friend_list.get(i).equals(user_id)){
                continue;
            }
            friend pane = new friend(friend_list.get(i));
            gbc.fill = GridBagConstraints.BOTH;
            gbc.ipadx = 600;
            gbc.ipady = 100;
            gbc.gridx = 0;
            gbc.gridy = i;
            Gbag.setConstraints(pane,gbc);
            scoll.add(pane);
            scoll.updateUI();
        }

        sroll_pane.setBackground(new Color(255,255,255));
        sroll_pane.setViewportView(scoll);
        sroll_pane.setVisible(true);
        scoll.setVisible(true);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = search_text.getText();
                scoll.removeAll();
                for(int i = 0;i< friend_list.size();i++){
                    if(friend_list.get(i).contains(email)&&!friend_list.get(i).equals(user_id)){
                        friend pane = new friend(friend_list.get(i));
                        gbc.fill = GridBagConstraints.BOTH;
                        gbc.ipadx = 600;
                        gbc.ipady = 100;
                        gbc.gridx = 0;
                        gbc.gridy = i;
                        Gbag.setConstraints(pane,gbc);
                        scoll.add(pane);
                        scoll.updateUI();
                    }
                }
                sroll_pane.setViewportView(scoll);
                sroll_pane.setVisible(true);
                scoll.setVisible(true);
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                story a = new story(session,user_id,client,t1);
                setVisible(false);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search a = new search(session,user_id,client,t1);
                setVisible(false);
                a.setVisible(true);
            }
        });

        reelsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reels a = new reels(session,user_id,client,t1);
                setVisible(false);
                a.setVisible(true);
            }
        });

        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shop a = new shop(session,user_id,client,t1);
                setVisible(false);
                a.setVisible(true);
            }
        });

        userHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userFeed a = new userFeed(session,user_id,user_id,client,t1);
                setVisible(false);
                a.setVisible(true);
            }
        });

        accountButton.addActionListener(new ActionListener() { //계정 검색시 누르는 버튼
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = search_text.getText();
                scoll.removeAll();
                for(int i = 0;i< friend_list.size();i++){
                    if(friend_list.get(i).contains(email)&&!friend_list.get(i).equals(user_id)){
                        friend pane = new friend(friend_list.get(i));
                        gbc.fill = GridBagConstraints.BOTH;
                        gbc.ipadx = 600;
                        gbc.ipady = 100;
                        gbc.gridx = 0;
                        gbc.gridy = i;
                        Gbag.setConstraints(pane,gbc);
                        scoll.add(pane);
                        scoll.updateUI();
                    }
                }
                sroll_pane.setViewportView(scoll);
                sroll_pane.setVisible(true);
                scoll.setVisible(true);
            }
        });

    }

    public class friend extends JPanel{

        private JButton user;

        private String friend_id;

        public friend(String friend_id){
            this.friend_id = friend_id;

            setLayout(new GridLayout(1,1));
            setSize(600,100);
            user = new JButton(friend_id);
            user.setBackground(new Color(255,255,255));
            user.setSize(600,100);
            add(user);

            user.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        userFeed a = new userFeed(session,user_id,friend_id,client,t1);
                        a.setVisible(true);
                        dispose();
                }
            });

        }
    }
}