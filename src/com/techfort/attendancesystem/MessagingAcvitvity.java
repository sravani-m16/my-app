package com.techfort.attendancesystem;


import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.database.Mydatabseone;

@SuppressLint("ShowToast")
public class MessagingAcvitvity extends Activity implements OnClickListener, android.content.DialogInterface.OnClickListener, OnItemClickListener {
	Button msg,sel_msg;
	String name,number,nm;
	EditText ed;
	ListView lv;
	Mydatabseone mydb;
	ArrayList<String> alist = new ArrayList<String>();
	ArrayList<String> alist_of_numbers = new ArrayList<String>();
	LinearLayout lll;
	
	Cursor c_vals;
	ArrayAdapter<String> ad;
	AlertDialog.Builder al ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_messaging_acvitvity);
		lv = (ListView) findViewById(R.id.listView1);
		msg = (Button) findViewById(R.id.button1);
		sel_msg = (Button) findViewById(R.id.button3);
		lll=(LinearLayout)findViewById(R.id.ler);
		lll.getBackground().setAlpha(40);
		msg.setOnClickListener(this);
		sel_msg.setOnClickListener(this);
		mydb = new Mydatabseone(this);
		specific_nethod();
		registerForContextMenu(lv);

	}
	public void messageAll(View v){
		startActivity(new Intent(this,AllMessage.class));
		
		
	}
	private void specific_nethod() {
		// TODO Auto-generated method stub
		c_vals = mydb.retrieve_name_nymber_parents();

		if (c_vals.getCount() != 0) {

			c_vals.moveToFirst();
			do {

				String name = c_vals.getString(c_vals
						.getColumnIndex("username"));
				String number = c_vals.getString(c_vals
						.getColumnIndex("number"));
				//alist_of_numbers.add(number);
				alist.add(name + "~" + number);

			} while (c_vals.moveToNext());

		} else {

			alist.add("opps...! no data");
		}

		ad = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, alist);
		
		ad.notifyDataSetChanged();
		
		lv.setAdapter(ad);
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lv.setOnItemClickListener(this);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.mess_call_acvitvity, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@SuppressLint("InlinedApi")
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();

		nm = alist.get(info.position);
		String arr[] = nm.split("~");
		 name = arr[0];
		 number = arr[1];
		System.out.println("the values is:" + name + "  for " + number);
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.call:

			startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ number)));

			break;

		case R.id.message:
			ed = new EditText(this);
			ed.setHint("ADD TEXT");
			ed.setTextColor(Color.YELLOW);
			al = new AlertDialog.Builder(this);
			al.setView(ed);
			al.setPositiveButton("Send", this);
			al.show();
			

			break;

		case R.id.delete:
			mydb = new Mydatabseone(this);
			mydb.delete_data(number);
			ad.notifyDataSetChanged();
			Intent intent = new Intent(getApplicationContext(), MessagingAcvitvity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			
		

		default:
			break;
		}

		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.messaging_acvitvity, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		mydb.delete_all();
		Intent i = new Intent(this, MessagingAcvitvity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
		// //lv.setAdapter(new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1,alist));
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
switch (v.getId()) {
case R.id.button1:
	startActivity(new Intent(this, NewMessage.class)
	.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
finish();
	break;
	case R.id.button3:
		//15555215554
		
		startActivity(new Intent(this, SelectedMessage.class).putStringArrayListExtra("My_call_list", alist_of_numbers));
		
		break;

default:
	break;
}
		
	}

	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub
		
		String S_Msg=ed.getText().toString();
		if(S_Msg.equals("")){
			
			Toast.makeText(MessagingAcvitvity.this, "Please Enter Message",Toast.LENGTH_LONG).show();
			
			
		}else{
		SmsManager.getDefault().sendTextMessage(number, null, S_Msg,null, null);
		Toast.makeText(MessagingAcvitvity.this, "Message send successfully",Toast.LENGTH_LONG).show();
		}
		
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		startActivity(new Intent(MessagingAcvitvity.this,DashBoardActivity.class));
		this.finish();
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
		// TODO Auto-generated method stub
		sel_msg.setVisibility(Button.VISIBLE);
		Toast.makeText(MessagingAcvitvity.this, ""+alist.get(position), 30).show();
		alist_of_numbers.add(alist.get(position));
		
		
	}
	

}
