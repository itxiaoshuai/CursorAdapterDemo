package com.arvin.cursoradapterdemo;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import java.io.InputStream;
import java.util.ArrayList;

/*
 *  @项目名：  CursorAdapterDemo 
 *  @包名：    com.arvin.cursoradapterdemo
 *  @文件名:   ContactsDAO
 *  @创建者:   Administrator
 *  @创建时间: 2017/1/19 12:42
 *  @描述：    获取联系人使用的DAO
 */
public class ContactsDAO {
	//获取所有联系人的Cursor
	public static Cursor getAllCursor(Context context) {
		ContentResolver contentResolver = context.getContentResolver();
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String[] projection = new String[]{
				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER,
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
				ContactsContract.CommonDataKinds.Phone._ID};//返回的列名
		String selection = null;   //查询的条件
		String[] selectionArgs = null;//查询条件的参数
		String sortOrder = null;   //排序
		Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder);
		return cursor;
	}

	//获取联系人姓名 电话
	public static ArrayList<NumBean> getAllContacts(Context context) {
		ArrayList<NumBean> datas = new ArrayList<>();
		ContentResolver cr = context.getContentResolver();
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String[] projection = new String[]{
				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER,
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID
		};
		String selection = null;      //查询的条件
		String[] selectionArgs = null;//查询条件的参数
		String sortOrder = null;      //排序
		Cursor cursor = cr.query(uri, projection, selection, selectionArgs, sortOrder, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				String name = cursor.getString(0);
				String number = cursor.getString(1);
				String id = cursor.getString(2);
				NumBean bean = new NumBean();
				bean.Name = name;
				bean.Number = number;
				bean.iconId = id;
				datas.add(bean);

			}
			cursor.close();
		}
		return datas;

		/**uri 查询的uri
		 * projection 返回的列名
		 * selection 查询的条件
		 * selectionArgs 查询条件的参数
		 * sortOrder 排序*/
	}

	public static NumBean getNumBean(Cursor cursor) {
		String name = cursor.getString(0);
		String num = cursor.getString(1);
		String id = cursor.getString(2);

		NumBean bean = new NumBean();
		bean.Name = name;
		bean.Number = num;
		bean.iconId = id;

		return bean;
	}
	//获取联系人头像的bitmap   content://com.android.contacts
	public static Bitmap getBitmap(Context context, String id){
		ContentResolver cr=context.getContentResolver();
		Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI,id);
		InputStream is = ContactsContract.Contacts.openContactPhotoInputStream(cr, uri);
		return BitmapFactory.decodeStream(is);
	}
}
