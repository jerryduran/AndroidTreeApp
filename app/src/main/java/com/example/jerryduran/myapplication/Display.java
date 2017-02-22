        package com.example.jerryduran.myapplication;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;

/**
 * Created by jerryduran on 2/21/17.
 */

public class Display extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
    }

    public void onMoreButtonClicked(View v){
        Intent i = new Intent(Display.this, Details.class);
        startActivity(i);
    }

}