package com.library;
import java.util.*;
import com.thinking.machines.nafcommon.*;
import com.thinking.machines.nafserver.annotation.*;
import com.thinking.machines.nafserver.model.Application;
@Path("/GroupChat")
public class GroupChat
{
@Path("/setUser")
public void setUser(String user)
{
Application.connect(user);
}
@Path("/removeUser")
public void removeUser(String user)
{
Application.disconnect(user);
}
@Path("/setMessage")
public void setMessage(String user,String message) throws GroupChatException
{
try
{
Application.setAttribute(user,message);
}catch(ApplicationException ae)
{
throw new GroupChatException(ae.getMessage());
}
}
@Path("/getUsers")
public static Vector<String> getUsers() throws GroupChatException
{
try
{
return Application.getUsers();
}catch(ApplicationException ae)
{
throw new GroupChatException(ae.getMessage());
}
}
@Path("/getMessages")
public static Vector<String> getMessages(String user) throws GroupChatException
{
try
{
Vector<Object> v=(Vector<Object>)Application.getAttribute(user);
Vector<String> messages=new Vector<>();
for(Object o:v) messages.add((String)o);
return messages;
}catch(ApplicationException ae)
{
throw new GroupChatException(ae.getMessage());
}
}
}

/*Note:- We have not applied the concept of Session Tracking yet.So we could not use setter/getter.Beacause every time we call a method a new instance of  service class(ServiceA) is formed that means every time a new space is allocated in memory for object variable.So we could not retrieve the previous value that has been set to object variable.*/
