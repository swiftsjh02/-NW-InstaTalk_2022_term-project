package display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import function.*;
import chatting.*;

import static java.lang.System.exit;


public class mainFeed extends JFrame{

    private JPanel panelBottom;
    private JButton homeButton;
    private JButton userHomeButton;
    private JButton searchButton;
    private JButton reelsButton;
    private JButton shopButton;
    private JPanel main;
    private JLabel icon;
    private JButton add;
    private JButton heart;
    private JButton dm;
    private JPanel topbar;
    private JScrollPane feedscroll;

    private JPanel home_main;
    private JButton story;
    private JPanel feed;
    private JPanel home;

    public String user_id;
    public int session_id;

    public ArrayList<String> feed_num;

    class JFrameWindowClosingEventHandler extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            JFrame frame = (JFrame)e.getWindow();
            loginregister manager= new loginregister();
            frame.dispose();
            manager.logout(session_id);
            System.out.println("LogOut");
        }
    }

    public class make_feed extends Thread{
        ArrayList<String> feed_num;
        JPanel feed;
        GridBagConstraints gbc;
        GridBagLayout Gbag;
        int i;
        public make_feed(ArrayList<String> feed_num, JPanel feed, GridBagConstraints gbc, GridBagLayout Gbag, int i){
            this.feed_num = feed_num;
            this.feed = feed;
            this.gbc = gbc;
            this.Gbag = Gbag;
            this.i = i;
        }
        public void run() {
            for(int k=i; k<i+4; k++){
                System.out.println("쓰레드k : " + k);
                feed pane = new feed(feed_num.get(k),user_id);
                gbc.fill = GridBagConstraints.BOTH;
                gbc.ipadx = 0;
                gbc.ipady = 0;
                gbc.gridx = 0;
                gbc.gridy = k;
                Gbag.setConstraints(pane, gbc);
                feed.add(pane);
                feed.updateUI();
            }
        }
    }

    public mainFeed(int session,String user_id,chatting_client client,ListeningThread t1){

        session_id=session;
        this.user_id=user_id;

        ImgSetSize home = new ImgSetSize("src/IMG/home.png", 50, 50);
        homeButton.setIcon(home.getImg());

        ImgSetSize search = new ImgSetSize("src/IMG/search.png", 50, 50);
        searchButton.setIcon(search.getImg());

        ImgSetSize reels = new ImgSetSize("src/IMG/reels.png", 50, 50);
        reelsButton.setIcon(reels.getImg());

        ImgSetSize shop = new ImgSetSize("src/IMG/shop.jpg", 50, 50);
        shopButton.setIcon(shop.getImg());

        ImgSetSize userhome = new ImgSetSize("src/IMG/userhomefeed.png", 50, 50);
        userHomeButton.setIcon(home.getImg());

        ImgSetSize mainphoto = new ImgSetSize("src/IMG/login.png", 200, 80);
        icon.setIcon(mainphoto.getImg());

        ImgSetSize storyEdit = new ImgSetSize("src/IMG/story.jpg", 70, 100);
        story.setIcon(storyEdit.getImg());

        ImgSetSize add_size = new ImgSetSize("src/IMG/addfeed.png", 50, 50);
        add.setIcon(add_size.getImg());

        ImgSetSize notification = new ImgSetSize("src/IMG/likes2.png", 50, 50);
        heart.setIcon(notification.getImg());

        ImgSetSize dm_size = new ImgSetSize("src/IMG/dm.jpg", 50, 50);
        dm.setIcon(dm_size.getImg());

        get_data feed_data = new get_data();
        feed_data.setType16(16,user_id);
        feed_data.start();
        feed_num = feed_data.getfeed_list();

        feedscroll.getVerticalScrollBar().setUnitIncrement(15);

        GridBagLayout Gbag = new GridBagLayout();
        feed.setLayout(Gbag);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        if(feed_num.size() < 4){
            for(int i=0; i< feed_num.size(); i++) {
                feed pane = new feed(feed_num.get(i),user_id);
                gbc.fill = GridBagConstraints.BOTH;
                gbc.ipadx = 0;
                gbc.ipady = 0;
                gbc.gridx = 0;
                gbc.gridy = i;
                Gbag.setConstraints(pane, gbc);
                feed.add(pane);
                feed.updateUI();
            }
        }
        else if(feed_num.size() % 4 == 0){
            for(int i=0; i<feed_num.size(); i+=4){
                make_feed thread = new make_feed(feed_num,feed,gbc,Gbag,i);
                thread.start();
            }
        }
        else{
            for(int i=0; i<feed_num.size()-4; i+=4){
                make_feed thread = new make_feed(feed_num,feed,gbc,Gbag,i);
                thread.start();
            }
            for(int i=feed_num.size()-4; i<feed_num.size(); i++){
                feed pane = new feed(feed_num.get(i),user_id);
                gbc.fill = GridBagConstraints.BOTH;
                gbc.ipadx = 0;
                gbc.ipady = 0;
                gbc.gridx = 0;
                gbc.gridy = i;
                Gbag.setConstraints(pane, gbc);
                feed.add(pane);
                feed.updateUI();
            }
        }

        feedscroll.setViewportView(feed);
        feedscroll.setVisible(true);
        feed.setVisible(true);
        setContentPane(main);

        setSize(850, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0,0,850,1000);
        setTitle("AI-DB Instagram LogIn System");
        //setVisible(true);
        this.addWindowListener(new JFrameWindowClosingEventHandler());

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

        userHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userFeed a = new userFeed(session,user_id,user_id,client,t1);
                setVisible(false);
                a.setVisible(true);
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFeed a = new addFeed(session,user_id,client,t1);
                setVisible(false);
                a.setVisible(true);
            }
        });

        heart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        dm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dm a = new dm(session,client,user_id,t1);
                setVisible(false);
                a.setVisible(true);
            }
        });


        story.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                story a = new story(session,user_id,client,t1);
                setVisible(false);
                a.setVisible(true);
            }
        });
    }

    public static class feed extends JPanel{
        private String feed_id;
        private String message;
        private String file_name;
        private ArrayList<String> Tag;

        private String writer;

        private JLabel img;

        private JLabel poster;
        private JTextArea feedMessage;

        private JTextArea feedTag;

        private JTextField comment;
        private JButton comment_button;

        private JLabel like_num1;

        private JButton like_button;
        public feed(String feed_id,String user_id){
            get_data feed_data = new get_data();
            feed_data.setType18(18,feed_id);
            feed_data.start();
            this.feed_id = feed_data.getFeed_id();
            message = feed_data.getMessage();
            file_name = feed_data.getFile_name();
            Tag = feed_data.getTag_list();

            feed_data.setType21(21,feed_id);
            feed_data.start();
            writer = feed_data.getposter_id();
            File img_tmp=new File("post/"+writer+"/"+file_name);
            if(img_tmp.exists()==false){
                try{imgdownload tmp = new imgdownload(writer,file_name);
                    tmp.start();
                    tmp.join();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }


            setSize(600,800);
            setBackground(new Color(0,0,0));
            GridBagLayout Gbag = new GridBagLayout();
            setLayout(Gbag);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;

            poster = new JLabel(writer);
            poster.setForeground(new Color(255,255,255));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 6;
            gbc.gridheight = 1;
            gbc.weightx = 0.75;
            gbc.weighty = 0.1;
            gbc.ipadx = 0;
            gbc.ipady = 0;
            add(poster,gbc);

            //좋아요 갯수
            like_num1 = new JLabel();
            feed_data.setType23(23,feed_id);
            feed_data.start();
            like_num1.setText("좋아요 : " + String.valueOf(feed_data.getLikeNum()));
            like_num1.setForeground(new Color(255,255,255));
            gbc.gridx = 6;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.gridheight = 1;
            gbc.weightx = 0.25;
            gbc.weighty = 0.1;
            gbc.ipadx = 0;
            gbc.ipady = 0;
            add(like_num1,gbc);
            //이미지 추가
            img = new JLabel();
            img.setSize(600,400);
            ImgSetSize image = new ImgSetSize("post/"+writer+"/"+file_name, 800, 400);
            img.setIcon(image.getImg());
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 8;
            gbc.gridheight = 7;
            gbc.weightx = 1.0;
            gbc.weighty = 0.7;
            gbc.ipadx = 0;
            gbc.ipady = 0;
            add(img,gbc);

            //메세지 추가
            feedMessage = new JTextArea();
            feedMessage.setSize(600,200);
            feedMessage.setText(message);
            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.gridwidth = 8;
            gbc.gridheight = 4;
            gbc.weightx = 1.0;
            gbc.weighty = 0.1;
            gbc.ipadx = 0;
            gbc.ipady = 0;
            add(feedMessage,gbc);

            //태그 추가
            feedTag = new JTextArea();
            feedTag.setSize(600,100);
            feedTag.setText("");
            for(int i = 0;i<Tag.size();i++){
                feedTag.setText(feedTag.getText() + "#" + Tag.get(i) + " ");
            }
            gbc.gridx = 0;
            gbc.gridy = 12;
            gbc.gridwidth = 8;
            gbc.gridheight = 2;
            gbc.weightx = 1.0;
            gbc.weighty = 0.04;
            gbc.ipadx = 0;
            gbc.ipady = 0;
            add(feedTag,gbc);

            //댓글 창 추가
            comment = new JTextField();
            comment.setSize(450,100);
            gbc.gridx = 0;
            gbc.gridy = 14;
            gbc.gridwidth = 6;
            gbc.gridheight = 2;
            gbc.weightx = 0.75;
            gbc.weighty = 0.06;
            gbc.ipadx = 0;
            gbc.ipady = 0;
            add(comment,gbc);

            //댓글 버튼
            comment_button = new JButton("comment");
            comment_button.setBackground(new Color(255,255,255));
            comment_button.setSize(150,100);
            gbc.gridx = 6;
            gbc.gridy = 14;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 0.125;
            gbc.weighty = 0.06;
            gbc.ipadx = 0;
            gbc.ipady = 0;
            add(comment_button,gbc);
            comment_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String a = comment.getText();
                    //댓글 보내기
                }
            });

            like_button = new JButton("like");
            like_button.setBackground(new Color(255,255,255));
            like_button.setSize(150,100);
            gbc.gridx = 7;
            gbc.gridy = 14;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 0.125;
            gbc.weighty = 0.06;
            gbc.ipadx = 0;
            gbc.ipady = 0;
            add(like_button,gbc);

            like_check check = new like_check(like_button, user_id, this.feed_id);
            check.run();

            like_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        // 좋아요 버튼 누르기
                        like like = new like(like_button, user_id, feed_id);
                        like.start();
                        like.join();
                        // 좋아요 여부
                        like_check check = new like_check(like_button, user_id, feed_id);
                        check.run();
                        // 좋아요 갯수
                        like_num like_num = new like_num(like_button, like_num1, feed_id);
                        like_num.start();
                        like_num.join();
                    }catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
    }

}

