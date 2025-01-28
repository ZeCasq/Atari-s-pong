package Atari.Frame;

import Atari.Object.*;
import Atari.System.*;
import Atari.Object.Wall;
import Atari.Object.WallMove;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


/**
    게임의 ui및 키입력을 나타내는 클래스
 */
public class GamePanel extends JPanel {

    public GamePanel() {

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
                                             Game.getWallMove2().isUp = true;


                                         }
                                         //오른쪽 화살표 누를 떄 오른쪽 벽 하락
                                         if (key == KeyEvent.VK_RIGHT) {
                                             Game.getWallMove2().isDown = true;
                                         }
                                         //key A가 눌릴 때 왼쪽 벽 상승
                                         if (key == KeyEvent.VK_A) {
                                             Game.getWallMove1().isUp = true;
                                         }
                                         //key D가 눌릴 때 오른쪽 벽 하락
                                         if (key == KeyEvent.VK_D) {
                                             Game.getWallMove1().isDown = true;
                                         }

                                     }

                                     //키가 눌렸다 때질 때 이벤트 처리
                                     @Override
                                     public void keyReleased(KeyEvent e) {
                                         int key = e.getKeyCode();
                                         if (key == KeyEvent.VK_LEFT) {
                                             Game.getWallMove2().isUp = false;
                                         }
                                         if (key == KeyEvent.VK_RIGHT) {
                                             Game.getWallMove2().isDown = false;
                                         }
                                         if (key == KeyEvent.VK_A) {
                                             Game.getWallMove1().isUp = false;
                                         }
                                         if (key == KeyEvent.VK_D) {
                                             Game.getWallMove1().isDown = false;
                                         }
                                     }
                                 }
        );
    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        for(Ball ball : Game.getBalls()){//ArrayList에 공 객체 담고 리스트안에 있는 객체들 모두 불러와서 그림 그림
            // 초기 위치
            // 현재 위치 계산
            double x = ball.calculateX();
            double y = ball.calculateY();
            ball.drawX += (int) (x);
            ball.drawY -= (int) (y);

            // 땅에 닿기 전까지 그리기
            if (true) {  // y좌표계는 위로 올라갈 수록 작은 거임.
                g.setColor(Color.BLUE);
                g.fillOval(ball.drawX, ball.drawY, 10, 10); // 물체 그리기 x 지름 y지름 모두 10인 타원 즉, 반지름이 5인 원

            }
            //벽 만드는 코드
            g.setColor(Color.BLACK);
            g.drawRect(Game.getWall1().startX,Game.getWall1().startY,Game.getWall1().width,Game.getWall1().height);
            g.drawRect(Game.getWall2().startX,Game.getWall2().startY,Game.getWall2().width,Game.getWall2().height);
            //중앙선 그리는 코드
            g.setColor(Color.BLACK);
            g.drawLine(400,50,400,500);

            if (ball.drawX <= 0 || ball.drawX + 6 >= getWidth()) {

                ball.reflectX();
            }
            //벽판정
            //왼
            if ((ball.drawX <= Game.getWall1().startX +Game.getWall1().width &&ball.drawX >= Game.getWall1().startX )&&(ball.drawY >= Game.getWall1().startY && ball.drawY <= Game.getWall1().startY +Game.getWall1().height)) {

                ball.reflectX();
            }
            //우
            if ((ball.drawX >= Game.getWall2().startX -Game.getWall2().width&&ball.drawX <= Game.getWall2().startX)&&(ball.drawY >= Game.getWall2().startY && ball.drawY <= Game.getWall2().startY +Game.getWall1().height)) {
                ball.reflectX();
            }



            if (ball.drawY <= 0 || ball.drawY + 6 >= getHeight()) { //frame height 크기가 600이기 때문.

                ball.reflectY();
            }
        }
    }
}
