package com.example.dicegame;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Button btnGamePlay, btnAccount;
    TextView txtBalance,txtAccountN,txtBankN;
    Account account;


    int randomAccount=new Random().nextInt(32352131)+512312312;

    int balanceBank=530;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGamePlay=(Button) findViewById(R.id.btn1);
        btnAccount=(Button) findViewById(R.id.btn2);
        txtAccountN=(TextView) findViewById(R.id.txtAccountNumber);
        txtBankN=(TextView) findViewById(R.id.txtBankName);
        txtBalance=(TextView) findViewById(R.id.txtBalance);

        account=new Account(String.valueOf(randomAccount),balanceBank,"Chase");

        txtAccountN.setText(String.valueOf(account.getAccountNumber()));
        txtBalance.setText("$"+String.valueOf(account.getBalance()));
        txtBankN.setText(account.getBankName());



        btnGamePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent(MainActivity.this,GamePlay.class);
                inte.putExtra("Account",account);
                startActivity(inte);
            }
        });

        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent inte=new Intent(getApplicationContext(),BalanceBank.class);
                inte.putExtra("Account", account);
                newBalance.launch(inte);


            }
        });

        
    }

    private final ActivityResultLauncher<Intent> newBalance=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode()==Activity.RESULT_OK){
                    if(result.getData()!=null && result.getData().hasExtra("Account")){
                        Account updatedAccount=(Account) result.getData().getSerializableExtra("Account");
                        int depositAmount=result.getData().getIntExtra("DepositAmount",0);
                        int withdrawAmount=result.getData().getIntExtra("WithdrawAmount",0);
                        txtBalance.setText("$" + String.valueOf(updatedAccount.getBalance()));

                        if(depositAmount>0){
                            Toast.makeText(getApplicationContext(), "You got a deposit of $"+depositAmount, Toast.LENGTH_SHORT).show();
                        }
                        if(withdrawAmount>0){
                            Toast.makeText(getApplicationContext(), "You got a withdraw of $"+withdrawAmount, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

}