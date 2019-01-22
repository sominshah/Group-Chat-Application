package com.library;
import com.thinking.machines.nafclient.*;
import java.util.*;
public class Main
{
public static void main(String gg[])
{
System.out.println("Username:");
Scanner s=new Scanner(System.in);
String user=s.nextLine();
GroupChatFrame gp=new GroupChatFrame(user);
}
}
