package display;

import javax.swing.*;

import chatting.ListeningThread;
import chatting.chatting_client;
import function.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class reels extends JFrame{
    private JPanel main;
    private JButton userhomeButton;
    private JButton homeButton;
    private JButton userhome;
    private JButton reelsButton;
    private JButton searchButton;
    private JLabel camera;
    private JButton imageButton;
    private JButton likeButton;
    private JButton moreButton;
    private JButton dmButton;
    private JButton commentsButton;
    private JButton follow;
    private JPanel bottom;
    private JButton shopButton;

    public reels(int session, String user_id, chatting_client client, ListeningThread t1){
        ImgSetSize home = new ImgSetSize("src/IMG/reels_home.jpg", 50, 50);
        homeButton.setIcon(home.getImg());

        ImgSetSize search = new ImgSetSize("src/IMG/reels_search.jpg", 50, 50);
        searchButton.setIcon(search.getImg());

        ImgSetSize reels = new ImgSetSize("src/IMG/reels_reels.jpg", 50, 50);
        reelsButton.setIcon(reels.getImg());

        ImgSetSize shop = new ImgSetSize("src/IMG/reels_shop.jpg", 50, 50);
        shopButton.setIcon(shop.getImg());

        ImgSetSize userHome = new ImgSetSize("src/IMG/reels_user.jpg", 50, 50);
        userhomeButton.setIcon(userHome.getImg());

        ImgSetSize camera1 = new ImgSetSize("src/IMG/camera1.png", 50,50);
        camera.setIcon(camera1.getImg());

        ImgSetSize like = new ImgSetSize("src/IMG/reels_like.png", 50,50);
        likeButton.setIcon(like.getImg());

        ImgSetSize comments = new ImgSetSize("src/IMG/reels_comment.png", 50,50);
        commentsButton.setIcon(comments.getImg());

        ImgSetSize dm = new ImgSetSize("src/IMG/reels_dm.png", 50,50);
        dmButton.setIcon(dm.getImg());

        ImgSetSize more = new ImgSetSize("src/IMG/reels_more.png", 50,50);
        moreButton.setIcon(more.getImg());


        setContentPane(main);
        setSize(850,1000);
        bottom.setSize(850,50);
        setBounds(0,0,850,1000);
       //setVisible(true);
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

    }
}
