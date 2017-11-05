package com.example.momolomo.texttospeechdemo;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    /**Called when the activity is first created*/
    private TextToSpeech tts;
    private Button buttonSpeak;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tts= new TextToSpeech(this, this);
        buttonSpeak = (Button) findViewById(R.id.button1);
        editText= (EditText) findViewById(R.id.editText1);
        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });
    }

    @Override
    protected void onDestroy() {
        //Don't forget to shutdown tts!
        if(tts!=null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status== TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.UK);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(MainActivity.this, "This language is not supported", Toast.LENGTH_SHORT).show();
            } else {
                buttonSpeak.setEnabled(true);
                speakOut();
            }
        } else
            {
                Log.e("TTS","INITIALIZATION FAILED");
            }
        }
        private void speakOut() {
            String text = editText.getText().toString();
            tts.speak(text, TextToSpeech.QUEUE_FLUSH,null);

    }


}
