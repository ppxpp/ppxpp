package me.ppxpp.app.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import me.ppxpp.app.test.utils.STTWrapper;

public class STTTestActivity extends AppCompatActivity {

    private STTWrapper mSTTWrapper;
    private RelativeLayout mContainer;
    private EditText mEditText;
    private CheckBox mCheckBox;
    private Button mSearchBtn;
    private SeekBar mSeekBar;
    private ImageView mIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stttest);
        mSTTWrapper = new STTWrapper(this);
        mEditText = (EditText) findViewById(R.id.edit_test);
        mContainer = (RelativeLayout) findViewById(R.id.edit_container);
        mCheckBox = (CheckBox) findViewById(R.id.checkbox);
        mSearchBtn = (Button) findViewById(R.id.btn_search);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mIcon = (ImageView) findViewById(R.id.icon);

        mSTTWrapper.setEditText(mContainer, mEditText, mIcon);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void start(View view){
        mSTTWrapper.onStart();
    }

    public void recognise(View view){
        mSTTWrapper.onRecognise();
    }

    public void change(View view){
        if (mSearchBtn.getVisibility() == View.VISIBLE){
            mSearchBtn.setVisibility(View.GONE);
        }else{
            mSearchBtn.setVisibility(View.VISIBLE);
        }
    }

    public void volume(View view){
        mSeekBar.getProgress();
        float volume = (float) mSeekBar.getProgress() / (float)mSeekBar.getMax();
        mSTTWrapper.onVolumeChanged(volume);
    }

    public void onResult(View view){
        if (mCheckBox.isChecked()){
            mSTTWrapper.onResult(true, "世界之窗");
        }else{
            mSTTWrapper.onResult(false, null);
        }
    }


}
