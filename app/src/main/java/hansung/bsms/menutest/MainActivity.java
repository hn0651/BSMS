package hansung.bsms.menutest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button btnStart;

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        String title = "3D 오브젝트 터치 이벤트";
        btnStart.setText(title);

        final CharSequence[] items = {
                "hansung", "seoul", "yonsei", "korea", "sogang",
                "sungkyunkwan", "hanyang", "chungang", "kyunghee", "hufs",
                "uos", "konkuk", "dongguk", "hongik", "kookmin",
                "soongsil", "sejong", "ewha", "sookmyung", "sungshin",
                "seokyeong", "sahmyook", "kwangwoon", "myongji", "sangmyung",
                "duksung", "dongduk", "swu"
        };

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("KEY is ...")
                        .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context, MenuActivity.class);
                                intent.putExtra("UnityValue", items[which]);
                                context.startActivity(intent);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
