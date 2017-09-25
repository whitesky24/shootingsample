package jp.ac.dendai.c.jtp.shootingsample;
import android.content.Context;

import java.util.Random;

import jp.ac.dendai.c.jtp.shootingsample.mono.Mono;
import jp.ac.dendai.c.jtp.shootingsample.mono.Teki;

public class TekiLogic {
    private static double period = 1000;
    private final Context context;
    private final HanteiList<Mono> list;
    private Score score;
    private double tic;
    private int point, flag;
    private Random random;
    private int x, y, num;

    //コンストラクタ
    public TekiLogic(Context context, HanteiList<Mono> list, Score score) {
        this.context = context;
        this.list = list;
        this.score = score;
        tic = 0;
        point = 0;
        flag = 0;
        random = new Random();
    }

    //敵生成処理
    private Mono createTeki1() {
        return new Teki(context, 100, -150, 1);
    }
    private Mono createTeki2() {
        return new Teki(context, 450, -350, 1);
    }
    private Mono createTeki3() {
        return new Teki(context, 800, -150, 1);
    }
    private Mono createTekirandom() {
        return new Teki(context, x, y, num);
    }

    //敵生成の間隔
    public void step(double tstep, int width, int height) {
        tic += 1;
        point = score.getScore();

        //ゲームが開始したときに敵を3体生成
        if(point == 0 && flag == 0){
            list.add(createTeki1());
            list.add(createTeki2());
            list.add(createTeki3());
            flag += 1;
        }

        //最初の3体を倒したら、次の敵を2体生成
        if(point == 300 && flag == 1) {
            x = random.nextInt(701) + 100;
            y = (random.nextInt(201) + 150) * (-1);
            num = random.nextInt(3) + 1;
            list.add(createTekirandom());

            x = random.nextInt(701) + 100;
            y = (random.nextInt(201) + 150) * (-1);
            num = random.nextInt(3) + 1;
            list.add(createTekirandom());

            flag += 1;
        }

        //生成された5体の敵を倒したら、一定時間ごとに敵を3体ずつ生成
        if(point >= 500 && flag == 2 && tic % 300 == 0){
            x = random.nextInt(701) + 100;
            y = (random.nextInt(201) + 150) * (-1);
            num = random.nextInt(3) + 1;
            list.add(createTekirandom());

            x = random.nextInt(701) + 100;
            y = (random.nextInt(201) + 150) * (-1);
            num = random.nextInt(3) + 1;
            list.add(createTekirandom());

            x = random.nextInt(701) + 100;
            y = (random.nextInt(201) + 150) * (-1);
            num = random.nextInt(3) + 1;
            list.add(createTekirandom());

            tic = 0;
        }

        if(tic == 1000){
            tic = 0;
        }
    }
}