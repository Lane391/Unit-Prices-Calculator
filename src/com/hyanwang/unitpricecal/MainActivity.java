package com.hyanwang.unitpricecal;

import com.hyanwang.unitpricescal.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText totalPricesValueEditText; //总价的值
    private EditText netValueEditText; //净量的值
    private Button upcButton; //单价计算按钮
    private TextView singleResultTextView; //单个单价显示结果
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //TODO:找到ID
        totalPricesValueEditText=(EditText)findViewById(R.id.TotalPricesValue);
        netValueEditText=(EditText)findViewById(R.id.NetValue);
        upcButton=(Button)findViewById(R.id.calcutlatorUP);
        singleResultTextView=(TextView)findViewById(R.id.singleResult);
        
        upcButton.setOnClickListener(listener);
    }
    //TODO:计算按钮监听器
    private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			float result=0;
			String netvalue = netValueEditText.getText().toString();
			String totalPricesString = totalPricesValueEditText.getText().toString();
			result=Float.parseFloat(totalPricesString)/Float.parseFloat(netvalue);
	        singleResultTextView.setText(String.valueOf(result));
			
		}
	};
}