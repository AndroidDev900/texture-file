/*
 * Copyright (C) 2008 dev 900.
 * 
 * This file is texture archive
 *
 * Java 1.5 Android Texture Archive is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Java 1.5 Android Texture Archive is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Java 1.5 Archive Texture.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * Created on 06/22/2020
 */
package com.textd;

import android.graphics.Bitmap;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.graphics.BitmapFactory;
import java.io.FileInputStream;
import android.util.Base64;

public class TexdReader {
  File f;
  String data;
  InputStream in;
  String type;
  String dat;
  Bitmap bitt;
  public TexdReader(InputStream i) {
   in = i;
   }
 /*
  * read and check the file
  *
  *
  */
 /**
     * Adds the given argument to the method call. The list of arguments will be
     * initialized if it is <code>null</code>.
     * 
     * @param call
     *            method call
     * @param arg
     *            argument value
     */
  public void read() throws TextureException {
  StringBuilder sb = new StringBuilder();
  //reader
  try {
  InputStream inn = in;
   InputStreamReader rr = new InputStreamReader(inn);
  BufferedReader br = new BufferedReader(rr);
  String ll = "";
  while((ll = br.readLine()) != null) {
    sb.append(ll);
   }
   br.close();
  dat = sb.toString();
  //check the file if contains error
  if(!dat.startsWith("o�TEXDATA������TYPE:") || !dat.contains("TYPE:��MMMM�+tex")) {
   throw new TextureException("invalid texture file");
  }
   data = dat.substring(dat.indexOf(TextureData.TEX) + 3, dat.lastIndexOf(TextureData.TEX));
   type = dat.substring(dat.indexOf(TextureData.TYPE) + TextureData.TYPE.length(), dat.lastIndexOf(TextureData.TYPE));
   } catch(IOException e) {
 throw new TextureException(e.getMessage());
   }
  }
  //get the bitmap from file
  public Bitmap getBitmap(String name) {
  //parse data from file
  String gg = data.substring(data.indexOf(name + ":") + name.length(), data.lastIndexOf(name + ":"));
  //decode from base64 to String
  byte[] decodedString = Base64.decode(gg, Base64.DEFAULT);
  //decode from String to Bitmap
  Bitmap bit = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
if(type.equals(TextureData.TYPE1)) {
  bitt = Bitmap.createBitmap(bit.getWidth(), bit.getHeight(), Bitmap.Config.ALPHA_8);
  } else if(type.equals(TextureData.TYPE2)) {
  bitt = Bitmap.createBitmap(bit.getWidth(), bit.getHeight(), Bitmap.Config.RGB_565);
  } else if(type.equals(TextureData.TYPE3)) {
  bitt = Bitmap.createBitmap(bit.getWidth(), bit.getHeight(), Bitmap.Config.ARGB_4444);
  } else if(type.equals(TextureData.TYPE4)) {
  bitt = Bitmap.createBitmap(bit.getWidth(), bit.getHeight(), Bitmap.Config.ARGB_8888);
  }
  //get Bitmap pixel and color
 for(int x = 0; x == bit.getWidth(); x++) {
 for(int y = 0; y == bit.getHeight(); y++) {
 int color = bit.getPixel(x, y);
 bitt.setPixel(x, y, color);
    }
   }
  return bit;
  }
}
