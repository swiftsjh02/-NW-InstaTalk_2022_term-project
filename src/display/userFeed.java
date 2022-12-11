package display;

import chatting.ListeningThread;
import chatting.chatting_client;
import function.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class userFeed extends JFrame{
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
    private JButton reelsButton;
    private JButton searchButton;
    private JButton shopButton;
    private JPanel bottom;
    private JPanel top;
    private JPanel profile;
    private JPanel post;
    private JPanel main;
    private String user_id;

    private chatting_client client;

    private final static ArrayList<Integer> List= new ArrayList<>();
    private JButton create;
    private JScrollPane post_scroll;
    private JPanel scroll;
    private JButton uploadButton;
    private JButton moreButton;
    private JButton profile_follow;
    private JLabel related;
    private JLabel security;
    private JLabel arrowDown;

    private JLabel feedList;
    private JLabel feedTag;
    private JPanel userLeftRight;
    private JPanel userLeft;
    private JPanel userRight;
    private JLabel user_id_gui;
    private JLabel postNum;
    private JLabel followerNum;
    private JLabel followingNum;

    private String follow_exist;

    private String id;

    private int session;

    private ListeningThread t1;
    public userFeed(int session, String user_id, String id,chatting_client client, ListeningThread t1){

        this.user_id = user_id;
        this.session = session;
        this.client = client;
        this.id = id;
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
        userhomeButton.setIcon(userHome.getImg());

        ImgSetSize lock = new ImgSetSize("src/IMG/security.png", 50, 50);
        security.setIcon(lock.getImg());

        ImgSetSize feedLl = new ImgSetSize("src/IMG/feedList.png", 50, 50);
        feedList.setIcon(feedLl.getImg());

        ImgSetSize feedRl = new ImgSetSize("src/IMG/feedTag.png", 50, 50);
        feedTag.setIcon(feedRl.getImg());

        ImgSetSize upload = new ImgSetSize("src/IMG/addfeed.png", 50, 50);
        uploadButton.setIcon(upload.getImg());

        ImgSetSize more = new ImgSetSize("src/IMG/user_more.png", 50, 50);
        moreButton.setIcon(more.getImg());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ArrayList<String> post_list = null;

        get_data a = new get_data();
        a.setType22(22,id);
        a.start();
        post_list = a.getfeed_list();
        int num;
        if(post_list == null){
            num = 0;
        }
        else{
            num = post_list.size();
        }
        post_scroll.getVerticalScrollBar().setUnitIncrement(15);

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
        else if(post_list.size() % 4 == 0){
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
        post_scroll.setViewportView(scroll);
        post_scroll.setVisible(true);
        scroll.setVisible(true);

        get_data getData = new get_data();
        getData.setType10(10, id);
        getData.start();
        getData.setType19(19, id);
        getData.start();
        getData.setType20(20, id);
        getData.start();


        postNum.setText(String.valueOf(getData.getPostNum()));
        followingNum.setText(String.valueOf(getData.getFollowerNum()));
        followerNum.setText(String.valueOf(getData.getFollowNum()));


        if(user_id == id){
            profile_follow.setText("editprofile");
            user_id_gui.setText(user_id);
        }
        else{
            user_id_gui.setText(id);
            getData.setType9(9, user_id,id);
            getData.start();
            follow_exist = getData.getFollow_yes_or_no();
            System.out.println(follow_exist);
            if(follow_exist.equals("true")){
                profile_follow.setText("unfollow");
            }
            else{
                profile_follow.setText("follow");
            }
        }


        setContentPane(main);
        setSize(850,1000);
        bottom.setSize(850,50);
        setBounds(0,0,850,1000);
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

        profile_follow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user_id == id){
                    //profile_수정
                    editprofile a = new editprofile(session,user_id,client,t1);
                    setVisible(false);
                    a.setVisible(true);
                }
                else{
                    user_id_gui.setText(id);
                    getData.setType9(9, user_id,id);
                    getData.start();
                    follow_exist = getData.getFollow_yes_or_no();
                    //follow
                    if(follow_exist.equals("true")){
                        //unfollow 요청
                        profile_follow.setText("follow");
                        new unfollow(user_id,id,client);
                    }
                    else{
                        //follow 요청
                        profile_follow.setText("unfollow");
                        new follow(user_id,id,client);

                    }
                    get_data getData = new get_data();
                    getData.setType10(10, id);
                    getData.start();
                    getData.setType19(19, id);
                    getData.start();
                    getData.setType20(20, id);
                    getData.start();


                    postNum.setText(String.valueOf(getData.getPostNum()));
                    followingNum.setText(String.valueOf(getData.getFollowerNum()));
                    followerNum.setText(String.valueOf(getData.getFollowNum()));
                }
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
//                    display.post a = new display.post(session,user_id,client,t1);
//                    a.setVisible(true);
//                    dispose();

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
                userFeed a = new userFeed(session,user_id,id,client,t1);
                a.setVisible(true);
                frame.dispose();
            }
        }
    }
}
