package display;

import function.loginregister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Signup extends JFrame{
    private JPanel panel1;
    private JTextField id;
    private JTextField email;
    private JButton button1;
    private JPasswordField pwd;
    private JTextField phone;
    private JTextField nickname;
    private JButton btnOk;
    private JLabel lblOk;

    private boolean isIdPossible = false;
    private boolean isSuccess = false;

    public Signup(){
        setContentPane(panel1);
        setSize(850, 1000);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBounds(0,0,850,1000);
        loginregister registermanager= new loginregister();
        setTitle("signup");
        setVisible(true);

        // 마우스 클릭 했을 때 텍스트 지우기
        nickname.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
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

        phone.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
                    phone.setText("휴대폰번호(-없이)");
                }
            }
        });

        email.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                email.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {
                if(email.getText().equals("")){
                    email.setText("이메일(아이디)");
                }
            }
        });

        pwd.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pwd.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {
                String pwdText = new String(pwd.getPassword());
                if(pwdText.equals("")){
                    pwd.setText("비밀번호486");
                }

            }
        });

        // 탭키나 엔터키 눌렀을때의 이벤트
        nickname.setFocusTraversalKeysEnabled(false);
        nickname.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_TAB){
                    phone.requestFocus();
                    phone.setText("");
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        phone.setFocusTraversalKeysEnabled(false);
        phone.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_TAB){
                    email.requestFocus();
                    email.setText("");
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        email.setFocusTraversalKeysEnabled(false);
        email.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_TAB){
                    pwd.requestFocus();
                    pwd.setText("");
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });


        pwd.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    btnOk.doClick();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] password= pwd.getPassword();
                String emailfromclient= email.getText();
                String nicknameclient= nickname.getText();
                String phoneclient= phone.getText();
                System.out.println("ID: " + emailfromclient + "\tPWD: " + password + "\n");
                String pwd_chartostr = new String(password);
                int status= registermanager.register(emailfromclient,pwd_chartostr,nicknameclient,phoneclient);
                if(status!= -1 && status != 2){
                    Login a = new Login();
                    setVisible(false);
                    a.setVisible(true);
                } else if (status==2) {
                    System.out.println("중복된 아이디가 존재합니다.");
                }
                else{
                    System.out.println("알 수 없는 에러");
                }


            }
        });

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Check Duplicated Id...");
                System.out.println(isIdPossible);

                if (isIdPossible == true) {
                    lblOk.setForeground(Color.GREEN);
                    lblOk.setText("Possible");
                }
                else {
                    lblOk.setForeground(Color.RED);
                    lblOk.setText("Impossible");
                }
            }
        });
    }
}