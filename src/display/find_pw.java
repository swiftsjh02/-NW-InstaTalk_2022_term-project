package display;

import function.get_data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class find_pw extends JFrame {
    private JPanel main;
    private JTextField ID;
    private JTextField nickname;
    private JButton FindButton;
    private JTextField phone;
    private JButton BACKButton;

    public find_pw(){
        setContentPane(main);
        setSize(850, 1000);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBounds(0,0,850,1000);
        setTitle("Find ID");
        setVisible(true);

        FindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname = find_pw.this.nickname.getText();
                String phone = find_pw.this.phone.getText();
                String id = find_pw.this.ID.getText();
                get_data getData = new get_data();
                getData.setType8(nickname, phone);
                getData.start();
                if(id==null){
                    JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.");
                }
                else{
                    JOptionPane.showMessageDialog(null, "ID : " + id);
                }
            }
        });


        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login a = new Login();
                a.setVisible(true);
                dispose();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
