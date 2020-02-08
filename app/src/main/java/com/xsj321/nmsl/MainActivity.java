package com.xsj321.nmsl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xsj321.nmsl.Util.*;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static Context MainContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainContext = this;
        final EditText InputTextBox = findViewById(R.id.find_emoji_text);
        final Button TranslationButton = findViewById(R.id.find_emoji_button);
        final Button ShareButton = findViewById(R.id.send);
        final EditText TranslationRes = findViewById(R.id.res);
        final TextUtil textUtil = new TextUtil();
        TranslationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TranslationRes.setText(textUtil.FindEmoji(InputTextBox.getText().toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        TranslationRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CopyButtonLibrary copyButtonLibrary= new CopyButtonLibrary(getApplicationContext(),TranslationRes);
                copyButtonLibrary.init();
            }
        });

        ShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_TEXT,TranslationRes.getText().toString());
                intent.setType("text/plain");
                startActivity(intent);
            }
        });
    }

    public static Context getMainContext() {
        return MainContext;
    }


}
