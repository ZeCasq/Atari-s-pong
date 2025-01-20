package Atari.Test;

public class Wall {
    // 벽 생성 위치
    int startX;
    int startY;


    public Wall(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }

    /**
     * 벽 상승
     */
    public void up(){
        this.startY--;
    }

    /**
     * 벽 하락
     */
    public void down(){
        this.startY++;
    }

}
