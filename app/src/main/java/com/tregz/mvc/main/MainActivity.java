package com.tregz.mvc.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.TextView;

import com.tregz.mvc.R;
import com.tregz.mvc.data.DataApple;
import com.tregz.mvc.core.date.DateUtil;
import com.tregz.mvc.list.ListApple;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY;

public class MainActivity extends AppCompatActivity {

    //private final String TAG = MainActivity.class.getSimpleName();
    private TextView log;
    private TextView sum;
    private EditText editor;
    private String input;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListApple.getInstance().clear();

        log = findViewById(R.id.log);
        log.setMovementMethod(new ScrollingMovementMethod());
        sum = findViewById(R.id.sum);
        DataApple apple1 = new DataApple(new Date());  // today
        onAppleCreated(ListApple.getInstance().add(apple1));
        apple1.setColor(R.color.colorAccent);
        DataApple apple2 = new DataApple(DateUtil.addMonth(new Date(), -1)); // last month
        onAppleCreated(ListApple.getInstance().add(apple2));
        apple2.setColor(android.R.color.white);
        DataApple apple3 = new DataApple(null);
        onAppleCreated(ListApple.getInstance().add(apple3));
        apple3.setColor(R.color.colorPrimary);
        apple();
    }

    private void onAppleCreated(DataApple apple) {
        log.append(HtmlCompat.fromHtml("<b>Pomme ajoutée</b>", FROM_HTML_MODE_LEGACY));
        String skeleton = "d MMMM yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(skeleton, Locale.getDefault());
        String day = apple.getRipe() != null ? formatter.format(apple.getRipe()) : null;
        String unknown = "Non cueillie ou date de cueillette inconnue.";
        String riped = day != null ? "Cueillie le " + day + "." : unknown;
        log.append("\n" + riped + "\n");
        String total = "Taille de la liste: " + ListApple.getInstance().getListCount();
        sum.setText(total);
    }

    public void apple() {
        DataApple apple = new DataApple(new Date());
        apple.setColor(R.color.colorPrimary);
        onAppleCreated(ListApple.getInstance().add(apple));
    }
}
