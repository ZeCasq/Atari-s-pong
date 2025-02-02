package Atari.Object;

import Atari.Frame.MainFrame;

import java.util.Random;

public class Ball {
    private Random random = new Random();

    int width = 10;
    int height = 10;
    int direction; //방향 (좌우)
    private double angle = random.nextInt(30) + 30;; // 발사 각도 (도) 30~60도
    private double velocity = 1000; // 초기 속도 (m/s)
    public double speedX = velocity * Math.cos(Math.toRadians(angle));
    public double speedY = velocity * Math.sin(Math.toRadians(angle));
    public int startX = MainFrame.frame.getContentPane().getWidth()/2;
    public int startY = 200;      // 초기위치 미리 선언해줘야 튕기는 부분 잘 구현가능해서 창 크기 600이라 그냥 하드코딩함. 근데 어차피 시작 위치 정하는 거라 큰 상관은 없을듯
    public int drawX = (int) (startX);
    public int drawY = (int) (startY); // 그래픽 좌표계는 y가 반대

    public Ball() {
        System.currentTimeMillis();
        this.direction = random.nextInt(2);
        if(direction == 1){
            speedX *= -1;
        }
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    private final int interval = 5; // 타이머 간격 (밀리초)

    public double calculateX() {
        return speedX * interval / 1000.0;
    }

    // 물체의 현재 y 좌표 계산 -> time 동안 움직이는 거리로 바꿈 22
    public double calculateY() {
        return speedY * interval / 1000.0;   // gravity 없어지면서 y로도 등속 직선 운동하도록 공식 변화
    }

    public void reflectX() {
        speedX = -speedX;
    }

    public void reflectY() {
        speedY = -speedY;
    }

}
