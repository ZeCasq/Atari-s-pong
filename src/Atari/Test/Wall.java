package Atari.Test;

public class Wall {
    // 벽 생성 위치
    int startX;
    int startY;
    int width;
    int height;

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
        this.startY -= 5;
    }

    /**
     * 벽 하락
     */
    public void down(){
        this.startY += 5;
    }

}
