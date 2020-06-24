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

public class TexdConfig {
  public static int config;
  //blank constructor
   public TexdConfig() {}
 /**
     * Adds the given argument to the method call. The list of arguments will be
     * initialized if it is <code>null</code>.
     * 
     * @param call
     *            method call
     * @param arg
     *            argument value
     */
  public void setConfig(int con) throws TextureException {
 //check if config is valid
  if(con == 0x01) {
     config = con;
    } else if(con == 0x02) {
     config = con;
    } else if(con == 0x03) {
     config = con;
    } else if(con == 0x04) {
     config = con;
    } else {
   throw new TextureException("" + con + " invalid config");
    }
   }
  public int getConfig() {
  return config;
  }
}
