package Atari.System;
import Atari.Frame.GamePanel;
import Atari.Object.*;
import Atari.Object.WallMove;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
     게임의 로직을 표현 하는 클래스
    하나의 객체여야 하는데 외부에서 이 클래스를 호출할 일이 많아 싱글톤 사용
 */
public enum Game{
    Instance;
    private GamePanel gamePanel;      // 연결할 게임 패널 repaint를 요청하기 위해서

    private Wall wall1; //왼쪽 벽
    private Wall wall2; //오른쪽 벽
    private ArrayList<Ball> balls =new ArrayList<>();
    private int interval = 5;
    private double time = 0; // 시간 (초)
    //추후 쓰레드를 위한 변수
    private WallMove wallMove1;
    private WallMove wallMove2;
    private Thread thread1;
    private Thread thread2;
    private Timer timer;


    Game(){
        //그냥 싱글톤은 생성자 받을 수 있는데 멀티 쓰레드 환경에서 위험할 수 있다고 enum을 씀
    }

    /**
     * 생성자를 맡음
     * @param gamePanel 생성자로 gamePanel 받아야해서,,,
     */
    public void settingGamePanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    //점수
    private int score1 = 0;
    private int score2 = 0;

    /**
     * 게임 시작 전 초기 설정해주는 클래스
     */
    public void gameset(){
        score1 = 0;
        score2 = 0;
        balls.clear();
        balls.add(new Ball());

        wall1 = new Wall(20,200,10,100);
        wall2 = new Wall(750,200,10,100);
        wallMove1 = new WallMove(wall1);
        wallMove2 = new WallMove(wall2);
        thread1 = new Thread(wallMove1);
        thread2 = new Thread(wallMove2);
    }

    /**
     * 게임 진행을 맡은 클래스
     */
    public void start(){

        thread1.start();
        thread2.start();

        // 타이머 설정
        //interval 마다 actionPerformed가 실행되는 구조
        // 시간 증가
        //                    if ((int) (time) % 5000 == 0) {     // 일정 시간마다 속도 증가
        //                        speedX *= 1.6;
        //                        speedY *= 1.6;
        //                    }
        // 화면 갱신 여기서 paintComponent 호출
        //System.out.println(thread.getState());
        timer = new Timer(interval, new ActionListener() {//interval 마다 actionPerformed가 실행되는 구조
            @Override
            public void actionPerformed(ActionEvent e) {

                time += interval; // 시간 증가


                gamePanel.repaint(); // 화면 갱신 여기서 paintComponent 호출
                //System.out.println(thread.getState());
            }
        });
        timer.start(); // 타이머 시작
    }

    /**
     *게임 퍼즈 거는 메소드
     */

    public void gamepause(){
        gamePanel.repaint();
        thread1.interrupt();//쓰레드 멈추기위한 메소드
        thread2.interrupt();//이하동문
        timer.stop();//timer 멈추는 메소드
    }

    /**
     * 게임 재개하는 메소드
     */
    public void gamerestart(){
        timer.start();
        wallMove1.re();
        wallMove2.re();
    }

    private void ballAdd(){
        balls.add(new Ball());
    }

    private void ballsSpeedUp(){
        for(Ball ball : balls){
            ball.speedX *= 1.3;
            ball.speedY *= 1.3;
        }
    }







    public void score1Up(){
        score1++;
    }
    public void score2Up(){
        score2++;
    }
    public  ArrayList<Ball> getBalls() {
        return balls;
    }

    public  Wall getWall1() {
        return wall1;
    }
    public  Wall getWall2(){
        return wall2;
    }

    public  WallMove getWallMove1() {
        return wallMove1;
    }

    public WallMove getWallMove2() {
        return wallMove2;
    }

    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }

    public double getTime() {
        return time;
    }
}
