package com.qdtckj.component.serialport.thread;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * 串口消息读取线程
 */

public abstract class SerialPortReadThread extends Thread {

    public abstract void onDataReceived(byte[] bytes);

    private static final String TAG = SerialPortReadThread.class.getSimpleName();
    private InputStream mInputStream;
    private byte[] mReadBuffer;

    public SerialPortReadThread(InputStream inputStream) {
        mInputStream = inputStream;
        mReadBuffer = new byte[64];
    }

    @Override
    public void run() {
        super.run();

        while (!isInterrupted()) {
            try {
                if (null == mInputStream) {
                    return;
                }

                Log.i(TAG, "run: ");
                int size = mInputStream.read(mReadBuffer);
                Log.i(TAG,"size = " + size);
                if (-1 == size || 0 >= size) {
                    return;
                }

                byte[] readBytes = new byte[size];

                System.arraycopy(mReadBuffer, 0, readBytes, 0, size);

                Log.i(TAG, "run: readBytes = " + bytes2HexString(readBytes));
                onDataReceived(readBytes);

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    /**
     * 关闭线程 释放资源
     */
    public void release() {
        interrupt();

        if (null != mInputStream) {
            try {
                mInputStream.close();
                mInputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 字节数组转16进制字符串
     */
    public static String bytes2HexString(byte[] b) {
        String r = "";
        if (b == null){
            return "";
        }
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            r += hex.toUpperCase();
        }

        return r;
    }
}
