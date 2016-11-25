package com.techfort.attendancesystem;


import java.util.ArrayList;

import com.database.Mydatabseone;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

public class AllMessage extends Activity {
	EditText ed;
	Cursor c_vals;
	Mydatabseone mydb;
	String data;
	ArrayList<String> alist = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_message);
		mydb = new Mydatabseone(this);
	ed=(EditText)findViewById(R.id.editText1);
	}

	public void messageALL(View v){
		
		 data=ed.getText().toString();
		if(data.equals("")||data.length()==0){
			
			ed.setError("Missing Message..!");
			
		}else{
			
			c_vals = mydb.retrieve_name_nymber_parents();

			if (c_vals.getCount() != 0) {

				c_vals.moveToFirst();
				do {

					/*String name = c_vals.getString(c_vals
							.getColumnIndex("username"));*/
					String number = c_vals.getString(c_vals
							.getColumnIndex("number"));
					alist.add(number);

				} while (c_vals.moveToNext());

				
				for(int i=0;i<alist.size();i++){
					
					String val=alist.get(i);
					SmsManager.getDefault().sendTextMessage(val, null, data, null, null);
				}
				
			} else {

				new AlertDialog.Builder(this).setMessage("No Numbers...!").setIcon(R.drawable.ic_launcher).show();
			}

			
			
		}
		
	}

}
