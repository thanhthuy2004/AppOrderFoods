package com.ltdd.orderfood;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ltdd.orderfood.Common.Common;
import com.ltdd.orderfood.Model.User;

public class ChangPass extends AppCompatActivity {
    EditText edtPhone,edtOldPass,edtPassword;
    Button btnSave;
    String sdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chang_pass);
        edtPhone =  findViewById(R.id.edtPhone);
        edtOldPass=  findViewById(R.id.oldPass);
        edtPassword =  findViewById(R.id.newPass);

        btnSave =  findViewById(R.id.btnSignUp);



        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference talbe_user = database.getReference("User");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Common.isConnectedToInterner(getBaseContext())) {
                    final ProgressDialog mDialog = new ProgressDialog(ChangPass.this);
                    mDialog.setMessage("Vui lòng chờ");
                    mDialog.show();

                    talbe_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                                mDialog.dismiss();

                                User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                                String passInput = edtOldPass.getText().toString();
                                if(passInput.equals(user.getPassword())) {
                                    talbe_user.child(edtPhone.getText().toString()).setValue(user);
                                    Toast.makeText(ChangPass.this, "Bạn thay đổi thành công", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                                else{
                                    Toast.makeText(ChangPass.this, "Mật khẩu cũ mà bạn nhập không đúng!", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            } else {
                                mDialog.dismiss();
                                Toast.makeText(ChangPass.this, "Vui Lòng kiểm tra lại số điện thoại", Toast.LENGTH_LONG).show();
                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else {
                    Toast.makeText(ChangPass.this,"Hãy kiểm tra đường truyền Internet của bạn",Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }
}
