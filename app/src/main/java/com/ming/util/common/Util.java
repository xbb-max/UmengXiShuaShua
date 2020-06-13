/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ming.util.common;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Vector;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


/**
 *
 * @author soho
 */
public class Util {
	
	public static float getFloat(String string) {
        try {
            return Float.parseFloat(string);
        } catch (Exception e) {
        }
        // TODO Auto-generated method stub
        return 0;
	}
	
    public static byte[] postHttpData(String destUrl, byte[] pdata)
    {
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;
        byte[] data = null;

        try {

            HttpURLConnection.setFollowRedirects(true);
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.setDoOutput(true);
            httpUrl.setDoInput(true);
            
            httpUrl.connect();
            
            OutputStream os = httpUrl.getOutputStream();
            os.write(pdata);
            os.flush();
            os.close();            
            

            bis = new BufferedInputStream(httpUrl.getInputStream());

            ByteArrayOutputStream fos = new ByteArrayOutputStream();

            int length = 0;

            while ((size = bis.read(buf)) != -1) {
            	length += size;
                fos.write(buf, 0, size);
            }
            data = fos.toByteArray();
            fos.close();
            bis.close();

        } catch (Exception e) {
        	e.printStackTrace();
        }
        finally
        {
            if (httpUrl != null) {
                httpUrl.disconnect();
            }
        }

        return data;
    }

 	public static String[] getTagString(String line, String sTag, String eTag)
	{
            Vector<String> vs = new Vector<String>();
            int start = line.indexOf(sTag);
            while(start!=-1)
            {
                int end = line.indexOf(eTag,start);
                vs.addElement(line.substring(start+sTag.length(),end));
                start = line.indexOf(sTag,end);
            }

            String[] ss = new String[vs.size()];
            vs.copyInto(ss);
            return ss;
	}

	public static void debug(String s)
	{
		System.out.println("minglog-"+s);
	}

 	public static String getTagString(String line, int stag, int etag)
	{
		if(!Util.isNull(line))
		{
			int start = line.indexOf(stag);
			int end = line.indexOf(etag);
			return line.substring(start+1, end);
		}
		return line;
	}

 	public static String getTagString(String line)
	{
		if(!Util.isNull(line))
		{
			int start = line.indexOf('<');
			int end = line.indexOf('>');
			return line.substring(start+1, end);
		}
		return line;
	}

	public static int getRandom(int size) {
		Random rand = new Random();
		int which = Math.abs(rand.nextInt()%size);
		return which;
	}
	public static String getRandomStr(String[] letters)
	{
		Random rand = new Random();
		int which = Math.abs(rand.nextInt()%letters.length);
		String s = letters[which];
		return s;
	}

	   public static int getRandomRate(float[] rates)
	   {
	        int which = 0;
	        if(rates!=null&&rates.length>0)
	        {
	            double randomNumber;
	            randomNumber = Math.random();

	            float currentRate = 0.0f;
	            for(int i=0;i<rates.length;i++)
	            {
	                if(randomNumber>=currentRate&&randomNumber<=(currentRate+rates[i]))
	                {
	                    which = (int)(rates[i]*100);
	                    break;
	                }
	                currentRate += rates[i];
	            }
	        }
	        return which;
	   }


	public static String convertString(String s)
	{
		if (s == null)
		{
			return "";
		}
		StringBuffer sb = new StringBuffer();
		s = s.toLowerCase();
		int size = s.length();
		for (int i = 0; i < size; i++)
		{
			char c = s.charAt(i);
			int type = Character.getType(c);
			if (type == Character.LOWERCASE_LETTER
					|| type == Character.UPPERCASE_LETTER
					|| type == Character.DECIMAL_DIGIT_NUMBER)
			{
				sb.append(c);
			} else
			{
//				sb.append('_');
			}
		}

		return sb.toString();
	}

