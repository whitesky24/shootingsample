
package jp.ac.dendai.c.jtp.shootingsample.mono;
import android.content.Context;

import jp.ac.dendai.c.jtp.shootingsample.R;
public class Haikei extends AbstractMono {
    private static final int[] ids = {R.drawable.haikei};
    public Haikei(Context context){
        super(context,ids);
        p.set(0,100);
    }
    @Override
    public void move(int width, int height) {
     }
}