package com.example.healthservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Query="create table user(username text,email text,password text)";
        sqLiteDatabase.execSQL(Query);
        String QueryCart="create table cart(username text,product text,price float,otype text)";
        sqLiteDatabase.execSQL(QueryCart);

        String QueryBooking="create table orderplace(username text,fullname text,adress text,contact text,pincode text,date text,time text,amount text,otype text)";
        sqLiteDatabase.execSQL(QueryBooking);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void register(String username,String email,String password){
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);

        SQLiteDatabase db= getWritableDatabase();
        db.insert("user",null,cv);
        db.close();
    }

    public int login(String username,String password){
        int result=0;
        String a[]=new String[2];
        a[0]=username;
        a[1]=password;

        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select *from user where username=? and password=?",a);
        if(c.moveToFirst()){
            result=1;
        }

        return result;
    }

    public void addtoCart(String username,String Product,float price,String oType){
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("product",Product);
        cv.put("price",price);
        cv.put("otype",oType);

        SQLiteDatabase db=getReadableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }

    public int checkCart(String username,String product){
        int result=0;
        String a[]=new String[2];
        a[0]=username;
        a[1]=product;

        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select *from cart where username=? and product=?",a);
        if(c.moveToFirst()){
            result=1;
        }

        return result;
    }

    public void removeCart(String username,String oType){
        String str[]=new String[2];
        str[0]=username;
        str[1]=oType;
        SQLiteDatabase db=getReadableDatabase();
        db.delete("cart","username=? and oType=?",str);
        db.close();
    }

    public ArrayList getCartData(String username,String oType){
        ArrayList<String>a=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();

        String s[]=new String[2];
        s[0]=username;
        s[1]=oType;

        Cursor c=db.rawQuery("select * from cart where username=?and otype=?",s);

        if(c.moveToFirst()){
            do{
                String product=c.getString(1);
                String price=c.getString(2);
                a.add(product+"$"+price);
            }while(c.moveToNext());
        }
        db.close();
        return a;

    }

    public void addOrder(String username, String fullname, String address, String contact, String pincode, String date, String time, String amount, String otype) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("fullname", fullname);
        values.put("adress", address);

        int pincodeInt = pincode.isEmpty() ? 0 : Integer.parseInt(pincode);
        values.put("contact", contact);
        values.put("pincode", pincodeInt);
        values.put("date", date);
        values.put("time", time);
        values.put("amount", amount);
        values.put("otype", otype);
        db.insert("orderplace", null, values);
        db.close();
    }





}
