package com.techfort.attendancesystem;



import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SheetActivity extends Activity implements OnItemClickListener {
	ListView lv;
	CheckBox ch_rem;
	Button dd, view;
	static String roll, ser, branch, date, sem, end_no;
	ArrayList<String> alist = new ArrayList<String>();
	ArrayList<String> alist_of_present = new ArrayList<String>();
	ArrayList<String> alist_of_absent = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		savedInstanceState = getIntent().getExtras();
		//roll = savedInstanceState.getString("Key_roll");
		branch = savedInstanceState.getString("Key_banch");
		sem = savedInstanceState.getString("Key_sem");
		end_no = savedInstanceState.getString("Key_date_last");

		ser = savedInstanceState.getString("Key_ser");
		date = savedInstanceState.getString("Key_date_no");
		dd = (Button) findViewById(R.id.button2);
		view = (Button) findViewById(R.id.button1);
		// ser--.start, br--.end

		int val_rol_srt = Integer.valueOf(ser);
		int val_end = Integer.valueOf(end_no);
		for (int ii = 01; val_end > ii; val_end--) {

			alist.add(roll + val_rol_srt);
			val_rol_srt++;
		}

		lv = (ListView) findViewById(R.id.listView1);
		lv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, alist));
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lv.setOnItemClickListener(this);
		// lv.setAdapter(new Mycustomadapter());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.sheet, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button2:
			Toast.makeText(this, "Today:" + date, Toast.LENGTH_LONG).show();
			break;
		case R.id.button1:

			Intent it = new Intent(SheetActivity.this, csvtry.class);

			it.putStringArrayListExtra("no_student", alist);
			it.putStringArrayListExtra("vpresent", alist_of_present);
			it.putStringArrayListExtra("vabsent", alist_of_absent);
			it.putExtra("key_branch", branch);
			it.putExtra("key_sem", sem);
			//it.putExtra("key_roll", roll)

			startActivity(it);
			Toast.makeText(this, "Today:" + date + "saved", Toast.LENGTH_LONG)
					.show();

			break;

		default:
			break;
		}

	}

	/*
	 * public boolean onOptionsItemSelected(MenuItem item) { // TODO
	 * Auto-generated method stub switch (item.getItemId()) { case
	 * R.id.action_settings: Toast.makeText(this, "Today:" + date,
	 * Toast.LENGTH_LONG).show(); break; case R.id.actionsave:
	 * 
	 * Intent it = new Intent(SheetActivity.this, csvtry.class);
	 * 
	 * it.putStringArrayListExtra("no_student", alist);
	 * it.putStringArrayListExtra("vpresent", alist_of_present);
	 * it.putStringArrayListExtra("vabsent", alist_of_absent);
	 * it.putExtra("key_branch", branch); it.putExtra("key_sem", sem);
	 * 
	 * startActivity(it); Toast.makeText(this, "Today:" + date + "saved",
	 * Toast.LENGTH_LONG) .show();
	 * 
	 * break;
	 * 
	 * default: break; }
	 * 
	 * return super.onOptionsItemSelected(item); }
	 */

	class Mycustomadapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub

			return alist.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(final int position, View vv, ViewGroup parent) {
			// TODO Auto-generated method stub

			vv = getLayoutInflater().inflate(R.layout.activity_sheet, null);
			LinearLayout ll = (LinearLayout) vv.findViewById(R.id.ler);
			ll.getBackground().setAlpha(40);

			TextView tv1 = (TextView) vv.findViewById(R.id.textView1);
			ch_rem = (CheckBox) vv.findViewById(R.id.checkBox1);
			tv1.setText(alist.get(position));

			ch_rem.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@SuppressLint("ShowToast")
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					// TODO Auto-generated method stub
					Toast.makeText(SheetActivity.this,
							"the value" + alist.get(position), 30).show();
					alist_of_present.add(alist.get(position) + "~");
					

					;

				}
			});
			return vv;
		}

	}

	@SuppressLint("ShowToast")
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub

		Toast.makeText(SheetActivity.this, alist.get(position), 30).show();
		alist_of_present.add(alist.get(position));

	}

}
