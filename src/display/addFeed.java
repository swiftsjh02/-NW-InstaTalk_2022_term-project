package display;

import chatting.ListeningThread;
import chatting.chatting_client;
import chatting.filechoose;
import chatting.protocol;
import function.ImgSetSize;
import function.get_data;
import function.imgClient;
import function.imgchoose;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class addFeed extends JFrame{
    private JButton share;
    private JButton cancel;
    private JLabel newPost;
    private JLabel music;
    private JLabel location;
    private JLabel other;
    private JLabel facebook;
    private JLabel twitter;
    private JLabel tumblr;
    private JLabel settings;
    private JTextField wordText;
    private JPanel main;
    private JTextField tag;
    private JButton picture;
    private String imgName;


    private ArrayList<String> Tag = new ArrayList<>();

    addFeed(int session, String user_id, chatting_client client, ListeningThread t1){


        ImgSetSize image = new ImgSetSize("src/IMG/addfeed_image.png", 50, 50);
        picture.setIcon(image.getImg());
        setContentPane(main);

        setSize(850, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0,0,850,1000);
        picture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imgchoose choice = new imgchoose();
                String filename = choice.jFileChooserUtil();
                imgName = filename;
            }
        });

        share.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = wordText.getText();
                String tag_not_split = tag.getText();

                String tag_split[] = tag_not_split.split("#");

                for(int i =1;i < tag_split.length;i++){
                    Tag.add(tag_split[i].trim());
                    System.out.println(tag_split[i]);
                }
                protocol q = new protocol();
                q.setTime();
                String time = q.getTime();


                imgClient imgCl = new imgClient(user_id,imgName,time,client);
                String file_name = imgCl.getName();

                client.chat_message(new protocol(17,user_id,message,Tag,file_name));

                mainFeed a = new mainFeed(session,user_id,client,t1);
                setVisible(false);
                a.setVisible(true);
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFeed a = new mainFeed(session,user_id,client,t1);
                setVisible(false);
                a.setVisible(true);
            }
        });
    }
}
