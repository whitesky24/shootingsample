package jp.ac.dendai.c.jtp.shootingsample.mono;
import android.content.Context;
import jp.ac.dendai.c.jtp.shootingsample.Vect;

public abstract class AbstractShootable extends AbstractMono implements Shootable {
    public AbstractShootable(Context context, int[] ids) {
        super(context, ids);
    }
    @Override
    public void init(Vect p, Vect dp) {
        double x = this.width / 2;
        this.p.set(p.getX() + x, p.getY());         //弾の位置を設定
        this.dp.set(dp.getX(), dp.getY());
        setRect();
    }
}