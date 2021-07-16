package com.example.integratesocial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

public class fab extends AppCompatActivity {
    private CallbackManager callback_manager;

    LoginButton login_button;
    TextView TF1,TF2;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);
        login_button=(LoginButton)findViewById(R.id.login_button);
        TF1=(TextView)findViewById(R.id.TF1);
        TF2=(TextView)findViewById(R.id.TF2);
        img=(ImageView)findViewById(R.id.IMG);

        callback_manager= CallbackManager.Factory.create();

        login_button.setPermissions(Arrays.asList("email","public_profile"));

        login_button.registerCallback(callback_manager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callback_manager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    AccessTokenTracker tokenTracker=new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken==null)
            {
                TF1.setText("");
                TF2.setText("");
                img.setImageResource(0);
                Toast.makeText(fab.this,"user logged out",Toast.LENGTH_LONG).show();
            }
            else
                loaduserProfile(currentAccessToken);
        }
    };
    private void loaduserProfile(AccessToken newAccesstoken)
    {
        GraphRequest request=GraphRequest.newMeRequest(newAccesstoken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if(object!=null) {
                    String email=object.getString("email");
                    String name=object.getString("name");
                    TF1.setText(name);
                    TF2.setText(email);
                    String img_url="https://graph.facebook.com/"+loginResult.getAccessToken().getUserId()+"/picture?return_ssl_resources=1";
                Picasso.get().load(img_url);
                }
            }
        });
        Bundle parameters=new Bundle();
        parameters.putString("fields","first_name,last_name");
        request.setParameters(parameters);
        request.executeAsync();
    }

}
