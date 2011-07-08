/*
* @PROJECT: UPCal
* Dbhelper.javacreat on Jun 23, 2011
* @author robinwong51@yahoo.com
* @version:1.5
* @comment: 将添加数据日志功能
* 
*/

package com.hyanwang.unitpricecal;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.hyanwang.methods.UnitPriceAdapter;
import com.hyanwang.unitpricescal.R;

public class MainActivity extends Activity {
	/*日志分析常量*/
	private static final String TAG = "upc_process";
	/*总价的值*/
	private EditText totalPricesValueEditText; // 
	/*净量的值*/
	private EditText netValueEditText; //
	/*单价计算按钮*/
	private Button upcButton;

	private Cursor cursor;
	private ListView list;
	private UnitPriceAdapter uniterPriceAdapter;
	
	/*show me the data*/
	private void fillData(){
		cursor = uniterPriceAdapter.fetchCursor();
		startManagingCursor(cursor);
		String[] from = new String[] {UnitPriceAdapter.KEY_TIMESTAMP,UnitPriceAdapter.KEY_UPVAL};
		int[] to = new int[]{R.id.date, R.id.value};
		SimpleCursorAdapter recordlistAdapter = new SimpleCursorAdapter(this, R.layout.record, cursor, from, to);
		list.setAdapter(recordlistAdapter);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// TODO:找到ID
		/*将id:calculatorUP赋值到upcButton变量中*/
		upcButton = (Button) findViewById(R.id.calcutlatorUP);
		list = (ListView)findViewById(R.id.history_list); //数据记录列表
		uniterPriceAdapter = new UnitPriceAdapter(this);
		uniterPriceAdapter.open();
		upcButton.setOnClickListener(listener);
	}

	// TODO:计算按钮监听器
	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Bundle bundle = new Bundle();
			totalPricesValueEditText = (EditText) findViewById(R.id.TotalPricesValue);
			netValueEditText = (EditText) findViewById(R.id.NetValue);

			bundle.putDouble("totalePrices", Double
					.parseDouble(totalPricesValueEditText.getText().toString()));
			bundle.putDouble("netValue",
					Double.parseDouble(netValueEditText.getText().toString()));

			Intent intent = new Intent(MainActivity.this, ResultActivity.class);
			
			intent.putExtras(bundle);
			startActivity(intent);
			
			fillData(); //show show data

		}
	};

	/* 程序菜单 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/* 关于程序的按钮按钮 */
		menu.add(Menu.NONE, Menu.FIRST + 0, 1, R.string.menu_aboutit).setIcon(
				android.R.drawable.ic_menu_info_details);
		/* 关闭程序的按钮 */
		menu.add(Menu.NONE, Menu.FIRST + 1, 1, R.string.menu_exit).setIcon(
				android.R.drawable.ic_menu_close_clear_cancel);
		return super.onCreateOptionsMenu(menu);
	}

	// 菜单选择动作
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			/*关于菜单的命令，将启动一个关于的Activity*/
			Intent aboutIntent = new Intent(MainActivity.this,
					AboutThisCal.class);
			startActivity(aboutIntent);
			break;

		case 2:
			/*关闭所有程序，退出命令*/
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	/**/
	@Override
	protected void onDestroy() {
		Log.i(TAG, "this programme will be destory");
		super.onDestroy();
	}


}