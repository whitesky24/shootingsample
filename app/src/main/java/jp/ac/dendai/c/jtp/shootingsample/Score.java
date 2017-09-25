package jp.ac.dendai.c.jtp.shootingsample;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Score {
    private int score;
    private Paint paint = new Paint();

    public Score() {
        paint.setColor(Color.WHITE);
        paint.setTextSize(100);                 //スコアを表示するサイズを指定
    }

    public void decrease(int point) {
        score -= point;
    }

    public void draw(Canvas canvas) {
        String sc = "000000000" + score;
        canvas.drawText(sc.substring(sc.length() - 10), 0, paint.getTextSize(), paint);
    }

    public void add(int point) {
        score += point;
    }

    //敵を生成するときに使う現在の得点を取得
    public int getScore(){
        return score;
    }
}