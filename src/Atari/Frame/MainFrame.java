package Atari.Frame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    Container frame = getContentPane();
    CardLayout cardLayout = new CardLayout();

    /**
     * 스윙 생성자
     * @param title----제목
     */
    public MainFrame(String title) {
        super(title);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initlayout();
        this.setVisible(true);
    }


    private void initlayout() {
        frame.add(creatCardLayout());
    }

    /**
     * 카드패널을 만들고 반환하는 함수
     * @return cardPanel -- 카드 패널 반환
     */
    private JPanel creatCardLayout(){
        StartPanel startPanelm = new StartPanel();
        GamePanel gamePanelm = new GamePanel();
        //키드패널 생성
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        //카드레이아웃에 패널들 붙이기
        JPanel startPanel  = startPanelm.createStartPanel(cardLayout,cardPanel);
        cardPanel.add(startPanel,"StartScreen");
        JPanel gamePanel = gamePanelm.createGamePanel(cardLayout,cardPanel);
        cardPanel.add(gamePanel,"GameScreen");

        return cardPanel;
    }

    /**
     * 메인 함수
     * @param args -- 모름 그냥 알아서 생성되는거
     */
    public static void main(String[] args) {
        MainFrame mainFrame =new MainFrame("Pong");
    }
}

