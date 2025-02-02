package Atari.Frame;

import Atari.System.Game;

import javax.swing.*;
import java.awt.*;

public class EndPanel extends JPanel{
    int win_player = 0;
    JLabel winner;

    public EndPanel(CardLayout cardLayout, JPanel cardPanel) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Game game = Game.Instance;
        // 여백 설정
        gbc.insets = new Insets(0, 20, 50, 10);
        winner = new JLabel( "Player" + game.getWinner() +"Win!");
        winner.setFont(new Font("Arial",Font.BOLD,50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(winner,gbc);

        //재개 버튼
        JButton restart = new JButton("재시작");
        restart.addActionListener(e -> {

            game.gameset();
            cardLayout.show(cardPanel,"GameScreen");

            game.getGamePanel().requestFocusInWindow();
            game.start();
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(restart, gbc);

        //메인 홈으로 가는 버튼
        JButton home = new JButton("홈으로");
        home.addActionListener(e -> {
            cardLayout.show(cardPanel,"StartScreen");
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(home, gbc);

    }

    public void setWin_player(int win_player) {
        this.win_player = win_player;
        this.winner.setText("Player" +win_player +" Win!");
    }

}
