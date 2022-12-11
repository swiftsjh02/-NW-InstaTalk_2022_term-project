package display;

import chatting.ListeningThread;
import chatting.chatting_client;
import function.ImgSetSize;
import javax.swing.*;

public class post extends JFrame {
    private JButton like;
    private JButton comment;
    private JButton share;
    private JButton bookmark;
    private JButton profile;
    private JButton userid;
    private JLabel feedImg;
    private JTextArea commentBox;
    private JButton upload;
    private JPanel main;

    post(int session, String user_id, chatting_client client, ListeningThread t1) {
        ImgSetSize profilebt = new ImgSetSize("src/IMG/testprofile.png", 50, 50);
        profile.setIcon(profilebt.getImg());

        userid.setText("younha_holic");

        ImgSetSize feed = new ImgSetSize("src/IMG/storytest.png", 400, 400);
        feedImg.setIcon(feed.getImg());

        ImgSetSize likebt = new ImgSetSize("src/IMG/notification.jpg", 50, 50);
        like.setIcon(likebt.getImg());

        ImgSetSize commentbt = new ImgSetSize("src/IMG/notification.jpg", 50, 50);
        comment.setIcon(commentbt.getImg());

        ImgSetSize sharebt = new ImgSetSize("src/IMG/dm.jpg", 50, 50);
        share.setIcon(sharebt.getImg());

        ImgSetSize bookmarkbt = new ImgSetSize("src/IMG/dm.jpg", 50, 50);
        bookmark.setIcon(bookmarkbt.getImg());

        setContentPane(main);
        setSize(600,1100);
        setVisible(true);
    }
}
