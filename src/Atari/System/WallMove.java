package Atari.System;


import Atari.Object.Wall;

public class WallMove implements Runnable{
    Wall wall;
    boolean go = true;
    public boolean isUp =false;
    public boolean isDown = false;
    public WallMove(Wall wall1) {
        this.wall = wall1;
    }

    /**
     *쓰레드를 start했을 때 자동적으로 run이 실행 되는 듯,계속 반복하기 위해 루프를 걸었음
     */
    public void run() {
        while (true) {
            //go가 false일 때 계속해서 10ms 멈추는데 go는 퍼즈걸 때 false됨 즉 그냥 퍼즈 거는 용도
            if(!go){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (isUp) {
                wall.up();
            }
            if (isDown) {
                wall.down();
            }
            try {
                //이 부분은 흔히 쓰는 sleep과 동일함 아마 sleep(1000)이 1초임
                Thread.sleep(10);
            } catch (InterruptedException e) {
                go =false;
                System.out.println("쓰레드가 인터럽트되었습니다.");
            }
        }
    }

    /**
     * 쓰레드를 재시작 할 때 필요한 함수
     * 퍼즈 풀기 위한 파츠
     */
    public void re(){
        go = true;
    }

}
