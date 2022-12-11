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
    private JTextField fullNameTextField;
    private JTextField mobileNumberOrEmailTextField;
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
        mobileNumberOrEmailTextField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mobileNumberOrEmailTextField.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {
                if(mobileNumberOrEmailTextField.getText().equals("")){
                    mobileNumberOrEmailTextField.setText("Mobile Number or Email");
                }
            }
        });

        fullNameTextField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fullNameTextField.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {
                if(fullNameTextField.getText().equals("")){
                    fullNameTextField.setText("Full Name");
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
                    email.setText("Username");
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
        mobileNumberOrEmailTextField.setFocusTraversalKeysEnabled(false);
        mobileNumberOrEmailTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_TAB){
                    fullNameTextField.requestFocus();
                    fullNameTextField.setText("");
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        fullNameTextField.setFocusTraversalKeysEnabled(false);
        fullNameTextField.addKeyListener(new KeyListener() {
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