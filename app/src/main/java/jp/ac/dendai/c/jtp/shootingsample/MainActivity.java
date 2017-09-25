package jp.ac.dendai.c.jtp.shootingsample;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Window;

public class MainActivity extends AppCompatActivity {
    private View view;
    private Thread mainThread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart(){
        super.onStart();
        Display display = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        view = new View(this, p);
        view.init();

        findViewById(R.id.start_button).setOnClickListener(start);      //startボタンにクリックされた時に、クリックリスナーのイベントの実装
    }

    View.OnClickListener start = new View.OnClickListener() {             //startボタンが押されたときの処理
        @Override
        public void onClick(android.view.View v) {
            setContentView(view);
        }
    };

    @Override
    public void onResume(){
        super.onResume();
        view.start();
    }
    @Override
    public void onStop(){
        super.onStop();
        while (mainThread != null && mainThread.isAlive()) {
            try {
                view.shutdown = true;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}