package Atari.Frame;

import Atari.System.Game;

import javax.swing.*;
import java.awt.*;

public class StartPanel {
    /**
     * 시작화면을 만들고 반환하는 함수
     * GridBagLayout() 공부하고 써라 이게 정답인거 같다. 한번 찾아보도록.
     * @param cardLayout -- 카드레이아웃으로 관리할거라 시작 패널이 붙을 카드 레이아웃 불러옴
     * @param cardPanel -- 위와 같은 이유로 불러옴
     *
     * @return startPanel -- 시작 화면 패널 반환
     */
    JPanel createStartPanel(CardLayout cardLayout, JPanel cardPanel,GamePanel gamePanel){
        //시작 화면 패널 생성
        JPanel startPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // 여백 설정
        gbc.insets = new Insets(0, 20, 50, 10);

        //게임 제목 표기

        JLabel title = new JLabel("Pong");
        title.setFont(new Font("Arial",Font.BOLD,50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        startPanel.add(title,gbc);
        //시작버튼
        JButton start =new JButton("시작");
        start.addActionListener(e ->{
            Game game = Game.Instance;
            game.settingGamePanel(gamePanel);
            game.gameset();

            cardLayout.show(cardPanel,"GameScreen");
            game.start();
            gamePanel.requestFocusInWindow(); //키보드 입력을 받을 수 있도록 포커스를 요청하는 함수
            gamePanel.revalidate();
            gamePanel.repaint();

        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        startPanel.add(start,gbc);

        //종료버튼
        JButton end =new JButton("종료");
        end.addActionListener(e -> {
            System.exit(0);
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        startPanel.add(end,gbc);

        JLabel contorl1 = new JLabel("Player1  Up : A , Down : D");
        contorl1.setFont(new Font("Arial",Font.BOLD,20));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        startPanel.add(contorl1,gbc);

        JLabel contorl2 = new JLabel("Player2  Up : <- , Down : ->");
        contorl2.setFont(new Font("Arial",Font.BOLD,20));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        startPanel.add(contorl2,gbc);

        return startPanel;
    }
}
