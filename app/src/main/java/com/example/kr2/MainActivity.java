package com.example.kr2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    ImageView mBat;
    ImageView mBall;
    ImageView mTarget;
    TextView textViewScore;
    int mBallX;
    int ballSpeed;
    int ballSpeedX;
    int mBatX;
    int mBallY;
    int score;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewScore = findViewById(R.id.textViewScore);
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.openDatabase();

        ballSpeed = -20;
        ballSpeedX = 15;

        init();
        toStart();
    }

    public void init() {
        mBall = findViewById(R.id.mBall);
        mBat = findViewById(R.id.mBat);
        mTarget = findViewById(R.id.mTarget);
        mBall.setOnTouchListener(this);
        mBat.setOnTouchListener(this);
        score = User.activeUser.getScore();
    }

    public void toStart() {
        ballSpeed = -20;
        ballSpeedX = 15;
        mBall.setX(500);
        mBall.setY(750);
        mBat.setX(500);
        mBat.setY(1800);
    }

    public void hitWall() {
        ballSpeedX *= -1;
    }

    public void hitFloor() {
        score = 0;
        textViewScore.setText(Integer.toString(score));
        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Игра окончена")
                .setMessage("Вы не отбили мяч")
                .setCancelable(false)
                .setPositiveButton("Играть ещё раз", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        alertDialog = builder.create();
        alertDialog.show();
        toStart();
    }

    public void batCollision() {
        ballSpeed *= -1.06;
        ballSpeedX *= 1.06;
    }

    public void hitTarget() {
        score++;
        if (score >= 121) score -= 126;
        ballSpeed *= -1;
        textViewScore.setText(Integer.toString(score));
        return;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();

        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                mBallX = X - lParams.leftMargin;
                mBallY = Y - lParams.topMargin;
                mBatX = X - lParams.bottomMargin;

                mBall.setY(mBall.getY() - ballSpeed);

                break;

            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                layoutParams.leftMargin = X - mBallX;
                layoutParams.topMargin = Y - mBallY;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;

                view.setLayoutParams(layoutParams);

                mBall.setY(mBall.getY() - ballSpeed);
                mBall.setX(mBall.getX() - ballSpeedX);

                if (mBall.getY() <= mTarget.getY()) {
                    hitTarget();
                }

                if (mBall.getY() >= mBat.getY() - 80 && mBall.getX() >= mBat.getX() - 120
                        && mBall.getX() <= mBat.getX() + 120) {
                    ballSpeedX *= (mBall.getX() - mBat.getX()) * 0.1;
                    batCollision();
                }

                if (mBat.getX() >= layoutParams.rightMargin-50) {
                    mBat.setX(layoutParams.rightMargin-50);
                }
                if (mBat.getX() <= layoutParams.leftMargin+50) {
                    mBat.setX(layoutParams.leftMargin+50);
                }

                if (mBall.getY() >= 2000) {
                    hitFloor();
                }

                if (mBall.getX() >= 1000) {
                    hitWall();
                }

                if (mBall.getX() <= 0) {
                    hitWall();
                }

                if (mBat.getY() <= 1800) {
                    mBat.setY(1800);
                }
                if (mBat.getY() >= 1770) {
                    mBat.setY(1770);
                }
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        score++;
        mBall.setY(mBall.getY() - 1);
    }

    public void buttonBackToMainClick(View view) {
        databaseHelper.updateDatabase(User.activeUser.getId(), User.activeUser.getUsername(), score);
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.updateDatabase(User.activeUser.getId(), User.activeUser.getUsername(), score);
        databaseHelper.closeDatabase();
    }
}