package by.bstu.pnv.education.laba_1_passworld;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

import java.util.Random;

/**
 * Created by Dima on 14.02.2018.
 */

public class MyPasswordTransformationMethod extends PasswordTransformationMethod {

    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return new PasswordCharSequence(source);
    }

    private class PasswordCharSequence implements CharSequence {

        private CharSequence mSource;

        public PasswordCharSequence(CharSequence source) {
            mSource = source;
        }

        public char charAt(int index) {
            char[] emojis = {'✌', '☺', '✋', '✈', '✒', '⏳'};
            Random rand = new Random(index);
            return emojis[rand.nextInt(emojis.length - 1)];
        }

        public int length() {
            return mSource.length();
        }

        public CharSequence subSequence(int start, int end) {
            return mSource.subSequence(start, end); // Return default
        }
    }
};