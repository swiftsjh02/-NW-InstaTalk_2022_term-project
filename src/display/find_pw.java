package display;

import function.get_data;
import function.md5;

import javax.swing.*;
import java.awt.event.*;
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
        setTitle("Find_PW");
        setVisible(true);

        ID.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    FindButton.doClick();
                }else if(e.getKeyCode() == KeyEvent.VK_TAB){
                    phone.setText("");
                    phone.requestFocus();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        nickname.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    FindButton.doClick();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        phone.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    FindButton.doClick();
                }else if(e.getKeyCode() == KeyEvent.VK_TAB){
                    ID.setText("");
                    ID.requestFocus();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        ID.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭했을때
                ID.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                if(ID.getText().equals("")){
                    ID.setText("아이디(이메일)");
                }
            }
        });

        phone.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭했을때
                phone.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                if(phone.getText().equals("")){
                    phone.setText("전화번호");
                }
            }
        });


        nickname.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭했을때
                nickname.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                if(nickname.getText().equals("")){
                    nickname.setText("닉네임");
                }
            }
        });

        FindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname = find_pw.this.nickname.getText();
                String phone = find_pw.this.phone.getText();
                String id = find_pw.this.ID.getText();
                get_data getData = new get_data();
                getData.setType9(id,nickname,phone);
                getData.start();
                boolean response = getData.getTf();
                if(!response){//만약 입력 정보 틀리면
                    JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.");
                }
                else{// 새로운 비밀번호 변경 알림창 띄우기
                    String pwdtochange = null;
                    pwdtochange = JOptionPane.showInputDialog("바꿀 비밀번호를 입력하세요");
                    getData.setType10(id,pwdtochange);
                    getData.start();

                    if(getData.getTf()){
                        JOptionPane.showMessageDialog(null, "비밀번호 변경 완료");
                        Login a = new Login();
                        setVisible(false);
                        a.setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "비밀번호 변경 실패");
                    }
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
