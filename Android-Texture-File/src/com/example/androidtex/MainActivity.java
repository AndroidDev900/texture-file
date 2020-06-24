package com.example.androidtex;

import android.app.Activity;
import android.os.Bundle;
import com.textd.TexdTools;
import com.textd.TextureFile;
import com.textd.TextureException;
import android.widget.Toast;
import com.textd.TexdConfig;
import com.textd.TexdInput;
import com.textd.TextureData;
import android.graphics.Bitmap;
import com.textd.TexdReader;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

public class MainActivity extends Activity {
 ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       image = new ImageView(this);
 setContentView(image);
  compressFiles();
  }
  public void compressFiles() {
   try {
   TexdInput input = new TexdInput("/storage/emulated/0/input.png");

   TexdInput input1 = new TexdInput("/storage/emulated/0/input1.png");

   String data = input.getData() + input1.getData();

   TextureFile tex = new TextureFile("/storage/emulated/0/test.textd");

   TexdConfig config = new TexdConfig();
  config.setConfig(TextureData.ARGB_8888);

   TexdTools tools = new TexdTools(tex);
    tools.setConfig(config);
    tools.add(data);
    tools.create();
    tools.close();
    } catch(TextureException e) {
  Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }
  }


  public void compressFile() {
  try {
   TexdInput input = new TexdInput("/storage/emulated/0/input1.png");

   TextureFile tex = new TextureFile("/storage/emulated/0/test.textd");

   TexdConfig config = new TexdConfig();
  config.setConfig(TextureData.ARGB_8888);

   TexdTools tools = new TexdTools(tex);
    tools.setConfig(config);
    tools.add(input);
    tools.create();
    tools.close();
    } catch(TextureException e) {
  Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }
   }


 public void readFromAssets() {
  try {
   TexdReader reader = new TexdReader(this.getAssets().open("test.textd"));
   reader.read();
  Bitmap bit = reader.getBitmap("input.png");
  image.setImageBitmap(bit);
   } catch(TextureException e) {
Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    } catch(IOException e) {
 Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
   }
   }


 public void read() {
  try {
TextureFile tex = new TextureFile("/storage/emulated/0/test.textd");
  InputStream in = new FileInputStream(tex.getFile());
   TexdReader reader = new TexdReader(in);
   reader.read();
  Bitmap bit = reader.getBitmap("input.png");
  image.setImageBitmap(bit);
   } catch(TextureException e) {
Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    } catch(IOException e) {
 Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
   }
  }
}
