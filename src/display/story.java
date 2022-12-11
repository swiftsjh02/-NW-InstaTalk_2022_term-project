package display;

import chatting.ListeningThread;
import chatting.chatting_client;
import function.ImgSetSize;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class story extends JFrame{

    public JPanel main;
    private JButton homeButton;
    private JButton searchButton;
    private JButton reelsButton;
    private JButton shopButton;
    private JPanel bottom;
    private JTextField sendMessageTextField;
    private JButton story_dm;
    private JButton like;
    private JButton exit;
    private JPanel top;
    private JButton more;

    public story(int session_id, String user_id, chatting_client client, ListeningThread t1){


        setContentPane(main);
        setSize(850,1000);
        bottom.setSize(850,50);
        setBounds(0,0,850,1000);
        setVisible(true);
        //dispose();

        ImgSetSize storyLike = new ImgSetSize("src/IMG/reels_like.png", 50, 50);
        like.setIcon(storyLike.getImg());

        ImgSetSize storyDm = new ImgSetSize("src/IMG/reels_dm.png", 50, 50);
        story_dm.setIcon(storyDm.getImg());

        ImgSetSize storyExit = new ImgSetSize("src/IMG/exit.png", 50, 50);
        exit.setIcon(storyExit.getImg());

        ImgSetSize storyMore = new ImgSetSize("src/IMG/reels_more.png", 50, 50);
        more.setIcon(storyMore.getImg());



        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFeed a = new mainFeed(session_id,user_id,client,t1);
                setVisible(false);
                a.setVisible(true);
            }
        });
    }

}
