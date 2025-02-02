package Atari.Frame;

import Atari.System.Game;

import javax.swing.*;
import java.awt.*;

public class PausePanel  {
    public JPanel createPausePanel(CardLayout cardLayout, JPanel cardPanel,GamePanel gamePanel) {
        JPanel PausePanel  = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // 여백 설정
        gbc.insets = new Insets(0, 20, 50, 10);

        //재개 버튼
        JButton restart = new JButton("재개");
        restart.addActionListener(e -> {
            Game game = Game.Instance;
            cardLayout.show(cardPanel,"GameScreen");
            gamePanel.requestFocusInWindow();
            game.startCountDown_restart();
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        PausePanel.add(restart, gbc);

        //메인 홈으로 가는 버튼
        JButton home = new JButton("홈으로");
        home.addActionListener(e -> {
            cardLayout.show(cardPanel,"StartScreen");
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        PausePanel.add(home, gbc);

        return PausePanel;

    }
}
