package Atari;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sample extends JPanel {
    private double angle = 45; // 발사 각도 (도)
    private double velocity = 50; // 초기 속도 (m/s)
    private double time = 0; // 시간 (초)
    private final double gravity = 9.8; // 중력 가속도 (m/s^2)
    private final int interval = 20; // 타이머 간격 (밀리초)
    private Timer timer;

    public sample() {
        // 타이머 설정
        timer = new Timer(interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time += interval / 1000.0; // 시간 증가

                // 현재 y 좌표 계산
                double y = calculateY();

                // 물체가 땅에 닿으면 타이머 정지
                if (y < 0) {
                    timer.stop(); // 타이머 멈춤
                }

                repaint(); // 화면 갱신
            }
        });
        timer.start(); // 타이머 시작
    }

    // 물체의 현재 x 좌표 계산
    private double calculateX() {
        return velocity * Math.cos(Math.toRadians(angle)) * time;
    }

    // 물체의 현재 y 좌표 계산
    private double calculateY() {
        return velocity * Math.sin(Math.toRadians(angle)) * time - 0.5 * gravity * time * time;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 초기 위치
        int startX = 50;
        int startY = getHeight() - 50;

        // 현재 위치 계산
        double x = calculateX();
        double y = calculateY();
        int drawX = (int) (startX + x);
        int drawY = (int) (startY - y); // 그래픽 좌표계는 y가 반대

        // 땅에 닿기 전까지 그리기
        if (drawY <= startY) {
            g.setColor(Color.BLUE);
            g.fillOval(drawX, drawY, 10, 10); // 물체 그리기
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Projectile Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        sample simulation = new sample();
        frame.add(simulation);

        frame.setVisible(true);
    }
}
