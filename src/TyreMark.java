public class TyreMark {
    public int x;
    public int y;
    int width = 5;
    int height = 5;
    int time = 15;

    public TyreMark(int x, int y){
        this.x = x ;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void updateTime(){
        this.time -= 1;
    }
}
