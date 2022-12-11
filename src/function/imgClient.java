package function;

import chatting.chatting_client;
import chatting.directorychoose;
import chatting.protocol;

import javax.sound.sampled.*;
import java.io.*;
import java.net.Socket;

public class imgClient{
    Socket socket = null;
    private String filetype;
    chatting_client A=null;
    String roomnumber;
    int type;
    String time;
    public String filename;
    String path;
    public String user_id;

    public imgClient(String user_id, String fileName, String time, chatting_client A) {
        this.user_id = user_id;
        this.filename = fileName;
        this.time = time;
        this.A = A;
        sending tmp =new sending();
        tmp.run(); // 쓰레드 시작
    }
    public String getName(){return (time + filetype);}
    public class sending implements Runnable{
        public void run() {
            try {
                Socket S = new Socket("swiftsjh.tplinkdns.com", 9798); //서버에 접속

                String filetype1 = filename.substring(filename.lastIndexOf("."));
                filetype = filetype1.toLowerCase();
                OutputStream os = S.getOutputStream();
                PrintWriter pw = new PrintWriter(os);
                pw.println(time);
                pw.flush();
                pw.println(filetype);
                pw.flush();


                // 파일 전송
                File file = new File(filename);
                pw.println((int) file.length()); //4파일크기
                pw.flush();
                os = S.getOutputStream();
                DataOutputStream dataOutput = new DataOutputStream(os); //output 스크림 생성
                System.out.println((int) file.length()); // 송신 파일 사이즈 콘솔출력
                System.out.println("filetype:" + filetype);
                pw.println(user_id);
                pw.flush();


                int totalSize = (int) file.length(); // 전송할 파일 사이즈
                int totalSize2 = (int) file.length(); // 아래에서 파일 전송시 totalsize 변수 크기가 변해서 원래 파일 크기 기억용 변수 만듬
                byte[] bytes = new byte[104857600];  //100MB 저장할 바이트 배열
                DataInputStream dataInput = new DataInputStream(new FileInputStream(filename));
                BufferedOutputStream  bufferedOutput = new BufferedOutputStream(dataOutput);
                int i = 0;     //buf 배열 인덱스용 변수
                int progress = 0; //몇 바이트가 전송됬는지 표시하는 변수

                //전송받은 파일 사이즈가 100MB 보다 크다면 100MB 단위로 배열에 저장 후 소켓 버퍼에 write 하고
                //소켓 버퍼에 write한 100MB만큼을  파일 사이즈에서 제외하는 while문!!!
                while (totalSize > 104857600) {
                    while (i < 104857600) {
                        bytes[i] = (byte) dataInput.read();
                        i++;
                        if (progress / (float) totalSize2 * 100 % 0.5 == 0) {
                            System.out.println("전송 진행률: " + String.format("%.1f", progress / (float) totalSize2 * 100) + "%");
                        }
                        progress++;
                    }//while(i < 104857600)문
                    totalSize -= 104857600;   //파일사이즈 - 100MB
                    i = 0;                    //배열 인덱스 초기화
                    bufferedOutput.write(bytes);   //소켓 버퍼에 write
                }//while(totalSize > 104857600)문
                //100MB보다 같거나 작은 남은 사이즈 혹은 원래의 사이즈가 100MB 보다 작을 시 if문 내용이 실행 되어
                //소켓 버퍼에 write 함
                if (totalSize <= 104857600) {
                    i = 0;        //배열 인덱스 초기화
                    bytes = new byte[totalSize];
                    while (i < totalSize) {
                        bytes[i] = (byte) dataInput.read();
                        i++;           //배열인덱스 이동
                        if (progress / (float) totalSize2 * 100 % 0.5 == 0) {
                            System.out.println("전송 진행률: " + String.format("%.1f", progress / (float) totalSize2 * 100) + "%");
                        }
                        progress++;
                    }//while문
                    bufferedOutput.write(bytes);  //소켓 버퍼에 write
                }//if문
                bufferedOutput.flush();
                abc();

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("file transfer complete");
        }
    }
    public void abc() {
        File bgm;
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;
        bgm = new File("Audio/1.wav"); // 사용시에는 개별 폴더로 변경할 것
        Clip clip;
        try {
            stream = AudioSystem.getAudioInputStream(bgm);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip)AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            System.out.println("err : " + e);
        }
    }
}


