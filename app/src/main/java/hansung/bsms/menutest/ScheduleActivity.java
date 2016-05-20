package hansung.bsms.menutest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by user on 2016-05-15.
 */
public class ScheduleActivity extends Activity {
    private String schedule;
    private ImageView imageViewSchedule;
    private PhotoViewAttacher photoViewAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        imageViewSchedule = (ImageView) findViewById(R.id.imageViewSchedule);

        schedule = getIntent().getExtras().getString("schedule");

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageViewSchedule.getLayoutParams();
        params.width = metrics.widthPixels;
        params.height = metrics.heightPixels;

        imageViewSchedule.setLayoutParams(params);

        photoViewAttacher = new PhotoViewAttacher(imageViewSchedule);
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = new URL(schedule);
            URLConnection conn = url.openConnection();
            conn.connect();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            Bitmap bm = BitmapFactory.decodeStream(bis);
            imageViewSchedule.setImageBitmap(bm);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
