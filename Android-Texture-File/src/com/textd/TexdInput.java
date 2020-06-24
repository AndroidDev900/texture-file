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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import android.util.Base64;

public class TexdInput {
   String data;
   String name;
  public TexdInput(String file) throws TextureException {
  File f = new File(file);
  name = f.getName();
  if(f == null) {
   throw new TextureException("error input is null");
   }
  StringBuilder sb = new StringBuilder();
  try{
   InputStream in = new FileInputStream(f);
   InputStreamReader rr = new InputStreamReader(in);
  BufferedReader br = new BufferedReader(rr);
  byte[] bytes;
  byte[] buffer = new byte[8192];
  int bytesRead;
  ByteArrayOutputStream output = new ByteArrayOutputStream();
  while ((bytesRead = in.read(buffer)) != -1) {
   output.write(buffer, 0, bytesRead);
   }
  bytes = output.toByteArray();
  String encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);
  sb.append(encodedString);
 rr.close();
   br.close();
   } catch(IOException e) {
   throw new TextureException(e.getMessage());
    }
   data = sb.toString();
  }
  public String getData() {
 String cc = name + ":" + data + name + ":";
   return cc;
  }
}
