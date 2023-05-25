package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class BalanceBank extends AppCompatActivity {

    TextView txtBalance, txtBankName,txtAccountNumber,txtAmount;
    Button withdraw, deposit;
    String amount;
    Account account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_bank);


        //Initialize and assign the textview to specific UI elements
        txtBankName=(TextView) findViewById(R.id.txtSetBankName);
        txtAccountNumber=(TextView) findViewById(R.id.txtSetAccountNumber);
        txtBalance=(TextView) findViewById(R.id.textView8);
        txtAmount=(TextView) findViewById(R.id.txtAmountWanted);

        withdraw=(Button) findViewById(R.id.btnWithdraw);
        deposit=(Button) findViewById(R.id.btnDeposit);


        //retrieving the account object from main activity
        account=(Account) getIntent().getSerializableExtra("Account");


        //set the text to the account values received from main activity
        txtBankName.setText("Account balance of "+account.getBankName());
        txtAccountNumber.setText("Your Account Number is "+account.getAccountNumber());
        txtBalance.setText("Your current balance is $"+account.getBalance());


        //withdraw button to withdraw money
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the text wrote inside the app
                amount=txtAmount.getText().toString();
                //check if the amount is empty or not
                if(!amount.isEmpty()){
                    //convert string to int
                    int amountWanted=Integer.parseInt(amount);
                    if(amountWanted<=account.getBalance()){
                        account.withdraw(amountWanted);
                        txtBalance.setText("Your current balance is $" + account.getBalance());


                        //create a new intent to send the updated version to the main activity
                        Intent intent = new Intent();
                        intent.putExtra("Account", account);
                        intent.putExtra("WithdrawAmount",amountWanted);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Insufficient funds", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    // Handle case when no amount is entered
                    Toast.makeText(getApplicationContext(), "Please enter an amount", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //deposit the specific amount to the account
        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount=txtAmount.getText().toString();
                if(!amount.isEmpty()){
                    int depositWanted=Integer.parseInt(amount);
                    account.deposit(depositWanted);
                    txtBalance.setText("Your current balance is $" + account.getBalance());

                    Intent intent = new Intent();
                    intent.putExtra("Account", account);
                    intent.putExtra("DepositAmount",depositWanted);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Please enter an amount", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }
}