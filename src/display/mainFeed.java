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

        dm a = new dm(session,client,user_id,t1);
        setVisible(false);
        a.setVisible(true);
        setVisible(false);




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
        //icon.setIcon(mainphoto.getImg());

        ImgSetSize storyEdit = new ImgSetSize("src/IMG/story.jpg", 70, 100);
        //story.setIcon(storyEdit.getImg());

        ImgSetSize add_size = new ImgSetSize("src/IMG/addfeed.png", 50, 50);
        //add.setIcon(add_size.getImg());

        ImgSetSize notification = new ImgSetSize("src/IMG/likes2.png", 50, 50);
        //heart.setIcon(notification.getImg());

        ImgSetSize dm_size = new ImgSetSize("src/IMG/dm.jpg", 50, 50);
        //dm.setIcon(dm_size.getImg());



        //feedscroll.getVerticalScrollBar().setUnitIncrement(15);

        GridBagLayout Gbag = new GridBagLayout();
        //feed.setLayout(Gbag);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;


        //feedscroll.setViewportView(feed);
        //feedscroll.setVisible(true);
        //feed.setVisible(true);
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



        dm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dm a = new dm(session,client,user_id,t1);
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

            }

        }
    }



