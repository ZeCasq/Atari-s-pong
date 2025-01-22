package Atari.Test;

class WallMove implements Runnable {
    Wall wall;
    public boolean isUp =false;
    public boolean isDown = false;
    public WallMove(Wall wall) {
        this.wall = wall;
    }
    //쓰레드를 start했을 때 자동적으로 run이 실행 되는 듯,계속 반복하기 위해 루프를 걸었음
    public void run() {
        while (true) {
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

                e.printStackTrace(); //이 부분은 예외처리 구문 같은데 어쩔 때 예외가 나는지 잘 모르며 함수도 잘 모름. 그냥 필요하니 있겠지 ㅋㅋ 공부하기에 너무 졸림
            }        }
    }


}

