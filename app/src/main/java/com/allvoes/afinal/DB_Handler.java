package com.allvoes.afinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DB_Handler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    //khai báo tên database
    private static final String DATABASE_NAME = "employee.db";
    //khai báo tên table
    private static final String TABLE_NAME = "employees";
    //khai báo các thuộc tính cho table( ko phải kiểu dữ liệu )
    private static final String COLUMN_ID = "emp_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DOB = "dob";
    private static final String COLUMN_EMAIL = "email";



    public DB_Handler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "create table " + TABLE_NAME +
                "(" + COLUMN_ID + " integer primary key, " +
                COLUMN_NAME + " text, " + COLUMN_DOB + " text, " +
                COLUMN_EMAIL + " text )";
        try{
            //thực thi câu lệnh
            db.execSQL(create_table);
            db.execSQL("Insert into employees(name, dob, email) values ('Bui Van Cong','20-05-1979','Cong2000@gmail.com')");
            db.execSQL("Insert into employees(name, dob, email) values ('Gold.D.Roger','23-06-1989','Gold.D.Roger@gmail.com')");
            db.execSQL("Insert into employees(name, dob, email) values ('Elise Maczuber','25-02-1995','Jim Raynor')");
            db.execSQL("Insert into employees(name, dob, email) values ('Ishbal','20-02-1998','Ishbal.santo@gmail.com')");
        }catch (SQLException ex){
            ex.getMessage();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + TABLE_NAME);
            onCreate(db);
    }
    public ArrayList<Employee> listEmployee(){
        ArrayList<Employee> list = new ArrayList<>();
        String query  = "Select * From " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                Employee book = new Employee();
                book.setEmpID(cursor.getInt(0));
                book.setEmpName(cursor.getString(1));
                book.setEmpBirth(cursor.getString(2));
                book.setEmail(cursor.getString(3));
                list.add(book);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    public void addEmployee(Employee e){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, e.getEmpName());
        values.put(COLUMN_DOB, e.getEmpBirth());
        values.put(COLUMN_EMAIL, e.getEmail());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void updateEmployee(Employee b){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,b.getEmpName());
        values.put(COLUMN_DOB,b.getEmpBirth());
        values.put(COLUMN_EMAIL,b.getEmail());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, values , COLUMN_ID + "=" +b.getEmpID(), null);
        db.close();
    }
    public Employee findEmployee(int id){
        String query = "Select * from " + TABLE_NAME + " Where " + COLUMN_ID + " = \"" + id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        Employee book = new Employee();
        if(cursor.moveToFirst()){
            book.setEmpID(Integer.parseInt(cursor.getString(0)));
            book.setEmpName(cursor.getString(1));
            book.setEmpBirth(cursor.getString(2));
            book.setEmail(cursor.getString(3));
            cursor.close();
        }else {
            book = null;
        }
        db.close();
        return book;
    }
    public boolean deleteEmployee(int code){
        boolean result = false;
        String query = "Select * from " + TABLE_NAME + " Where " + COLUMN_ID + " = \'" + code + "\'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        Employee book = new Employee();
        if(cursor.moveToFirst()){
            book.setEmpID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(book.getEmpID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public boolean Login(String id , String pass){
        if(id == "fpt" && pass == "123" || id.equals("fpt") && pass.equals("123")){
            return true;
        }
        return false;
    }


}