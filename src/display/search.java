package display;
import chatting.ListeningThread;
import chatting.chatting_client;
import function.ImgSetSize;
import function.get_data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class search extends JFrame {
    public class make_feed extends Thread{
        ArrayList<String> feed_num;
        JPanel feed;
        JPanel scorll;
        GridBagConstraints gbc;
        GridBagLayout Gbag;
        int i;
        public make_feed(ArrayList<String> feed_num, JPanel scorll, GridBagConstraints gbc, GridBagLayout Gbag, int i){
            this.feed_num = feed_num;
            this.scorll = scorll;
            this.gbc = gbc;
            this.Gbag = Gbag;
            this.i = i;
        }
        public void run() {
            for(int k=i; k<i+4; k++){
                System.out.println("쓰레드k : " + k);
                post pane = new post(feed_num.get(k));
                gbc.fill = GridBagConstraints.BOTH;
                gbc.ipadx = 0;
                gbc.ipady = 0;
                gbc.gridx = k%3;
                gbc.gridy = k/3;
                Gbag.setConstraints(pane,gbc);
                scroll.add(pane);
                scroll.updateUI();
            }
        }
    }
    private JButton homeButton;
    private JButton userhomeButton;
    private JButton searchButton;
    private JButton reelsButton;
    private JButton shopButton;
    private JButton searchingButton;
    private JPanel main;
    private JPanel bottom;
    private JPanel top;
    private JPanel post;
    private JScrollPane scroll_post;
    private JPanel scroll;

    private String user_id;

    private int session;

    private chatting_client client;

    private ListeningThread t1;
    public search(int session, String user_id, chatting_client client, ListeningThread t1) {
        this.user_id = user_id;
        this.session = session;
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

        ImgSetSize userFeed = new ImgSetSize("src/IMG/userhomefeed.png", 50, 50);
        userhomeButton.setIcon(home.getImg());

        ArrayList<String> post_list = new ArrayList<String>();

        get_data a = new get_data();
        a.setType16(16,user_id);
        a.start();
        post_list = a.getfeed_list();

        scroll_post.getVerticalScrollBar().setUnitIncrement(15);

        GridBagLayout Gbag = new GridBagLayout();
        scroll.setLayout(Gbag);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        if(post_list.size() < 4){
            for(int i=0; i< post_list.size(); i++) {
                post pane = new post(post_list.get(i));
                gbc.fill = GridBagConstraints.BOTH;
                gbc.ipadx = 0;
                gbc.ipady = 0;
                gbc.gridx = i % 3;
                gbc.gridy = i / 3;
                Gbag.setConstraints(pane, gbc);
                scroll.add(pane);
                scroll.updateUI();
            }
        }
        if(post_list.size() % 4 == 0){
            for(int i=0; i<post_list.size(); i+=4){
                make_feed thread = new make_feed(post_list,scroll,gbc,Gbag,i);
                thread.start();
            }
        }
        else{
            for(int i=0; i<post_list.size()-4; i+=4){
                make_feed thread = new make_feed(post_list,scroll,gbc,Gbag,i);
                thread.start();
            }
            for(int i=post_list.size()-4; i<post_list.size(); i++){
                post pane = new post(post_list.get(i));
                gbc.fill = GridBagConstraints.BOTH;
                gbc.ipadx = 0;
                gbc.ipady = 0;
                gbc.gridx = i%3;
                gbc.gridy = i/3;
                Gbag.setConstraints(pane,gbc);
                scroll.add(pane);
                scroll.updateUI();
            }
        }
        scroll_post.setViewportView(scroll);
        scroll_post.setVisible(true);
        scroll.setVisible(true);

        setContentPane(main);
        setSize(850, 1000);
        bottom.setSize(850, 50);
        setBounds(0, 0, 850, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFeed a = new mainFeed(session,user_id,client,t1);
                setVisible(false);
                a.setVisible(true);
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

        userhomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userFeed a = new userFeed(session,user_id,user_id,client,t1);
                setVisible(false);
                a.setVisible(true);
            }
        });

        searchingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchTag a = new searchTag(session,user_id,client,t1);
                setVisible(false);
                a.setVisible(true);
            }
        });

    }

    public class post extends JPanel{
        private JButton post_num;

        private String feed;

        post(String feed_id){
            feed = feed_id;
            setLayout(new GridLayout(1,1));
            setSize(200,200);
            post_num = new JButton();
            post_num.setSize(200,200);
            post_num.setBackground(new Color(255,255,255));
            get_data a = new get_data();
            a.setType18(18,feed_id);
            a.start();
            a.setType21(21,feed_id);
            a.start();

            String file_name = a.getFile_name();
            String writer = a.getposter_id();
            ImgSetSize feed_img = new ImgSetSize("post/"+writer+"/"+file_name, 200, 200);
            post_num.setIcon(feed_img.getImg());

            add(post_num);

            post_num.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame a = new JFrame();
                    display.mainFeed.feed n = new display.mainFeed.feed(feed_id,user_id);
                    a.setContentPane(n);
                    a.setSize(850,1000);
                    a.addWindowListener(new JFrameWindowClosingEventHandler());

                    a.setVisible(true);
                    dispose();
                }
            });
        }
        class JFrameWindowClosingEventHandler extends WindowAdapter {
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame)e.getWindow();
                search a = new search(session,user_id,client,t1);
                a.setVisible(true);
                frame.dispose();
            }
        }
    }

}



