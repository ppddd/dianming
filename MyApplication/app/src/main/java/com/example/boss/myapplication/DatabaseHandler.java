package com.example.boss.myapplication;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by BOSS on 2016/10/31.
 */
public class DatabaseHandler extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="Test";
    private static final String TABLE_NAME="student";
    private static final int VERSION=1;
    private static final String KEY_name="name";
    private static final String KEY_id="id";
    private static final String KEY_class="stu_class";
    private static final String KEY_grade2="grade2";
    private static final String KEY_grade3="grade3";
    private static final String KEY_grade4="grade4";
    private static final String KEY_grade5="grade5";
    private static final String KEY_grade1="grade1";
    private static final String KEY_totle="totle";

    //建表语句
    private static final String CREATE_TABLE="create table "+TABLE_NAME+"("+KEY_id+
            " integer primary key autoincrement,"+KEY_name+" text not null,"+
            KEY_class+" text not null,"+KEY_grade1+","+KEY_grade2+","+KEY_grade3+","+
            KEY_grade4+","+KEY_grade5+","+KEY_totle+");";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    /**
     * 当数据库首次创建时执行该方法，一般将创建表等初始化操作放在该方法中执行.
     * 重写onCreate方法，调用execSQL方法创建表
     * */
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }


    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public void addStudent(student student)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        //使用ContentValues添加数据
        ContentValues values=new ContentValues();

        values.put(KEY_id,student.getId());
        values.put(KEY_name,student.getName());
        values.put(KEY_class,student.getStu_class());
        values.put(KEY_grade1,student.getGrade1());
        values.put(KEY_grade2,student.getGrade2());
        values.put(KEY_grade3,student.getGrade3());
        values.put(KEY_grade4,student.getGrade4());
        values.put(KEY_grade5,student.getGrade5());
        values.put(KEY_totle,student.getTotle());
        db.insert(TABLE_NAME, null, values);
        /**Cursor c=db.rawQuery("select * from Test",null);
        if(c!=null) {
            Log.i("info", c.getString(0));
            Log.i("info", c.getString(1));
            Log.i("info", c.getString(2));
            Log.i("info", c.getString(3));
        }
        c.close();**/
        db.close();
    }
    public student getStudent(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        //Cursor对象返回查询结果
        Cursor cursor=db.query(TABLE_NAME,new String[]{KEY_id,KEY_name,KEY_class,KEY_grade1,KEY_grade2,KEY_grade3,KEY_grade4,KEY_grade5,KEY_totle},
                KEY_name+"=?",new String[]{name},null,null,null,null);

        student student=null;

        //注意返回结果有可能为空

        if(cursor.moveToFirst()){
            student=new student(cursor.getString(0),cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
        }
        return student;
    }
    public int getStudentCounts(){
        String selectQuery="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        cursor.close();

        return cursor.getCount();
    }

    //查找所有student
    public List<student> getALllStudent(){
        List<student> studentList=new ArrayList<student>();

        String selectQuery="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                student student=new student();
                student.setId(cursor.getString(0));
                student.setName(cursor.getString(1));
                student.setStu_class(cursor.getString(2));
                student.setGrade1(cursor.getString(3));
                student.setGrade2(cursor.getString(4));
                student.setGrade3(cursor.getString(5));
                student.setGrade4(cursor.getString(6));
                student.setGrade5(cursor.getString(7));
                student.setTotle(cursor.getString(8));
                studentList.add(student);
            }while(cursor.moveToNext());
        }
        return studentList;
    }

    //更新student
    public int updateStudent(student student){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        //values.put(KEY_id,student.getId());
        values.put(KEY_name,student.getName());
        values.put(KEY_class,student.getStu_class());
        values.put(KEY_grade1,student.getGrade1());
        values.put(KEY_grade2,student.getGrade2());
        values.put(KEY_grade3,student.getGrade3());
        values.put(KEY_grade4,student.getGrade4());
        values.put(KEY_grade5,student.getGrade5());
        values.put(KEY_totle,student.getTotle());
        return db.update(TABLE_NAME,values,KEY_id+"=?",new String[]{String.valueOf(student.getId())});
    }
    public void deleteStudent(student student){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_id+"=?",new String[]{String.valueOf(student.getId())});
        db.close();
    }
}
