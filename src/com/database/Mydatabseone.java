package com.database;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Mydatabseone extends SQLiteOpenHelper {

	private static String name = "Easy_Management_DB";
	private static int version = 1;
	SQLiteDatabase db;

	public Mydatabseone(Context context) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
		db = getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table if not exists Register_Table(username text ,password text,fullname text,address text,phone text,country text)");
		db.execSQL("create table if not exists parents_Table(username text ,number text)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void insert_in_to_register_values(String username, String password,
			String s_Fullname, String s_Address, String s_Phonenumber,
			String s_Country) {
		// TODO Auto-generated method stub
		db.execSQL("insert into Register_Table values('" + username + "','"
				+ password + "','" + s_Fullname + "','" + s_Address + "','"
				+ s_Phonenumber + "','" + s_Country + "')");

	}

	public ArrayList<String> retrieve_credientiaos_frm_dataBase(String val) {
		ArrayList<String> alist = new ArrayList<String>();
		
		Cursor cc=db.rawQuery("select * from Register_Table where username", null);
		
		
		

		return alist;
	}

	public void insert_numbers_parent(String name2, String number) {
		// TODO Auto-generated method stub
		db.execSQL("insert into parents_Table values('" + name2 + "','"
				+ number + "')");

	}

	public Cursor retrieve_name_nymber_parents() {

		Cursor c = db.rawQuery("select * from parents_Table", null);

		// TODO Auto-generated method stub
		return c;
	}

	public void delete_all() {
		// TODO Auto-generated method stub
		db.execSQL("delete from parents_Table");

	}

	public Cursor retrieve_database() {

		Cursor c1 = db.rawQuery("select *from Register_Table", null);
		return c1;

	}

	public ArrayList<String> retrieve_credientiaos_frm_dataBase() {
		// TODO Auto-generated method stub
		
		ArrayList<String> alist = new ArrayList<String>();
		// TODO Auto-generated method stub
		Cursor c = db.rawQuery("select *  from Register_Table",  null);
		
		if (c!=null) {
			
		
		c.moveToFirst();
		do {
			String usernamedd = c.getString(c.getColumnIndex("username"));
			String passwordd = c.getString(c.getColumnIndex("password"));
			
			alist.add(usernamedd);
			alist.add(passwordd);
			
			

		} while (c.moveToNext());
		}
		
		
		return alist;
		
	}

	public void delete_data(String number) {
		// TODO Auto-generated method stub
		db.execSQL("DELETE FROM parents_Table WHERE number='" + number + "'");
		
	}
	
	
}
