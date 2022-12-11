package display;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;

import chatting.ListeningThread;
import chatting.chatting_client;
import function.*;

public class Login extends JFrame {
    private JTextField txtId;
    private JButton OKButton;
    private JPanel mainPanel;
    private JButton signup;
    private JLabel Icon;
    private JPasswordField txtpwd;

    public String makedir(){

        String path = "chatting_data";
        File Folder = new File(path);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try{
                Folder.mkdir(); //폴더 생성합니다.
                System.out.println("폴더가 생성되었습니다.");
                return Folder.getAbsolutePath();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }else {
            System.out.println("이미 폴더가 생성되어 있습니다.");
            return null;
        }
        return null;
    }


    public Login() {
        // 캐싱된 체팅 파일들 모두 지우기
        makedir();
        new clean_cache("chatting_data/");
        txtId.setText("전화번호, 사용자이름 또는 이메일");
        txtpwd.setText("비밀번호486");
        ImgSetSize mainphoto = new ImgSetSize("src/IMG/login.png", 800, 400);
        Icon.setIcon(mainphoto.getImg());
        Icon.setVisible(true);

        setContentPane(mainPanel);

        setSize(850, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0,0,850,1000);
        setTitle("AI-DB Instagram LogIn System");
        setVisible(true);

        // 마우스 클릭 했을 때 텍스트 지우기
        txtId.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭했을때
                txtId.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                if(txtId.getText().equals("")){
                    txtId.setText("전화번호, 사용자이름 또는 이메일");
                }
            }
        });

        txtpwd.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭했을때
                txtpwd.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                String pwd = new String(txtpwd.getPassword());
                if (pwd.equals("")) {
                    txtpwd.setText("비밀번호486");
                }
            }
        });

        // 엔터키 눌렀을 때 로그인, 탭키를 눌렀을 때 다음칸으로 이동하고 택스트 지우기
        txtId.setFocusTraversalKeysEnabled(false);
        txtId.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    OKButton.doClick();
                }
                else if(e.getKeyCode() == KeyEvent.VK_TAB){
                    txtpwd.setText("");
                    txtpwd.requestFocus();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        txtpwd.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    OKButton.doClick();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });


        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id= txtId.getText();
                char[] pw= txtpwd.getPassword();
                String password = new String(pw);
                System.out.println("ID: " + id + "\tPWD: " + password + "\n");
                loginregister manager = new loginregister();
                int session_id = manager.login(id,password);
                if(session_id!=-1){
                    chatting_client client = new chatting_client(id);
                    client.run();
                    ListeningThread t1 = client.get_listening();
                    mainFeed a = new mainFeed(session_id,id,client,t1);
                    setVisible(false);
                    a.setVisible(true);
                }
                else{
                    System.out.println("x");
                }
            }
        });
        signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Signup sign = new Signup();
                setVisible(false);
                sign.setVisible(true);
            }
        });
    }
}

