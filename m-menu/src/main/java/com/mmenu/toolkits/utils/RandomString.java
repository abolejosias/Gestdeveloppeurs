/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * cette classe permet de générer une chaine de caractère aléatoire
 */

package com.mmenu.toolkits.utils;

import java.util.Random;

public class RandomString
{

  private static final char[] symbols = new char[36];

  static {
    for (int idx = 0; idx < 10; ++idx)
      symbols[idx] = (char) ('0' + idx);
    for (int idx = 10; idx < 36; ++idx)
      symbols[idx] = (char) ('a' + idx - 10);
  }

  private static final Random random = new Random();

  private  static final char[] buf=new char[16];

  public RandomString()
  {
      
  }
  public static String toNumberic()
{
    String res="";
    for(int i=0;i<buf.length;i++)
    {
        res=res+(int)buf[i];
    }
    return res;
}

  public  static String nextString(int length)
  {

    for (int idx = 0; idx < buf.length; ++idx)
      buf[idx] = symbols[random.nextInt(symbols.length)];
     String res= toNumberic();
     int lth=length>res.length()?res.length()-1:length;
      res=res.substring(0, lth);
      return res;
  }
  public  static String nextString()
  {
    for (int idx = 0; idx < buf.length; ++idx)
      buf[idx] = symbols[random.nextInt(symbols.length)];
    return new String(buf);
  }

public static void main(String[] str)
{
    for(int i=0;i<40;i++)
    {
        RandomString r=new RandomString();
        System.out.println(r.nextString());
    }
}
}
