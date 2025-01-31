package Atari.Frame;


import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    static public JFrame frame = new JFrame();
    CardLayout cardLayout = new CardLayout();




    static private void initlayout() {
        frame.add(creatCardLayout());
    }

    /**
     * 카드패널을 만들고 반환하는 함수
     * @return cardPanel -- 카드 패널 반환
     */
    private static JPanel creatCardLayout(){
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        StartPanel startPanelm = new StartPanel();
        PausePanel pausePanelm = new PausePanel();

        //키드패널 생성


        //카드레이아웃에 패널들 붙이기
        EndPanel endPanel = new EndPanel(cardLayout,cardPanel);
        cardPanel.add(endPanel,"EndPanel");
        GamePanel gamePanel = new GamePanel(cardLayout,cardPanel,endPanel);
        cardPanel.add(gamePanel,"GameScreen");
        JPanel startPanel  = startPanelm.createStartPanel(cardLayout,cardPanel,gamePanel);
        cardPanel.add(startPanel,"StartScreen");
        JPanel pausePanel = pausePanelm.createPausePanel(cardLayout,cardPanel,gamePanel);
        cardPanel.add(pausePanel,"PausePanel");


        cardLayout.show(cardPanel,"StartScreen");


        return cardPanel;
    }

    /**
     * 메인 함수
     * @param args -- 모름 그냥 알아서 생성되는거
     */
    public static void main(String[] args) {
        frame = new JFrame("Projectile Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false); //화면 크기 고정
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        initlayout();
    }
}

