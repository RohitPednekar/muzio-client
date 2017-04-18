package musicplayer.muzio.com.muzioclient;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.SlideFragmentBuilder;

/**
 * Created by Sarvesh on 4/13/2017.
 */


    public class IntroActivity extends MaterialIntroActivity {

        SharedPreferences sharedPreferences;
        boolean firstTime;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            sharedPreferences = getSharedPreferences("ShaPreferences", Context.MODE_PRIVATE);
            firstTime = sharedPreferences.getBoolean("first", false);


            if (firstTime) {

                Intent intent = new Intent(IntroActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            } else {


                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("first", true);
                editor.apply();


                hideBackButton();

                addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.colorPrimary)
                        .buttonsColor(R.color.colorAccent)
                        .neededPermissions(new String[]{

                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE})
                        .title("Hello there")
                        .description("Before we get started we need a Few Permissions")
                        .build());


                addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.colorPrimary)
                        .buttonsColor(R.color.colorAccent)
                        .image(R.mipmap.ic_launcher)
                        .title("Almost there")
                        .build());

            }
        }


        @Override
        public void onFinish() {
            super.onFinish();

            Intent i = new Intent(IntroActivity.this,MainActivity.class);
            startActivity(i);
            finish();

        }

    }
