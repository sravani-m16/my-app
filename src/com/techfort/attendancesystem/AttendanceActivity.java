package com.techfort.attendancesystem;


import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class AttendanceActivity extends Activity implements OnClickListener {
	Spinner sp1_branches, sp2_semisters;
	ImageView img1;
	String arr_branches[] = { "CSE", "EEE", "ECE" };
	String arr_semisters[] = { "1st year", "2-1", "2-2", "3-1", "3-2",
			"4-1", "4-2" };
	int yrs, day, mnt;
	static int DAT_PIC = 1;
	//String roll_c[] = {"13UP1A05","14UP1A05","15UP1A05","16UP1A05","17UP1A05","18UP1A05","19UP1A05","20UP1A05"};
	//String roll_e[] = {"13UP1A04","14UP1A04","15UP1A04","16UP1A04","17UP1A04","18UP1A04","19UP1A04","20UP1A04"};
	//String roll_ee[] = {"13UP1A02","14UP1A02","15UP1A02","16UP1A02","17UP1A02","18UP1A02","19UP1A02","20UP1A02"};
	String roll, ser_no, banch, date, sem, branches, end_no;
	Button save;
	LinearLayout rell;
	AutoCompleteTextView ac;	

	EditText ed_date, ed_roll_serical, ed_rol_strt, ed_end;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Calendar c = Calendar.getInstance();

		// hrs=c.get(Calendar.HOUR);
		// min=c.get(Calendar.MINUTE);
		yrs = c.get(Calendar.YEAR);
		day = c.get(Calendar.DAY_OF_MONTH);
		mnt = c.get(Calendar.MONTH);

		setContentView(R.layout.activity_attendance);
		rell = (LinearLayout) findViewById(R.id.tran);
		rell.getBackground().setAlpha(40);
		sp1_branches = (Spinner) findViewById(R.id.spinner1);
		sp2_semisters = (Spinner) findViewById(R.id.spinner2);
		img1 = (ImageView) findViewById(R.id.imageView1);
		/*
		 * save=(Button)findViewById(R.id.button1);
		 * save.setOnClickListener(this);
		 */
		ed_date = (EditText) findViewById(R.id.editText1);
		ed_roll_serical = (EditText) findViewById(R.id.editText2);
		ed_rol_strt = (EditText) findViewById(R.id.editText3);
		ed_end = (EditText) findViewById(R.id.editText4);
		img1.setOnClickListener(this);
		ac=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		/*final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>  
        (this,android.R.layout.select_dialog_item,roll_c);
		final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>  
        (this,android.R.layout.select_dialog_item,roll_e);
		final ArrayAdapter<String> adapter3 = new ArrayAdapter<String>  
        (this,android.R.layout.select_dialog_item,roll_ee);*/
		sp1_branches.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, arr_branches));
		sp2_semisters.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, arr_semisters));
		sp1_branches.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				branches = arr_branches[arg2];
				/*if(branches=="CSE")
				{
					ac.setThreshold(1);
					ac.setAdapter(adapter1);
					ac.setTextColor(Color.GRAY);
					
				}
				if(branches=="ECE")
				{
					ac.setThreshold(1);
					ac.setAdapter(adapter2);
					ac.setTextColor(Color.GRAY);
					
				}
				if(branches=="EEE")
				{
					ac.setThreshold(1);
					ac.setAdapter(adapter3);
					ac.setTextColor(Color.GRAY);
					
				}*/
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		sp2_semisters.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub

				sem = arr_semisters[arg2];

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.attendance, menu);
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		showDialog(DAT_PIC);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub

		DatePickerDialog dd = new DatePickerDialog(this, olink, yrs, mnt, day);
		return dd;

	}

	OnDateSetListener olink = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			yrs = year;
			mnt = monthOfYear + 1;
			day = dayOfMonth;
			ed_date.setText(yrs + ":" + mnt + ":" + day);

		}
	};

	public void saveContent(View v) {

		roll = ed_roll_serical.getText().toString();
		ser_no = ed_rol_strt.getText().toString();
		date = ed_date.getText().toString();
		end_no = ed_end.getText().toString();

		if (roll.equals("") && ser_no.equals("") && date.equals("")
				&& end_no.equals("")) {

			Toast.makeText(this, "oops...Enter data...!", Toast.LENGTH_LONG)
					.show();
		} else {

			Intent isheet = new Intent(this, SheetActivity.class);
			isheet.putExtra("Key_roll", roll);// serial no
			isheet.putExtra("Key_banch", branches);// branch
			isheet.putExtra("Key_sem", sem);// sem
			isheet.putExtra("Key_ser", ser_no);// startting
			isheet.putExtra("Key_date_no", date);// date
			isheet.putExtra("Key_date_last", end_no);// ends with
			startActivity(isheet);
		}
	}

}
