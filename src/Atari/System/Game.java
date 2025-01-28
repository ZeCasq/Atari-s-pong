package Atari.System;
import Atari.Frame.GamePanel;
import Atari.Object.*;
import Atari.Object.WallMove;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
     게임의 로직을 표현 하는 클래스
 */
public class Game{

    static Wall wall1; //왼쪽 벽
    static Wall wall2; //오른쪽 벽
    static ArrayList<Ball> balls =new ArrayList<>();
    static int interval = 5;
    private static double time = 0; // 시간 (초)
    GamePanel gamePanel; // 연결할 게임 패널 repaint를 요청하기 위해서
    //추후 쓰레드를 위한 변수
    static WallMove wallMove1;
    static WallMove wallMove2;
    static Thread thread1;
    static Thread thread2;
    static Timer timer;
    public Game(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    //점수
    static int score1;
    static int score2;

    /**
     * 게임 시작 전 초기 설정해주는 클래스
     */
    public  void gameset(){
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

    static public void gamepause(){
        thread1.interrupt();//쓰레드 멈추기위한 메소드
        thread2.interrupt();//이하동문
        timer.stop();//timer 멈추는 메소드

    }

    /**
     * 게임 재개하는 메소드
     */
    static public void gamerestart(){
        timer.start();
        wallMove1.re();
        wallMove2.re();
    }



    static public void score1Up(){
        score1++;
    }
    static public void score2Up(){
        score2++;
    }
    public static ArrayList<Ball> getBalls() {
        return balls;
    }

    public static Wall getWall1() {
        return wall1;
    }
    public static Wall getWall2(){
        return wall2;
    }

    public static WallMove getWallMove1() {
        return wallMove1;
    }

    public static WallMove getWallMove2() {
        return wallMove2;
    }

    public static double getTime() {
        return time;
    }
}