	public static byte[] getZipData(String zippath, String name)
	{
		// System.out.println("zippath:"+zippath);
		// System.out.println("name:"+name);
		byte[] data = null;
		try
		{
			// ZipInputStream jis = new ZipInputStream(new
			// FileInputStream(zippath));
			// ZipEntry jarEntry;
			// while ((jarEntry = jis.getNextEntry()) != null)
			// {
			// System.out.println(jarEntry.getName());
			// }
			// jis.close();

			ZipFile zipFile = new ZipFile(zippath);
			ZipEntry je = zipFile.getEntry(name);// (JarEntry)entries.nextElement();
			InputStream is = null;
			int BUFFER_SIZE = 8 * 1024;
			if (je != null && je.isDirectory() == false)
			{
				is = zipFile.getInputStream(je);
				int size = (int) je.getSize();
				if (size > 0)
				{
					ByteArrayOutputStream fos = new ByteArrayOutputStream();
					byte[] buf = new byte[BUFFER_SIZE];
					while ((size = is.read(buf)) != -1)
					{
						fos.write(buf, 0, size);
					}
					data = fos.toByteArray();
					fos.close();
					is.close();
					buf = null;
				}
			}
			zipFile.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return data;
	}


    private static int BUFFER_SIZE = 8*1024;


    public static byte[] getHttpData(String destUrl, int time, boolean isbreak) {

        HttpURLConnection httpUrl = null;
        URL url = null;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;

        try {
//??-?? ??:??:??.???: INFO/<unknown>(<unknown>): minglog-000---o:libcore.net.http.HttpURLConnectionImpl:http://baidu.com

            HttpURLConnection.setFollowRedirects(true);
            url = new URL(destUrl);
            Object o = url.openConnection();
          Util.debug("---o---"+o);
            httpUrl = (HttpURLConnection) o;
            if(time!=-1)
            {
                httpUrl.setConnectTimeout(time);
                httpUrl.setReadTimeout(time);
            }
            httpUrl.connect();

            InputStream bis = httpUrl.getInputStream();
//            Util.debug("0---"+httpUrl.getContentLength());
//??-?? ??:??:??.???: INFO/<unknown>(<unknown>): minglog-mingsms-getMobilenum----

            ByteArrayOutputStream fos = new ByteArrayOutputStream();

            int length = 0;
//            Util.debug("bis.markSupported():"+bis.markSupported());

            while ((size = bis.read(buf)) != -1) {
            	length += size;
                fos.write(buf, 0, size);
                if(isbreak)
                {
                	break;
                }
            }
            byte[] data = fos.toByteArray();
//            Util.debug("1---"+new String(data));
            fos.close();
//            bis.mark(0);
            bis.close();

//            buf = new byte[BUFFER_SIZE];
//            size = 0;
//            fos = new java.io.ByteArrayOutputStream();
//            while ((size = bis.read(buf)) != -1) {
//            	length += size;
//                fos.write(buf, 0, size);
//                if(isbreak)
//                {
//                	break;
//                }
//            }
//            data = fos.toByteArray();
//            Util.debug("2---"+new String(data));


//            Util.debug("3---"+httpUrl.getContentLength());
//            bis.reset();

            return data;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        finally
        {
            if (httpUrl != null) {
                httpUrl.disconnect();
            }
        }

        return null;
    }
    public static byte[] getHttpData(String destUrl, boolean isbreak) {

        HttpURLConnection httpUrl = null;
        URL url = null;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;

        try {
//??-?? ??:??:??.???: INFO/<unknown>(<unknown>): minglog-000---o:libcore.net.http.HttpURLConnectionImpl:http://baidu.com

            HttpURLConnection.setFollowRedirects(true);
            url = new URL(destUrl);
            Object o = url.openConnection();
            httpUrl = (HttpURLConnection) o;
            httpUrl.setConnectTimeout(5*1000);
            httpUrl.setReadTimeout(5*1000);
            httpUrl.connect();

            InputStream bis = httpUrl.getInputStream();
//            Util.debug("0---"+httpUrl.getContentLength());

            ByteArrayOutputStream fos = new ByteArrayOutputStream();

            int length = 0;
//            Util.debug("bis.markSupported():"+bis.markSupported());

            while ((size = bis.read(buf)) != -1) {
            	length += size;
                fos.write(buf, 0, size);
                if(isbreak)
                {
                	break;
                }
            }
            byte[] data = fos.toByteArray();
//            Util.debug("1---"+new String(data));
            fos.close();
//            bis.mark(0);
            bis.close();

//            buf = new byte[BUFFER_SIZE];
//            size = 0;
//            fos = new java.io.ByteArrayOutputStream();
//            while ((size = bis.read(buf)) != -1) {
//            	length += size;
//                fos.write(buf, 0, size);
//                if(isbreak)
//                {
//                	break;
//                }
//            }
//            data = fos.toByteArray();
//            Util.debug("2---"+new String(data));


//            Util.debug("3---"+httpUrl.getContentLength());
//            bis.reset();

            return data;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        finally
        {
            if (httpUrl != null) {
                httpUrl.disconnect();
            }
        }

        return null;
    }

    public static void copyFile(String oldPath, String newPath) {
        try {
            File file = new File(newPath);
            file = file.getParentFile();
            if(file!=null)
            {
                if(!file.exists())
                {
                    file.mkdirs();
                }
            }
            FileInputStream input = new FileInputStream(oldPath);
            FileOutputStream output = new FileOutputStream(newPath);
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = input.read(b)) != -1) {
                output.write(b, 0, len);
            }
            output.flush();
            output.close();
            input.close();
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public static String getInstallPath() {
        try {
            File fi = new File(".");
            String path = fi.getAbsolutePath();
            path = path.substring(0, path.length() - 2);
            return path;

        } catch (Exception e) {
        }
        return null;
    }

    public static byte[] getUrlData(String s, byte[] data) {
        try {
            Util.debug(s);
            int size = data.length;
            URL url = new URL(s);
            HttpURLConnection url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("POST");
            url_con.setDoOutput(true);
            DataOutputStream dos = new DataOutputStream(url_con.getOutputStream());
            for (int i = 0; i < size; i++) {
                dos.write(data[i]);
            }
            dos.flush();
            dos.close();
            InputStream is = url_con.getInputStream();
            byte[] bs = Util.read(is);
            is.close();
            return bs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] read(InputStream is) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int c = 0;
            while (c != -1) {
                c = is.read();
                bos.write(c);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String writeData(DataOutputStream dos, byte[] data) {
        try {
            int size = data.length;
            dos.writeInt(data.length);
            dos.write(data, 0, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String readString(DataInputStream dis) {
        try {
            int size = dis.readInt();
            if (size == 0) {
                return Const.STRING;
            }

            byte[] data = new byte[size];
            dis.read(data, 0, size);
            data = Gzip.inflate(data);
            String s = new String(data, Const.UTF_8);
            data = null;
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean writeString(DataOutputStream dos, String s) {
        try {
            if (s == null) {
                dos.writeInt(0);
                return true;
            }


            byte[] data = s.getBytes(Const.UTF_8);
            int size = data.length;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            GZIPOutputStream gz = new GZIPOutputStream(baos);
            gz.write(data, 0, data.length);
            gz.finish();
            byte[] data_ = baos.toByteArray();
            if (data_.length > data.length) {
                dos.writeInt(data.length);
                dos.write(data, 0, data.length);
            } else {
                dos.writeInt(data_.length);
                dos.write(data_, 0, data_.length);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String readFileSdcard(String fileName) {
        String res = "";
        try {
            FileInputStream fin = new FileInputStream(fileName);
            int length = fin.available();
            byte[] buffer = new byte[length];
            fin.read(buffer);
            res = new String (buffer, "UTF-8");
            fin.close();

        } catch (Exception e) {
            e.printStackTrace();
            Util.debug("readFileSdcard Exception "+e.getMessage());
        }
        return res;
    }

    public static byte[] readFileData(String name) {
        try {
            FileInputStream file = new FileInputStream(name);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            byte[] b = new byte[1024 * 20];
            int len;
            while ((len = file.read(b)) != -1) {
                bos.write(b, 0, len);
            }

            byte[] bs = bos.toByteArray();
            bos.close();
            file.close();
            return bs;
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return null;
    }

    public static int getInt(String attr) {
        try {
            return Integer.parseInt(attr);
        } catch (Exception e) {
        }
        // TODO Auto-generated method stub
        return 0;
    }

	public static boolean getBoolean(String string) {
        try {
            return Boolean.parseBoolean(string);
        } catch (Exception e) {
        }
        // TODO Auto-generated method stub
		return false;
	}

    public static long getLong(String attr) {
        try {
            return Long.parseLong(attr);
        } catch (Exception e) {
        }
        // TODO Auto-generated method stub
        return 0;
    }




    public static String[] getDevilString(String s, char s1) {
        String[] back = null;
        try {
            int i = 0;
            int j = 0;
            Vector<String> stringbuffer = new Vector<String>();
            while ((j = s.indexOf(s1, i)) >= 0) {
                stringbuffer.addElement(s.substring(i, j));
                i = j + 1;
            }
            if (i < s.length()) {
                stringbuffer.addElement(s.substring(i, s.length()));
            }

            back = new String[stringbuffer.size()];
            stringbuffer.copyInto(back);
            stringbuffer = null;
        } catch (Exception e) {
        }
        return back;
    }

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath);
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            boolean b = myFilePath.delete();
        	Util.debug("delFolder---"+folderPath+"---"+b);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void delFile(String folderPath) {
        try {
            File file = new File(folderPath);
            boolean b = file.delete();
        	Util.debug("delFile---"+folderPath+"---"+b);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        if(tempList!=null)
        {
            File temp = null;
            for (int i = 0; i < tempList.length; i++) {
                if (path.endsWith(File.separator)) {
                    temp = new File(path + tempList[i]);
                } else {
                    temp = new File(path + File.separator + tempList[i]);
                }
                if (temp.isFile()) {
                    boolean b = temp.delete();
                	Util.debug("delFile---"+temp.getPath()+"---"+b);
                }
                if (temp.isDirectory()) {
                    delAllFile(path + "/" + tempList[i]);
                    delFolder(path + "/" + tempList[i]);
                }
            }
        }
    }

    public static boolean writeFileData(String paramString, byte[] paramArrayOfByte)
    {
      try
      {
        File localFile = new File(paramString);
        if (!(localFile.getParentFile().exists()))
        {
            localFile.getParentFile().mkdirs();
        }
        FileOutputStream localFileOutputStream = new FileOutputStream(paramString);
        localFileOutputStream.write(paramArrayOfByte);
        localFileOutputStream.close();
        return true;
      }
      catch (Exception localException)
      {
          localException.printStackTrace();
      }
      return false;
    }

    public static boolean writeFileData(String paramString, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      try
      {
        File localFile = new File(paramString);
        if (!(localFile.getParentFile().exists()))
          localFile.getParentFile().mkdirs();
        FileOutputStream localFileOutputStream = new FileOutputStream(paramString);
        localFileOutputStream.write(paramArrayOfByte, paramInt1, paramInt2);
        localFileOutputStream.close();
        return true;
      }
      catch (Exception localException)
      {
          localException.printStackTrace();
      }
      return false;
    }



    public static String replaceAll(String s, String s1, String s2, boolean ignoreCase) {
        String tmp = "";
        if (s == null || s1 == null || s2 == null) {
            return s;
        }
        int j;
        if (ignoreCase) {
            s1 = s1.toLowerCase();
            int i;
            while ((i = s.toLowerCase().indexOf(s1)) != -1) {
                String s3 = s.substring(0, i);
                String s5 = s.substring(i + s1.length());
                tmp = tmp + s3 + s2;
                s = s5;
            }
        } else {
            while ((j = s.indexOf(s1)) != -1) {
                String s4 = s.substring(0, j);
                String s6 = s.substring(j + s1.length());
                tmp = tmp + s4 + s2;
                s = s6;
            }
        }
        return tmp + s;
    }

    public static void copyFolder(String oldPath, String newPath) {
        try {
            File a = new File(oldPath);
            if (a.isFile()) {
                File f = new File(newPath);
                if (!f.exists()) {
                    f.mkdirs();
                }


                FileInputStream input = new FileInputStream(a);
                File b_ = new File(newPath + "/"
                        + (a.getName()).toString());
                if (!b_.exists()) {
                    b_.createNewFile();
                }
                FileOutputStream output = new FileOutputStream(newPath + "/"
                        + (a.getName()).toString());
                byte[] b = new byte[1024 * 5];
                int len;
                while ((len = input.read(b)) != -1) {
                    output.write(b, 0, len);
                }
                output.flush();
                output.close();
                input.close();
            } else {
                (new File(newPath)).mkdirs();
                String[] file = a.list();
                File temp = null;
                for (int i = 0; i < file.length; i++) {
                    if (oldPath.endsWith(File.separator)) {
                        temp = new File(oldPath + file[i]);
                    } else {
                        temp = new File(oldPath + File.separator + file[i]);
                    }

                    if (temp.isFile()) {
                        FileInputStream input = new FileInputStream(temp);
                        FileOutputStream output = new FileOutputStream(newPath + "/"
                                + (temp.getName()).toString());
                        byte[] b = new byte[1024 * 5];
                        int len;
                        while ((len = input.read(b)) != -1) {
                            output.write(b, 0, len);
                        }
                        output.flush();
                        output.close();
                        input.close();
                    }
                    if (temp.isDirectory()) {
                        copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                    }
                }
            }
        } catch (Exception e) {
         //   e.printStackTrace();
        }
    }

    public static boolean isNull(String css) {
        if (css == null || css.equals(Const.STRING) || css.length() == 0) {
            return true;
        }
        return false;
    }

    public static String unicodeToString(String str) {
        return str;
    }

    public static byte[] inputStreamToBytes(InputStream bis)
    {
      try
      {

              ByteArrayOutputStream fos = new ByteArrayOutputStream();

              int size = 0;
        byte[] buf = new byte[BUFFER_SIZE];

              
              while ((size = bis.read(buf)) != -1) {
                  fos.write(buf, 0, size);
              }
              byte[] data = fos.toByteArray();
              fos.close();
              bis.close();
          

          return data;
      }
      catch (Exception localException)
      {
      }
      return null;
    }




}
