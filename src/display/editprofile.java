package display;

import chatting.ListeningThread;
import chatting.chatting_client;
import function.ImgSetSize;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editprofile extends JFrame {
    private JPanel main;
    private JTextField nameText;
    private JTextField idText;
    private JTextField introduce;
    private JTextField link;
    private JButton okButton;
    private JButton cancelButton;
    private JLabel pro;
    private JLabel personal;
    private JButton profile;
    private JLabel editImage;
    private JButton profile2;

    private int session;
    private String user_id;

    private chatting_client client;

    private ListeningThread t1;

    public editprofile(int session,String user_id, chatting_client client, ListeningThread t1){
        this.session = session;
        this.user_id = user_id;
        this.client = client;
        this.t1 = t1;

        setContentPane(main);

        setSize(850, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0,0,850,1000);

        ImgSetSize profileLeft = new ImgSetSize("src/IMG/editprofile.jpg", 50, 50);
        profile.setIcon(profileLeft.getImg());

        ImgSetSize profileRight = new ImgSetSize("src/IMG/avatar.jpg", 50, 50);
        profile2.setIcon(profileRight.getImg());
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userFeed a = new userFeed(session,user_id,user_id,client,t1);
                setVisible(false);
                a.setVisible(true);
            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //게시물 데이터 업로드
            }
        });
    }
}
