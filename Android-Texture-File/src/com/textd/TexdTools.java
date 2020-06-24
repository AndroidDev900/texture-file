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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TexdTools {
   int config;
   File texture;
   FileOutputStream fos;
  ArrayList<String> data = new ArrayList<String>();
   String conf = "";
   String texdata = "";
   public TexdTools(TextureFile tex) {
  texture = tex.getFile();
   }
 /**
     * Adds the given argument to the method call. The list of arguments will be
     * initialized if it is <code>null</code>.
     * 
     * @param call
     *            method call
     * @param arg
     *            argument value
     */
   public void setConfig(TexdConfig conf) {
   config = conf.getConfig();
   }
    // add data to file
 /**
     * Adds the given argument to the method call. The list of arguments will be
     * initialized if it is <code>null</code>.
     * 
     * @param call
     *            method call
     * @param arg
     *            argument value
     */
   public void add(TexdInput input) {
  data.add(input.getData());
   }
  // add data to file
 /**
     * Adds the given argument to the method call. The list of arguments will be
     * initialized if it is <code>null</code>.
     * 
     * @param call
     *            method call
     * @param arg
     *            argument value
     */
   public void add(String add) {
   data.add(add);
   }
  //create file
 /**
     * Adds the given argument to the method call. The list of arguments will be
     * initialized if it is <code>null</code>.
     * 
     * @param call
     *            method call
     * @param arg
     *            argument value
     */
   public void create() throws TextureException {
  //convert config to String
  if(config == 0x01) {
    conf = TextureData.TYPE1;
   } else if(config == 0x02) {
    conf = TextureData.TYPE2;
   } else if(config == 0x03) {
    conf = TextureData.TYPE3;
   } else if(config == 0x04) {
    conf = TextureData.TYPE4;
   }
  //extract data
   for(int i = 0; i < data.size(); i++) {
   texdata = data.get(i);
   }
  //writer
   try{
  fos = new FileOutputStream(texture);
   fos.write(TextureData.DATA1);
   fos.write(TextureData.DATA2);
   fos.write(TextureData.TEXDATA.getBytes());
   fos.write(TextureData.DATA2);
   fos.write(TextureData.DATA2);
   fos.write(TextureData.DATA2);
   fos.write(TextureData.DATA2);
   fos.write(TextureData.DATA2);
   fos.write(TextureData.DATA2);
   fos.write(TextureData.TYPE.getBytes());
   fos.write(conf.getBytes());
   fos.write(TextureData.TYPE.getBytes());
   fos.write(TextureData.DATA2);
   fos.write(TextureData.DATA2);
   fos.write(TextureData.DATA3);
   fos.write(TextureData.DATA3);
   fos.write(TextureData.DATA3);
   fos.write(TextureData.DATA3);
   fos.write(TextureData.DATA4);
   fos.write(TextureData.DATA5);
   fos.write(TextureData.TEX.getBytes());
   fos.write(texdata.getBytes());
   fos.write(TextureData.TEX.getBytes());
   } catch(IOException e) {
  //get message
    throw new TextureException(e.getMessage());
   }
   }
 //close the FileOutputStream
 /**
     * Adds the given argument to the method call. The list of arguments will be
     * initialized if it is <code>null</code>.
     * 
     * @param call
     *            method call
     * @param arg
     *            argument value
     */
 public void close() throws TextureException {
  try{
  fos.close();
   } catch(IOException e) {
  throw new TextureException(e.getMessage());
   }
  }
}
