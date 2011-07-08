/*
 * @PROJECT: UPCal
 * UnitPriceAdapter.javacreat on Jul 1, 2011
 * @author robinwong51@yahoo.com
 * @version:
 * @comment:
 * 
 */
package com.hyanwang.methods;

import java.sql.Timestamp;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UnitPriceAdapter {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_TIMESTAMP = "time"; // Time stamp of record
	public static final String KEY_UPVAL = "up_val"; // Unit Price
	public static final String DATABASE_TABLE = "history";
	private Context context;
	private SQLiteDatabase database;
	private UnitPriceDatabaseHelper dbhelper;

	public UnitPriceAdapter(Context context) {
		this.context = context;
	}

	public UnitPriceAdapter open() throws SQLException {
		dbhelper = new UnitPriceDatabaseHelper(context);
		database = dbhelper.getWritableDatabase(); // create database if not
													// exist it
		return this;
	}

	public void close() {
		dbhelper.close(); // close database
	}

	/* create a record */
	public long createrRecord(String upval) {
		ContentValues initValues = createContentValues(upval);
		return database.insert(DATABASE_TABLE, null, initValues);
	}

	/* clear all */
	public long clearAll() {
		return database.delete(DATABASE_TABLE, KEY_TIMESTAMP + "=" + "*", null);
	}

	/* show me the Data */
	public Cursor fetchCursor() {
		return database.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_TIMESTAMP,
				KEY_UPVAL }, null, null, null, null, KEY_ROWID + " desc");
	}

	private ContentValues createContentValues(String unitprice) {
		ContentValues values = new ContentValues();
		Timestamp recordtimeTimestamp = new Timestamp(new Date().getTime());
		values.put(KEY_TIMESTAMP, String.valueOf(recordtimeTimestamp));
		values.put(KEY_UPVAL, unitprice);
		return values;
	}

}
