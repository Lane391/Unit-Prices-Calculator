/*
 * @PROJECT: UPCal
 * ResultActivity.javacreat on Jun 13, 2011
 * @author robinwong51@yahoo.com
 * @version:
 * @comment:
 * 
 */
package com.hyanwang.unitpricecal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.hyanwang.methods.CalClass;
import com.hyanwang.methods.UnitPriceAdapter;
import com.hyanwang.unitpricescal.R;

public class ResultActivity extends Activity {
	private TextView resultTextView;
	private UnitPriceAdapter unAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		/* 加载默认Layout */
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);

		Bundle bundle = this.getIntent().getExtras();

		double totalePrices = bundle.getDouble("totalePrices");
		double netvalue = bundle.getDouble("netValue");

		CalClass upcalculatorCalClass = new CalClass();

		resultTextView = (TextView) findViewById(R.id.resultUp);

		String resultString = upcalculatorCalClass.untilpkString(totalePrices,
				netvalue);
		resultTextView.setText("计算出来的单价" + resultString);

		// TODO:将查询的数据添加到数据库中去

		/*
		 * 记录查询单价的数据信息
		 */
		unAdapter = new UnitPriceAdapter(this);
		unAdapter.open();
		unAdapter.createrRecord(resultString);

	}

}
