package com.example.boss.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    private ListView students;
    private StudentAdapter adapter;
    private Button btnAdd, btnSearch,btnCall;
    private DatabaseHandler dbHandler;
    private List<student> studentList;
    private SQLiteDatabase db;
    private spinnerAdapter ssadapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        students = (ListView) findViewById(R.id.stduent_list);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnSearch = (Button) findViewById(R.id.btn_search);
        btnCall=(Button)findViewById(R.id.btn_call);

        btnSearch.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnCall.setOnClickListener(this);

        dbHandler = new DatabaseHandler(this);

        //获取全部学生信息
        studentList = dbHandler.getALllStudent();
        adapter = new StudentAdapter(this, studentList);
        students.setAdapter(adapter);

        //点名界面获取学生信息
        studentList = dbHandler.getALllStudent();
        ssadapter=new spinnerAdapter(this,studentList);
        students.setAdapter(ssadapter);

        //点击ListView item跳转到详细界面
        students.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, StudentActivity.class);

                //注意这里的request是为了区分是通过什么跳转到详细界面的
                intent.putExtra("request", "Look");
                intent.putExtra("id", studentList.get(i).getId());
                intent.putExtra("name", studentList.get(i).getName());
                intent.putExtra("class", studentList.get(i).getStu_class());
                intent.putExtra("grade1", studentList.get(i).getGrade1());
                intent.putExtra("grade2", studentList.get(i).getGrade2());
                intent.putExtra("grade3", studentList.get(i).getGrade3());
                intent.putExtra("grade4", studentList.get(i).getGrade4());
                intent.putExtra("grade5", studentList.get(i).getGrade5());
                intent.putExtra("totle", studentList.get(i).getTotle());
                //
                startActivityForResult(intent, 0);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:

                Intent i = new Intent(MainActivity.this, StudentActivity.class);
                i.putExtra("request", "Add");

                //startActivity(i);
                startActivityForResult(i, 1);
                break;
            case R.id.btn_call:
                Intent i0=new Intent();
                i0.setClass(MainActivity.this,spinnerActivity.class);
                startActivity(i0);
                break;

            case R.id.btn_search:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);



                //自定义View的Dialog
                final LinearLayout searchView = (LinearLayout) getLayoutInflater().inflate(R.layout.search, null);
                builder.setView(searchView);
                final AlertDialog dialog = builder.create();
                dialog.show();


                //为自定义View的Dialog的控件添加事件监听
                final EditText searchName = (EditText) searchView.findViewById(R.id.search_name);
                Button btnDialogSearch = (Button) searchView.findViewById(R.id.btn_search_dialog);
                btnDialogSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        searchName.setVisibility(View.GONE);
                        ListView list = (ListView) searchView.findViewById(R.id.search_result);
                        List<student> resultList = new ArrayList<student>();
                        final student searchStudent = dbHandler.getStudent(searchName.getText().toString());
                        if (searchStudent != null) {
                            resultList.add(searchStudent);
                            StudentAdapter resultAdapter = new StudentAdapter(getApplicationContext(), resultList);
                            list.setAdapter(resultAdapter);
                            list.setVisibility(View.VISIBLE);
                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                                    intent.putExtra("request", "Look");
                                    intent.putExtra("id", searchStudent.getId());
                                    intent.putExtra("name", searchStudent.getName());
                                    intent.putExtra("class", searchStudent.getStu_class());
                                    intent.putExtra("grade1", searchStudent.getGrade1());
                                    intent.putExtra("grade2", searchStudent.getGrade2());
                                    intent.putExtra("grade3", searchStudent.getGrade3());
                                    intent.putExtra("grade4", searchStudent.getGrade4());
                                    intent.putExtra("grade5", searchStudent.getGrade5());
                                    intent.putExtra("totle", searchStudent.getTotle());
                                    startActivityForResult(intent, 0);
                                }
                            });
                        } else {
                            //关闭Dialog
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "无此学生", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                break;
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //根据返回的resultCode判断是通过哪种操作返回的，并提示相关信息；
        switch (requestCode) {
            case 0:
                if (resultCode == 2)
                    Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                if (resultCode == 3)
                    Toast.makeText(this, "已删除", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                if (resultCode == RESULT_OK)
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
        }
        /**
         * 如果这里仅仅使用adapter.notifyDataSetChanged()是不会刷新界面ListView的，
         * 因为此时adapter中传入的studentList并没有给刷新，即adapter也没有被刷新，所以你可以
         * 重新获取studentList后再改变adapter，我这里通过调用onCreate()重新刷新了整个界面
         */

        studentList=dbHandler.getALllStudent();
        adapter=new StudentAdapter(this,studentList);
        students.setAdapter(adapter);
        //onCreate(null);
    }

   /** @Override
    public void onStart() {
       super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.boss.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.boss.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }*/
}

