package com.example.boss.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by BOSS on 2016/10/31.
 */
public class StudentActivity extends Activity implements View.OnClickListener{
    private EditText etName,etId,etClass,etGrade1,etGrade2,etGrade3,etGrade4,etGrade5,etTotle;
    private ImageView imageView;
    private Button btnChange,btnDelete,btnAdd;

    private DatabaseHandler handler;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);


        etId= (EditText) findViewById(R.id.student_id);
        etName= (EditText) findViewById(R.id.student_name);
        etClass=(EditText)findViewById(R.id.student_class);
        etGrade1=(EditText)findViewById(R.id.student_grade1);
        etGrade2=(EditText)findViewById(R.id.student_grade2);
        etGrade3=(EditText)findViewById(R.id.student_grade3);
        etGrade4=(EditText)findViewById(R.id.student_grade4);
        etGrade5=(EditText)findViewById(R.id.student_grade5);
        etTotle=(EditText)findViewById(R.id.student_totle);
        btnChange= (Button) findViewById(R.id.btn_change);
        btnDelete= (Button) findViewById(R.id.btn_delete);
        btnAdd= (Button) findViewById(R.id.btn_add_student);


        handler=new DatabaseHandler(this);
        //获取传递过来的intent
        intent=getIntent();

        //通过request判断，是通过那个Button点击进入的，之后隐藏或者显示相应的Button
        String request=intent.getStringExtra("request");
        switch (request){
            //点击添加按钮进入的，则只显示btnAdd
            case "Add":
                btnChange.setVisibility(View.GONE);
                btnDelete.setVisibility(View.GONE);
                btnAdd.setVisibility(View.VISIBLE);
                break;
            //通过ListView Item进入的
            case "Look":

                etId.setText(intent.getStringExtra("id"));
                etName.setText(intent.getStringExtra("name"));
                etClass.setText(intent.getStringExtra("class"));
                etGrade1.setText(intent.getStringExtra("grade1"));
                etGrade2.setText(intent.getStringExtra("grade2"));
                etGrade3.setText(intent.getStringExtra("grade3"));
                etGrade4.setText(intent.getStringExtra("grade4"));
                etGrade5.setText(intent.getStringExtra("grade5"));
                etTotle.setText(intent.getStringExtra("totle"));
                break;


        }
        btnAdd.setOnClickListener(this);
        btnChange.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_student:
                student newStudent=new student(etName.getText().toString(),etId.getText().toString(),etClass.getText().toString(),etGrade1.getText().toString(),etGrade2.getText().toString(),etGrade3.getText().toString(),etGrade4.getText().toString(),etGrade5.getText().toString(),etTotle.getText().toString());
                handler.addStudent(newStudent);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btn_change:
                student student=new student(etName.getText().toString(),etId.getText().toString(),etClass.getText().toString(),etGrade1.getText().toString(),etGrade2.getText().toString(),etGrade3.getText().toString(),etGrade4.getText().toString(),etGrade5.getText().toString(),etTotle.getText().toString());
                handler.updateStudent(student);
                //这里设置resultCode是为了区分是修改后返回主界面的还是删除后返回主界面的。
                setResult(2,intent);
                finish();
                break;
            case R.id.btn_delete:
                student s=new student(etName.getText().toString(),etId.getText().toString(),etClass.getText().toString(),etGrade1.getText().toString(),etGrade2.getText().toString(),etGrade3.getText().toString(),etGrade4.getText().toString(),etGrade5.getText().toString(),etTotle.getText().toString());
                handler.deleteStudent(s);
                setResult(3, intent);
                finish();
                break;
        }
    }
}
