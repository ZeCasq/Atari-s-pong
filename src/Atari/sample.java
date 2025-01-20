    package Atari;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

    public class sample extends JPanel {
        private double angle = 15; // 발사 각도 (도)
        private double velocity = 200; // 초기 속도 (m/s)
        private double speedX = velocity * Math.cos(Math.toRadians(angle));
        private double speedY = velocity * Math.sin(Math.toRadians(angle));
        private double time = 0; // 시간 (초)
        int startX = 50;
        int startY = 550;       // 초기위치 미리 선언해줘야 튕기는 부분 잘 구현가능해서 창 크기 600이라 그냥 하드코딩함. 근데 어차피 시작 위치 정하는 거라 큰 상관은 없을듯
        int drawX = (int) (startX);
        int drawY = (int) (startY); // 그래픽 좌표계는 y가 반대

    //    private final double gravity = 9.8; // 중력 가속도 (m/s^2)     // 포물선 운동하면 안 되니 이 부분 삭제

        private final int interval = 20; // 타이머 간격 (밀리초)
        private Timer timer;

        public sample() {
            // 타이머 설정
            timer = new Timer(interval, new ActionListener() {  //interval 마다 actionPerformed가 실행되는 구조
                @Override
                public void actionPerformed(ActionEvent e) {
                    time += interval; // 시간 증가
                    if ((int) (time) % 5000 == 0) {     // 일정 시간마다 속도 증가
                        speedX *= 1.6;
                        speedY *= 1.6;
                    }

//                    // 현재 y 좌표 계산
//                    double y = calculateY();
    //                // 물체가 땅에 닿으면 타이머 정지
    //                if (y < 0) {
    //                    timer.stop(); // 타이머 멈춤       // 땅에 닿으면 튕겨야 하기 때문에 이 부분 삭제
    //                }


                    repaint(); // 화면 갱신 여기서 paintComponent 호출
                }
            });
            timer.start(); // 타이머 시작
        }

        // 물체의 현재 x 좌표 계산 -> time 동안 움직이는 거리로 바꿈 (튕기는 부분 잘 구현하기 위함)
        private double calculateX() {
            return speedX * interval / 1000.0;
        }

        // 물체의 현재 y 좌표 계산 -> time 동안 움직이는 거리로 바꿈 22
        private double calculateY() {
            return speedY * interval / 1000.0;   // gravity 없어지면서 y로도 등속 직선 운동하도록 공식 변화
        }

        private void reflectX() {
            speedX = -speedX;
        }

        private void reflectY() {
            speedY = -speedY;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 초기 위치

            // 현재 위치 계산
            double x = calculateX();
            double y = calculateY();
            drawX += (int) (x);
            drawY -= (int) (y);

            // 땅에 닿기 전까지 그리기
            if (true) {  // y좌표계는 위로 올라갈 수록 작은 거임.
                g.setColor(Color.BLUE);
                g.fillOval(drawX, drawY, 10, 10); // 물체 그리기 x 지름 y지름 모두 10인 타원 즉, 반지름이 5인 원
                System.out.println(drawY);
            }

            if (drawX <= 0 || drawX >= getWidth()) {
                time = 0;       // x 시작 좌표에서부터 time만큼 간 거리를 더해주는 건데, 이동방향이 바뀌었는데도 time 초기화를 해주지 않으면 예를들어 speedX * time 이 800이 나오고 나서 운동방향 반전될 때 -800 이하가 나와버림.
                reflectX();
            }

            if (drawY <= 0 || drawY >= getHeight()) { //frame height 크기가 600이기 때문.
                time = 0;       // 같은 이유로 여기서도 초기화.
                reflectY();
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
