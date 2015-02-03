package course.labs.permissionsapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ConverterActivity extends Activity {

    private static final String TEXT_KEY = "text";
    private static final String TAG = "Lab-Converter";
    RadioGroup mRadioGroup;
    RadioButton mFahrenheitBtn;
    private RadioButton mRadioButton;
    Button mConvertButton;
    Button mClearButton;
    EditText mEditText;
    TextView mTextView;
    String mText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter_activity);

        if (savedInstanceState != null) {
            mText = savedInstanceState.getString(TEXT_KEY);
        }
        addListenerOnButton();
    }

    // TODO - Implement the temperature conversion logic and other behavior here
    private void addListenerOnButton() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        mEditText = (EditText) findViewById(R.id.converter_activity_editBox);
        final String mFinalEditText = mEditText.getText().toString();

        mTextView = (TextView) findViewById(R.id.converter_activity_textView);
        mTextView.setText(mText);

        mFahrenheitBtn = (RadioButton) findViewById(R.id.radioButtonFahrenheit);
        mFahrenheitBtn.setChecked(true);

        mConvertButton = (Button) findViewById(R.id.converter_activity_btn_convert);
        Button mClearButton = (Button) findViewById(R.id.converter_activity_btn_clear);

        mConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double mFahrenheitValue = 0;
                double mCelsiusValue = 0;
                int selectedId = mRadioGroup.getCheckedRadioButtonId();
                mRadioButton = (RadioButton) findViewById(selectedId);

                if(selectedId == R.id.radioButtonFahrenheit) {
                    mCelsiusValue = Double.parseDouble(mEditText.getText().toString());
                    mFahrenheitValue = ((mCelsiusValue*9)/5)+32;
                }
                else if(selectedId == R.id.radioButtonCelsius){
                    mFahrenheitValue = Double.parseDouble(mEditText.getText().toString());
                    mCelsiusValue = ((mFahrenheitValue-32)*5)/9;
                }

                mTextView.setText( String.valueOf(mCelsiusValue) +" Celsius is " + String.valueOf(mFahrenheitValue) + " Fahrenheit");
            }

        });

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFahrenheitBtn.setChecked(true);
                mEditText.setText("");
                mTextView.setText("");
            }

        });

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mText = savedInstanceState.getString(TEXT_KEY);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(TEXT_KEY, mTextView.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }
}