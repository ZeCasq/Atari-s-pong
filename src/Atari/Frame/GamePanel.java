package Atari.Frame;

import javax.swing.*;
import java.awt.*;

public class GamePanel {
    /**
     * 게임 화면 만드는 함수
     *
     * @param cardLayout -- 카드레이아웃으로 관리할거라 시작 패널이 붙을 카드 레이아웃 불러옴
     * @param cardPanel -- 위와 같은 이유로 불러옴
     * @return 게임 패널 반환
     */
    JPanel createGamePanel(CardLayout cardLayout, JPanel cardPanel){
        JPanel gamePanel = new JPanel();
        JLabel test = new JLabel("test");

        gamePanel.add(test);
        return gamePanel;
    }
}
