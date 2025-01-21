    package Atari.Test;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    import java.util.ArrayList;
    import java.util.Random;

    public class sample extends JPanel {

        static JFrame frame;
        static Wall wall1; //왼쪽 벽
        static Wall wall2; //오른쪽 벽
        static ArrayList<Ball> balls =new ArrayList<>();
        private final int interval = 5; // 타이머 간격 (밀리초)
        private double time = 0; // 시간 (초)
        private Timer timer;


        public sample() {
            //키 감지하는 함수
            frame.addKeyListener(new KeyListener() {


                @Override
                //key가 눌려 문자가 완성 될 때 발생하는 이벤트 쓸 일 없을거 같은데 아마 동시 처리에 실마리가 될지도
                public void keyTyped(KeyEvent e) {

                }

                @Override
                //key가 눌릴 때 발생하는 이벤트
                public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode(); // key 입력받은 값을 저장하는 변수
                    //왼쪽 화살표 누를때 오른쪽 벽 상승
                    if (key == KeyEvent.VK_LEFT) {
                        if(!(wall2.startY <= 0)){
                            wall2.up();
                        }
                    }
                    //오른쪽 화살표 누를 떄 오른쪽 벽 하락
                    if (key == KeyEvent.VK_RIGHT) {
                        if(!(wall2.startY >= getHeight()-wall2.height)){
                            wall2.down();
                        }

                    }
                    //key A가 눌릴 때 왼쪽 벽 상승
                    if (key == KeyEvent.VK_A) {
                        if(!(wall1.startY <= 0)){
                            wall1.up();
                        }
                    }
                    //key D가 눌릴 때 오른쪽 벽 하락
                    if (key == KeyEvent.VK_D) {
                        if(!(wall1.startY >= getHeight()-wall2.height)){
                            wall1.down();
                        }

                    }

                }
                //키가 눌렸다 때질 때 이벤트 처리
                @Override
                public void keyReleased(KeyEvent e) {

                }
            }
            );
            // 타이머 설정
            timer = new Timer(interval, new ActionListener() {//interval 마다 actionPerformed가 실행되는 구조
                @Override
                public void actionPerformed(ActionEvent e) {

                    time += interval; // 시간 증가
//                    if ((int) (time) % 5000 == 0) {     // 일정 시간마다 속도 증가
//                        speedX *= 1.6;
//                        speedY *= 1.6;
//                    }

                    repaint(); // 화면 갱신 여기서 paintComponent 호출
                }
            });
            timer.start(); // 타이머 시작
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for(Ball ball : balls){//ArrayList에 공 객체 담고 리스트안에 있는 객체들 모두 불러와서 그림 그림
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
                    System.out.println(wall2.startY);
                }
                //벽 만드는 코드
                g.setColor(Color.BLACK);
                g.drawRect(wall1.startX,wall1.startY,wall1.width,wall1.height);
                g.drawRect(wall2.startX,wall2.startY,wall2.width,wall2.height);
                //중앙선 그리는 코드
                g.setColor(Color.BLACK);
                g.drawLine(400,50,400,500);

                if (ball.drawX <= 0 || ball.drawX + 6 >= getWidth()) {
                    //time = 0;       // x 시작 좌표에서부터 time만큼 간 거리를 더해주는 건데, 이동방향이 바뀌었는데도 time 초기화를 해주지 않으면 예를들어 speedX * time 이 800이 나오고 나서 운동방향 반전될 때 -800 이하가 나와버림.
                    ball.reflectX();
                }
                //벽판정
                //왼
                if (ball.drawX <= wall1.startX +wall1.width&&(ball.drawY >= wall1.startY && ball.drawY <= wall1.startY +wall1.height)) {
                    //time = 0;       // x 시작 좌표에서부터 time만큼 간 거리를 더해주는 건데, 이동방향이 바뀌었는데도 time 초기화를 해주지 않으면 예를들어 speedX * time 이 800이 나오고 나서 운동방향 반전될 때 -800 이하가 나와버림.
                    ball.reflectX();
                }
                //우
                if (ball.drawX >= wall2.startX -wall2.width&&(ball.drawY >= wall2.startY && ball.drawY <= wall2.startY +wall1.height)) {
                    //time = 0;       // x 시작 좌표에서부터 time만큼 간 거리를 더해주는 건데, 이동방향이 바뀌었는데도 time 초기화를 해주지 않으면 예를들어 speedX * time 이 800이 나오고 나서 운동방향 반전될 때 -800 이하가 나와버림.
                    ball.reflectX();
                }



                if (ball.drawY <= 0 || ball.drawY + 6 >= getHeight()) { //frame height 크기가 600이기 때문.
                    //time = 0;       // 같은 이유로 여기서도 초기화.
                    ball.reflectY();
                }
            }
        }

        public static void main(String[] args) {
            wall1 = new Wall(20,300,10,100);
            wall2 = new Wall(750,300,10,100);
            //공 객체 balls라는 ArrayList에 추가하면 객체 추가 됨.
            Ball ball1 = new Ball();
            Ball ball2 = new Ball();

            balls.add(ball1);
            balls.add(ball2);
            frame = new JFrame("Projectile Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            sample simulation = new sample();
            frame.add(simulation);

            frame.setVisible(true);
        }
    }
