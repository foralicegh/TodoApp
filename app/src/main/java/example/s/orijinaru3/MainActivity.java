package example.s.orijinaru3;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView sTextView;
    TextView dTextView;
    String mSubject;
    String mContent;
    String mdeadline;
    long mdiffday;
    List<Homework> homeworkList;
    String subject[];
    List<String> readList;
    String helper[];
    List<String> readHelperList;
    ImageView sImageView;
    ImageView cImageView;
    ImageView dImageView;
    EditText cEditText;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.clear_white_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cEditText = (EditText) findViewById(R.id.cEditText);
        cEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        sTextView = (TextView) findViewById(R.id.sTextView);
        sTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });


//        subjectList = new ArrayList<String>();
//        readSubject();
//
//        if (subjectList == null) {
//
//        }

//        Intent intent = getIntent();
//        if (intent != null) {
//            subjectList = new ArrayList<String>();
//            subjectList = intent.getStringArrayListExtra("subjectadd");
//            for (int i = 0; i < subjectList.size(); i++) {
//                spinnerArrayAdapter.add(subjectList.get(i));
//            }
//        } else {
//            for (int i = 0; i < 14; i++) {
//                spinnerArrayAdapter.add(subjectList.get(i));
//            }
//
//        }

        dTextView = (TextView) findViewById(R.id.dTextView);
        dTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datepick();
            }
        });
        sImageView = (ImageView) findViewById(R.id.sImageView);
        cImageView = (ImageView) findViewById(R.id.cImageView);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        dImageView = (ImageView) findViewById(R.id.dImageView);

        homeworkList = new ArrayList<>();
        readFile();

        readList = new ArrayList<String>();
        readHelperList = new ArrayList<String>();
        readSubjectFile();
        readHelperFile();
        firststarting();
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

    public boolean readFile(){
        try {
            FileInputStream fis = openFileInput("lHomework");
            ObjectInputStream ois = new ObjectInputStream(fis);
            homeworkList = (ArrayList<Homework>) ois.readObject();
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



    public boolean readSubjectFile() {
        try {
            FileInputStream fis = openFileInput("savesubject");
            ObjectInputStream ois = new ObjectInputStream(fis);
            readList = (List<String>) ois.readObject();
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

    public boolean readHelperFile() {
        try {
            FileInputStream fis = openFileInput("savehelper");
            ObjectInputStream ois = new ObjectInputStream(fis);
            readHelperList = (List<String>) ois.readObject();
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

    public boolean firststarting() {
        String[] firstsubject = {"古典", "現代文", "数I", "数Ⅱ", "英語", "英語表現",
                "コミュ英語", "物理", "化学", "生物", "地学", "地理", "世界史", "日本史"};
        if (readList.size() == 0) {
            for (int i = 0; i < 14; i++) {
                readList.add(firstsubject[i]);
            }
        }
//        ,"","","","","","","","","",""
        String[] firsthelper = {"P", "No", "～P", "～No", "・"};
        if (readHelperList.size() == 0) {
            for (int i = 0; i < 5; i++) {
                readHelperList.add(firsthelper[i]);
            }
        }
        return true;
    }

    public void save(View v) {

        String msubject = String.valueOf(sTextView.getText());
        String mcontent = String.valueOf(cEditText.getText());
        Homework addHomework=new Homework(msubject,mcontent,mdiffday);
        homeworkList.add(addHomework);
//        intent.putExtra("addSubject", msubject);
//        intent.putExtra("addContent", mcontent);
//        intent.putExtra("addDiffday", mdiffday);

        try {
            FileOutputStream fos = openFileOutput("lHomework", MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(homeworkList);
            oos.close();
            fos.close();
        } catch (Exception e) {

        }
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
//        List<String> saveSubject = new ArrayList<String>();
//        saveSubject =  Arrays.asList(subject);
//        if (readList.size() == 0) {
//            String[] subject = {"古典", "現代文", "数I", "数Ⅱ", "英語", "英語表現",
//                    "コミュ英語", "物理", "化学", "生物", "地学", "地理", "世界史", "日本史"};
//            saveSubject.addAll(Arrays.asList(subject));
//        }
        try {
            FileOutputStream fos = openFileOutput("savesubject", MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(readList);
            oos.close();
            fos.close();
        } catch (Exception e) {
        }

//        List<String> saveHelper=new ArrayList<String>();
//        if (readHelperList.size()==0){
//            String helper[]={"P", "No", "～", "・", "「」"};
//            saveHelper.addAll(Arrays.asList(helper));
        try {
            FileOutputStream fos = openFileOutput("savehelper", MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(readHelperList);
            oos.close();
            fos.close();
        } catch (Exception e) {
        }
    }


//        String mdeadline = String.valueOf(dTextView.getText());

//        hwlist.add(new Homework(msubject, mcontent, mdiffday));

//        Homework add = new Homework(msubject, mcontent, mdiffday);
//
//        if (hwlist.size() != 0) {
//            try {
//                FileOutputStream fos = openFileOutput("addHomework", MODE_PRIVATE);
//                ObjectOutputStream oos = new ObjectOutputStream(fos);
//                oos.writeObject(add);
//                oos.close();
//                fos.close();
//            } catch (Exception e) {
//
//            }
//        }


    public boolean add() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
//        subjectList = (String[]) readList.toArray();
        subject = readList.toArray(new String[readList.size()]);
        alertDialog.setItems(subject, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sTextView.setText(subject[i]);
            }
        });
        alertDialog.create().show();
        return true;
    }

    public boolean datepick() {
        DialogFragment newFragment = new DatePickFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
        return true;
    }

    public void helper(View v) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        helper = readHelperList.toArray(new String[readHelperList.size()]);
        alertDialog.setItems(helper, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cEditText.setText(cEditText.getText() + helper[i]);
                cEditText.setSelection(cEditText.getText().length());
            }
        });
        alertDialog.create().show();
    }


//    public boolean readSubject() {
//        try {
//            FileInputStream fis = openFileInput("mAdd");
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            subjectList = (ArrayList<String>) ois.readObject();
//            ois.close();
//            fis.close();
//            return true;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return false;
//        } catch (StreamCorruptedException e) {
//            e.printStackTrace();
//            return false;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }


}





