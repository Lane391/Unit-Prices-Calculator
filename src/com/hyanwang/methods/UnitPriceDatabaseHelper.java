/*
 * @PROJECT: UPCal
 * UnitPriceDatabaseHelper.javacreat on Jul 1, 2011
 * @author robinwong51@yahoo.com
 * @version: 1
 * @comment:Database helper 
 * 
 */
package com.hyanwang.methods;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**/
public class UnitPriceDatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "unitprice_db"; // database's
																// name
	private static final int DATABASE_VERSION = 1; // database's version
	private static final String CREATE_TABLE = "create table history(_id integer primary key autoincrement, time timestamp, up_val double);";
	private static final String TAG = "Database Help";

	public UnitPriceDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
		Log.w(TAG, "creat table:history");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, UnitPriceDatabaseHelper.class.getName()
				+ "Upgrading database from " + oldVersion + " to " + newVersion
				+ ", which will destory all old data ");
		db.execSQL("DROP TABLE IF EXISTS history");
	}

}
