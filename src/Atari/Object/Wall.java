package Atari.Object;

import Atari.Frame.MainFrame;

public class Wall {
    // 벽 생성 위치
    public int startX;
    public int startY;
    public int width;
    public int height;

    //벽의 좌표 기준점은 벽의 왼쪽 상단 꼭짓점인듯
    public Wall(int startX, int startY,int width,int height) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
    }
//사실상 속도
    /**
     * 벽 상승
     */
    public void up(){
        if(!(this.startY <= 0)) {
            this.startY -= 10;
        }
    }

    /**
     * 벽 하락
     */
    //getContentPane은 이제 테두리 미포함,즉 실제 우리가 만질 수 있는 화면 부분을 말함
    public void down(){
       if(!(this.startY >= MainFrame.frame.getContentPane().getHeight() -this.height)){
            this.startY += 10;
        }
    }
}
