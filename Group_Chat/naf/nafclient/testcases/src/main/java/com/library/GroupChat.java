package com.library;
import com.thinking.machines.nafclient.*;
import com.thinking.machines.nafcommon.*;
import java.util.*;
public class GroupChat
{
public static TMNAFClient client=null;
public static void login(String user)
{
try
{
client=new TMNAFClient("localhost",5000);
GroupChat.client.process("/GroupChat/setUser",user);
}catch(ApplicationException ae)
{
System.out.println(ae.getMessage());
}
}
public static void logout(String user)
{
try
{
GroupChat.client.process("/GroupChat/removeUser",user);
client=null;
}catch(ApplicationException ae)
{
System.out.println(ae);
}
}
public static Vector<String> getUsers()
{
try
{
Vector<String> list=(Vector<String>)GroupChat.client.process("/GroupChat/getUsers");
return list;
}catch(ApplicationException ae)
{
System.out.println(ae);
}
return null;
}
public static void setMessage(String user,String message)
{
try
{
GroupChat.client.process("/GroupChat/setMessage",user,message);
}catch(ApplicationException ae)
{
System.out.println(ae);
}
}
public static Vector<String> getMessages(String user)
{
try
{
return (Vector<String>)GroupChat.client.process("/GroupChat/getMessages",user);
}catch(ApplicationException ae)
{
System.out.println(ae);
}
return null;
}
}

