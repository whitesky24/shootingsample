package jp.ac.dendai.c.jtp.shootingsample.mono;
import android.content.Context;
import jp.ac.dendai.c.jtp.shootingsample.R;
import jp.ac.dendai.c.jtp.shootingsample.Vect;

public class Teki extends AbstractMono {
    private static final int[] ids = {R.drawable.teki1, R.drawable.teki2};
    private int dpindex;
    private Vect[] dps1 = {new Vect(1, 3), new Vect(-1, 3)};
    private Vect[] dps2 = {new Vect(0, 5), new Vect(0, -5)};
    private double dpcycle = 1000;
    private double dpcounter;
    private int num;

    //コンストラクタ
    public Teki(Context context) {
        super(context, ids);
    }
    public Teki(Context context, int x, int y, int num) {
        super(context, ids);
        set(x, y);
        this.num = num;
    }

    //画面端を越えたときの処理
    @Override
    public void move(int width, int height) {
        if (p.getX() + this.width > width){             //自機が画面右端を越えたときの処理
            p.setX(width - this.width);
            num = 2;
        }
        else if(p.getX() < 0){                          //自機が画面左端を越えたときの処理
            p.setX(0);
            num = 3;
        }
        if (p.getY() > height) p.setY(-this.height);    //自機が画面下端を越えたときの処理
    }

    @Override
    public double getInterval() {
        return 23;
    }
    @Override
    public int getScore() {
        return 100;
    }

    @Override
    public void step(double t, int width, int height) {
        //画像の切り替え
        period += t;
        if (dpcounter + t > dpcycle) {
            dpindex = (dpindex + 1) % dps1.length;
            dpcounter = dpcounter + t - dpcycle;
        } else {
            dpcounter += t;
        }

        //敵の種類に応じて移動速度を変える
        if(num == 1) {
            p.add(dps2[0]);
        }
        if(num == 2){
            p.add(dps1[1]);
        }
        if(num == 3){
            p.add(dps1[0]);
        }

        move(width, height);
        setRect();
    }
}