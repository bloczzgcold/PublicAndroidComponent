package com.qdtckj.component.commons.file;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by zhangzhenguo on 2018/5/3.
 * assets 资源文件操作
 */

public class AssetsFileUtil {
    /**
     * 获取assets目录下的文件数据
     *
     * @param fileName 文件名
     * @param context  上下文对象
     * @return string
     */
    public static String readAssetsJson(Context context, String fileName) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 复制asset文件到指定目录
     *
     * @param context 上下文对象
     * @param oldPath asset下的路径
     * @param newPath SD卡下保存路径
     */
    public static void copyAssets(Context context, String oldPath, String newPath) {
        try {
            String fileNames[] = context.getAssets().list(oldPath);// 获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {     // 如果是目录
                File file = new File(newPath);
                file.mkdirs();  // 如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyAssets(context, oldPath + "/" + fileName, newPath + "/" + fileName);
                }
            } else {// 如果是文件
                InputStream is = context.getAssets().open(oldPath);
                File file = new File(newPath);
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) != -1) {   // 循环从输入流读取
                    // buffer字节
                    fos.write(buffer, 0, byteCount);    // 将读取的输入流写入到输出流
                }
                fos.flush();// 刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件
     *
     * @param fileName 文件名称
     * @return string
     */
    public static String readFile(String fileName) {
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String readline = "";
            StringBuffer sb = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                sb.append(readline);
            }
            br.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取数据到文件
     *
     * @param filePath 文件路径
     * @param string   内容
     * @return true or false
     */
    public static boolean writeFileString(String filePath, String string) {
        try {
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(string.getBytes());
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
