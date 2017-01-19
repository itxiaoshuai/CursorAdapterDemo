package com.arvin.cursoradapterdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectNumActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

	private ListView mLv;
	private Cursor mCursor;
	public static final String KEY_NUM="key_num";
	private ArrayList<NumBean> datas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_num);
		mLv = (ListView) findViewById(R.id.listView);
		mCursor = ContactsDAO.getAllCursor(this);
		SelectNumAdapter adapter = new SelectNumAdapter(this, mCursor);
		mLv.setAdapter(adapter);
		mLv.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		NumBean bean =ContactsDAO.getNumBean(mCursor);
		Intent data =new Intent();
		data.putExtra(KEY_NUM,bean.Number);
		setResult(Activity.RESULT_OK,data);
		finish();
	}

	class SelectNumAdapter extends CursorAdapter {

		public SelectNumAdapter(Context context, Cursor c) {
			super(context, c);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			return View.inflate(context, R.layout.item_select_number, null);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			//view
			ImageView icon = (ImageView) view
					.findViewById(R.id.item_select_num_iv_icon);
			TextView name = (TextView) view
					.findViewById(R.id.item_select_num_tv_name);
			TextView num = (TextView) view
					.findViewById(R.id.item_select_num_tv_num);

			//view+data
			NumBean bean = ContactsDAO.getNumBean(cursor);
			icon.setImageBitmap(ContactsDAO.getBitmap(context, bean.iconId));
			name.setText(bean.Name);
			num.setText(bean.Number);
		}
	}
}
