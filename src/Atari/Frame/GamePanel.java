package Atari.Frame;

import Atari.Object.*;
import Atari.System.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
    게임의 ui및 키입력을 나타내는 클래스
    엔드 페이지 확인 하려면 키보드에서 R이나 T눌러 보삼 KeyRelease에도 써있긴함
 */
public class GamePanel extends JPanel {
    JLabel score1;
    JLabel score2;
    CardLayout cardLayout;
    JPanel cardPanel;
    EndPanel endPanel;
    boolean keyEnabled = true;


    public GamePanel(CardLayout cardLayout, JPanel cardPanel,EndPanel endPanel) {
        this.cardLayout =cardLayout;
        this.cardPanel = cardPanel;
        this.endPanel = endPanel;

        Game game = Game.Instance;
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // 여백 설정
        gbc.insets = new Insets(0, 200, 240, 200);

        //일시정지 설명 라벨 표기

        JLabel explain = new JLabel("esc를 누르면 일시정지 됩니다");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(explain,gbc);

        gbc.insets = new Insets(0, 100, 250, 100);
        score1 = new JLabel(String.valueOf(game.getScore1()));
        score1.setFont(new Font("Arial",Font.BOLD,50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(score1,gbc);

        //스코어 표시
        score2 = new JLabel(String.valueOf(game.getScore2()));
        score2.setFont(new Font("Arial",Font.BOLD,50));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(score2,gbc);

        //키 감지하는 함수
            /*알고리즘 자체는 키를 누르고 있을 때 해당하는 변수를 true로 바꾸고 그 키를 땔 때 false로 바꾸는 것으로
            쓰   레드에서 만약 해당하는 변수의 값이 true면 그에 맞게 벽이 움직이도록 설정함
             */
        this.addKeyListener(new KeyListener() {


                                     @Override
                                     //key가 눌려 문자가 완성 될 때 발생하는 이벤트 쓸 일 없을거 같은데 아마 동시 처리에 실마리가 될지도
                                     public void keyTyped(KeyEvent e) {
                                     }

                                     @Override
                                     //key가 눌릴 때 발생하는 이벤트
                                     public void keyPressed(KeyEvent e) {
                                         int key = e.getKeyCode();// key 입력받은 값을 저장하는 변수
                                         //왼쪽 화살표 누를때 오른쪽 벽 상승
                                         if (key == KeyEvent.VK_LEFT) {
                                             game.getWallMove2().isUp = true;


                                         }
                                         //오른쪽 화살표 누를 떄 오른쪽 벽 하락
                                         if (key == KeyEvent.VK_RIGHT) {
                                             game.getWallMove2().isDown = true;
                                         }
                                         //key A가 눌릴 때 왼쪽 벽 상승
                                         if (key == KeyEvent.VK_A) {
                                             game.getWallMove1().isUp = true;
                                         }
                                         //key D가 눌릴 때 오른쪽 벽 하락
                                         if (key == KeyEvent.VK_D) {
                                             game.getWallMove1().isDown = true;
                                         }

                                     }

                                     //키가 눌렸다 때질 때 이벤트 처리
                                     @Override
                                     public void keyReleased(KeyEvent e) {
                                         int key = e.getKeyCode();
                                         //엔드 페이지 테스트 용
                                         if(key == KeyEvent.VK_R){
                                             game.setScore1(10);
                                         }
                                         if(key == KeyEvent.VK_T){
                                             game.setScore2(10);
                                         }
                                         //esc 눌렀을 때 퍼즈
                                         if(key == KeyEvent.VK_ESCAPE){
                                             cardLayout.show(cardPanel,"PausePanel");
                                             game.gamepause();
                                         }
                                         if (key == KeyEvent.VK_LEFT) {
                                             game.getWallMove2().isUp = false;
                                         }
                                         if (key == KeyEvent.VK_RIGHT) {
                                             game.getWallMove2().isDown = false;
                                         }
                                         if (key == KeyEvent.VK_A) {
                                             game.getWallMove1().isUp = false;
                                         }
                                         if (key == KeyEvent.VK_D) {
                                             game.getWallMove1().isDown = false;
                                         }
                                     }
                                 }
        );
    }


    @Override
    protected void paintComponent(Graphics g) {
        Game game = Game.Instance;
        score1.setText(String.valueOf(game.getScore1()));
        score2.setText(String.valueOf(game.getScore2()));
        super.paintComponent(g);
        for(Ball ball : game.getBalls()){//ArrayList에 공 객체 담고 리스트안에 있는 객체들 모두 불러와서 그림 그림
            // 초기 위치
            // 현재 위치 계산
            double x = ball.calculateX();
            double y = ball.calculateY();

            ball.drawX += (int) (x);
            ball.drawY -= (int) (y);

            g.setColor(Color.BLUE);
            g.fillOval(ball.drawX, ball.drawY, ball.getWidth(), ball.getHeight()); // 물체 그리기 x 지름 y지름 모두 10인 타원 즉, 반지름이 5인 원

            //벽 만드는 코드
            g.setColor(Color.BLACK);
            g.drawRect(game.getWall1().startX,game.getWall1().startY,game.getWall1().width,game.getWall1().height);
            g.drawRect(game.getWall2().startX,game.getWall2().startY,game.getWall2().width,game.getWall2().height);
            //중앙선 그리는 코드
            g.setColor(Color.BLACK);
            g.drawLine(400,50,400,500);

            if (ball.drawX <= 0) {
                game.score2Up();
                game.getBalls().clear();
                game.getBalls().add(new Ball());

            }
            if( ball.drawX + 6 >= getWidth()){
                game.score1Up();
                game.getBalls().clear();
                game.getBalls().add(new Ball());
            }

            if (game.getCountdown()> 0) {
                g.setFont(new Font("Arial", Font.BOLD,100));
                g.setColor(Color.BLACK);
                String countdownText = String.valueOf(game.getCountdown());
                FontMetrics fm = g.getFontMetrics();
                int textWidth = fm.stringWidth(countdownText);
                int textHeight = fm.getAscent();

                g.drawString(countdownText, getWidth() / 2 - textWidth / 2, getHeight() / 2 + textHeight / 2);
            }
            //벽판정
            //왼
            int wall1Right = game.getWall1().startX + game.getWall1().width;
            int wall1Left = game.getWall1().startX;
            int wall1Top = game.getWall1().startY + game.getWall1().height;
            int wall1Bottom = game.getWall1().startY;

            if (ball.drawX <= wall1Right && ball.drawX >= wall1Left) {
                if (ball.drawY >= wall1Bottom && ball.drawY <= wall1Top) {
                    ball.drawX = wall1Right;
                    ball.reflectX();
                }
            }

            //우
            int wall2Right = game.getWall2().startX + game.getWall2().width;
            int wall2Left = game.getWall2().startX;
            int wall2Top = game.getWall2().startY + game.getWall2().height;
            int wall2Bottom = game.getWall2().startY;

            if ((ball.drawX + ball.getWidth() >= wall2Left  && ball.drawX + ball.getWidth() <= wall2Right)) { // drawX와 drawY는 공의 왼쪽 상단 모서리 좌표가 기준임.
                if (ball.drawY >= wall2Bottom && ball.drawY <= wall2Top)    // 때문에 벽의 아랫부분으로 공을 칠 때는 판정이 후하고, 위쪽으로 칠 때는 짤 수도 있음. 필요시 조정하면 됨.
                ball.reflectX();
            }


            if (ball.drawY <= 0 || ball.drawY + 6 >= getHeight()) { //frame height 크기가 600이기 때문.

                ball.reflectY();
            }
        }
    }



    /**
     * 엔드 페이지로 보내는 함수
     */
    public void goEnd(){
        Game game = Game.Instance;
        endPanel.setWin_player(game.getWinner());
        cardLayout.show(cardPanel,"EndPanel");
    }


}
