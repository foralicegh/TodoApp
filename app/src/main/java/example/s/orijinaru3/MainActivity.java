package example.s.orijinaru3;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.DatePicker;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView sTextView;
    TextView cTextView;
    TextView dTextView;
    String mdeadline;
    long mdiffday;
    List<Homework> hwlist;
    ImageView sImageView;
    ImageView cImageView;
    ImageView dImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_mainmenu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                save();
                return true;
            }

        });


        sTextView = (TextView) findViewById(R.id.sTextView);
        cTextView = (TextView) findViewById(R.id.cTextView);
        dTextView = (TextView) findViewById(R.id.dTextView);
        sImageView = (ImageView) findViewById(R.id.sImageView);
        cImageView = (ImageView) findViewById(R.id.cImageView);
        dImageView = (ImageView) findViewById(R.id.dImageView);

        Intent intent = getIntent();
        hwlist = new ArrayList<Homework>();
        if (intent != null) {
            String subject = intent.getStringExtra("Subject");
            sTextView.setText(subject);

        }

        readFile();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        dTextView.setText(String.valueOf(year) + "/ " + String.valueOf(monthOfYear + 1) + "/ " + String.valueOf(dayOfMonth));
        mdeadline = String.valueOf(year) + "/ " + String.valueOf(monthOfYear + 1) + "/ " + String.valueOf(dayOfMonth);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        long deadlineMillis = calendar.getTimeInMillis();
        long currentTimeMillis = System.currentTimeMillis();
        long diff = deadlineMillis - currentTimeMillis;
        diff = diff / 1000;
        diff = diff / 60;
        diff = diff / 60;
        diff = diff / 24;
        mdiffday = diff;
    }


    public boolean readFile() {
        try {
            FileInputStream fis = openFileInput("lHomework");
            ObjectInputStream ois = new ObjectInputStream(fis);
            hwlist = (ArrayList<Homework>) ois.readObject();
            ois.close();
            fis.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean save() {

        String msubject = String.valueOf(sTextView.getText());
        String mcontent = String.valueOf(cTextView.getText());
//        String mdeadline = String.valueOf(dTextView.getText());

        hwlist.add(new Homework(msubject, mcontent, mdiffday));

        if (hwlist.size() != 0) {
            try {
                FileOutputStream fos = openFileOutput("mHomework", MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(hwlist);
                oos.close();
                fos.close();
            } catch (Exception e) {

            }
        }
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
        return false;
    }


    public void subjectpick(View v) {
        Intent intent = new Intent(MainActivity.this, SubjectPickActivity.class);
        startActivity(intent);
    }

    public void contentpick(View v) {

    }

    public void datepick(View v) {
        DialogFragment newFragment = new DatePickFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }


    //aaaaaa

}





