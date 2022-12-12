package display;

import function.loginregister;

import javax.swing.*;
import java.awt.event.*;

public class Signup extends JFrame{
    private JPanel panel1;
    private JTextField namefield;
    private JTextField nicknamefield;
    private JTextField birthfield;
    private JTextField Identifier;
    private JButton button1;
    private JButton backButton;
    private JPasswordField pwd;


    private boolean isIdPossible = false;
    private boolean isSuccess = false;

    public Signup(){
        setContentPane(panel1);
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setBounds(0,0,1000,700);
        loginregister registermanager= new loginregister();
        setTitle("signup");
        setVisible(true);

        // 마우스 클릭 했을 때 텍스트 지우기
        namefield.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                namefield.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {
                if(namefield.getText().equals("")){
                    namefield.setText("Name");
                }
            }
        });

        nicknamefield.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nicknamefield.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {
                if(nicknamefield.getText().equals("")){
                    nicknamefield.setText("Nick-name");
                }
            }
        });
        birthfield.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                birthfield.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {
                if(birthfield.getText().equals("")){
                    birthfield.setText("OOOO-OO-OO");
                }
            }
        });

        Identifier.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Identifier.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {
                if(Identifier.getText().equals("")){
                    Identifier.setText("ID or E-mail");
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
        namefield.setFocusTraversalKeysEnabled(false);
        namefield.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_TAB){
                    nicknamefield.requestFocus();
                    nicknamefield.setText("");
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        nicknamefield.setFocusTraversalKeysEnabled(false);
        nicknamefield.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_TAB){
                    Identifier.requestFocus();
                    Identifier.setText("");
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        Identifier.setFocusTraversalKeysEnabled(false);
        Identifier.addKeyListener(new KeyListener() {
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




        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] password= pwd.getPassword();
                String emailfromclient= Identifier.getText();
                System.out.println("ID: " + emailfromclient + "\tPWD: " + password + "\n");
                String pwd_chartostr = new String(password);
                int status= registermanager.register(emailfromclient,pwd_chartostr);
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login b = new Login();
                setVisible(false);
                b.setVisible(true);
            }
        });

    }
}