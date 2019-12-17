package projects.uikt.com.myukt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class SettingsActivity extends AppCompatActivity {
SwitchCompat enableNotificationSwitch,enableSoundSwitch/*,enableRemindersSwitch*/;
ImageView editAvatarImageView,editPasswordImageView;
Bitmap bitmap=null;
public static final int IMAGE_REQUEST=29;

void selectImage(){
    Intent intent=new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(intent,IMAGE_REQUEST);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        enableNotificationSwitch=findViewById(R.id.enable_notifications);
        //enableRemindersSwitch=findViewById(R.id.enable_reminders);
        enableSoundSwitch=findViewById(R.id.enable_sounds);
        editAvatarImageView=findViewById(R.id.change_avatar);
        editPasswordImageView=findViewById(R.id.change_password);
        editPasswordImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(SettingsActivity.this);
                View view=getLayoutInflater().inflate(R.layout.change_passwod_dialog,null);
                final EditText oldEmailEditText,newEmailEditText;
                TextView changePasswordButton=view.findViewById(R.id.change_password_button);
                oldEmailEditText=view.findViewById(R.id.old_password);
                newEmailEditText=view.findViewById(R.id.new_password);
                changePasswordButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!oldEmailEditText.getText().toString().isEmpty()){
                            if(!newEmailEditText.getText().toString().isEmpty()){
                                if(!oldEmailEditText.getText().toString().equals(newEmailEditText.getText().toString())){

                                }else{
                                    Snackbar.make(v, R.string.change_at_least_a_char, Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            }else{
                                Snackbar.make(v, R.string.enter_new_paasword_first, Snackbar.LENGTH_LONG).
                                        setAction("Action", null).show();
                            }
                        }else{
                            Snackbar.make(v, R.string.enter_old_password_first, Snackbar.LENGTH_LONG).
                                    setAction("Action", null).show();
                        }
                    }
                });
                builder.setView(view);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });
        editAvatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                AlertDialog.Builder builder=new AlertDialog.Builder(SettingsActivity.this);
                View view=getLayoutInflater().inflate(R.layout.new_avatar_showing_dialogue,null);
                de.hdodenhof.circleimageview.CircleImageView avatar=view.findViewById(R.id.avatar_img_view);
                TextView save,cancel,change;
                save=view.findViewById(R.id.update_avatar);
                cancel=view.findViewById(R.id.undo);
                change=view.findViewById(R.id.change_avatar_button);
                builder.setView(view);
                final AlertDialog alertDialog=builder.create();
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });
                change.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectImage();
                        alertDialog.cancel();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });
                avatar.setImageBitmap(bitmap);
                alertDialog.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
