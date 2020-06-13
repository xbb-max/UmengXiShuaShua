package com.ming.xposed.xishuashua.util;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class InternalVariables
{
  protected static final String PS_REGEX = "^\\S+\\s+([0-9]+).*$";
  protected static boolean accessGiven = false;
  protected static String busyboxVersion;
  protected static boolean found;
  protected static String getSpaceFor;
  protected static String inode;
  protected static boolean nativeToolsReady = false;
  protected static Set<String> path;
  public static Pattern psPattern;
  protected static List<String> results;
  protected static String[] space;

  static
  {
    found = false;
    inode = "";
    psPattern = Pattern.compile("^\\S+\\s+([0-9]+).*$");
  }
}