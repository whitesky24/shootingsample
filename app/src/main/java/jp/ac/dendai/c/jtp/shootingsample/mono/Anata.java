package jp.ac.dendai.c.jtp.shootingsample.mono;
import android.content.Context;
import android.view.MotionEvent;

import jp.ac.dendai.c.jtp.shootingsample.HanteiList;
import jp.ac.dendai.c.jtp.shootingsample.R;
import jp.ac.dendai.c.jtp.shootingsample.Vect;

public class Anata extends AbstractShooter implements Mikata {
    private static final int[] ids = {R.drawable.anata};    //64×64ピクセルのサイズがちょうど良い
    private final static double shootperiod = 500;         //弾の発射間隔
    private static final Vect tamadp = new Vect(0, -10);    //弾速
    private double shoottic;
    private int upY = 250;
    private int downY = 1600;

    //コンストラクタ
    public Anata(Context context, HanteiList<Shootable> tamalist) {
        super(context, ids, tamalist, new Tama(context));
        shoottic = 0;
    }

    //画面端を越えたときの処理
    @Override
    public void move(int width, int height) {
        if(p.getX() +  this.width > width) p.setX(width - this.width);           //自機が画面右端を越えたときの処理
        if(p.getX() < 0) p.setX(0);                                                 //自機が画面左端を越えたときの処理
        if(p.getY() + this.height > downY) p.setY(downY - this.height);       //自機が画面下端を越えたときの処理
        if(p.getY() < upY) p.setY(upY);                                            //自機が画面上端を越えたときの処理
    }


    //自機の処理
    @Override
    public void setDirection(MotionEvent event, int width, int height) {
        final double delta = 8;
        float px = event.getX();
        float bx = event.getY();

        //左右の処理
        if (px < p.getX()) {
            dp.setX(-delta);
        }else if(p.getX() +  this.width < px){
            dp.setX(delta);
        }

        //上下の処理
        if(bx < p.getY()&& p.getX() < px && px < p.getX() + this.width){
            dp.setY(-delta);
        }else if(p.getY() + this.height < bx && p.getX() < px && px < p.getX() + this.width){
            dp.setY(delta);
        }

        //弾の発射処理
        if(p.getX() < px && px < p.getX() + this.width && p.getY() < bx && bx < p.getY() + this.height){
            shoot(tamadp);
        }
    }

    @Override
    public void step(double t, int width, int height) {
        super.step(t, width, height);
    }
}