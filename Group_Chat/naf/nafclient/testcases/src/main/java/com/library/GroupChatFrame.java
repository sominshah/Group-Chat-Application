package com.library;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class GroupChatFrame extends JFrame
{
private JButton button;
private Container c;
private JTextArea messageBox;
private JTextField textField;
private JList list;
private DefaultListModel model;
private static String user;
int messageCount;
int userCount;
String message;
GroupChatFrame(String user)
{
super(user);
try
{
GroupChatFrame.user=user;
GroupChat.login(user);
c=getContentPane();
textField=new JTextField(20);
messageBox=new JTextArea();
button=new JButton("Send");
model=new DefaultListModel();
list=new JList(model);
button.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent av)
{
if(textField.getText().length()==0) return;
message="\n"+user+">"+textField.getText();
GroupChat.setMessage(GroupChatFrame.user,message);
}
});
javax.swing.Timer timer1=new javax.swing.Timer(1000,new ActionListener()
{
public void actionPerformed(ActionEvent av)
{
Vector<String> users=GroupChat.getUsers();
list.setListData(users);
list.repaint();
}
});
javax.swing.Timer timer2=new javax.swing.Timer(5000,new ActionListener()
{
public void actionPerformed(ActionEvent av)
{
Vector<String> messages=GroupChat.getMessages(GroupChatFrame.user);
if(messages==null) return;
if(messageCount<messages.size()) 
{
for(int i=messageCount;i<messages.size();i++)
{
message=messages.elementAt(i);
messageBox.append(message);
}
messageCount=messages.size();
}
else return;
}
});
c.setLayout(null);
messageBox.setBounds(10,10,400,250);
list.setBounds(430,10,100,250);
textField.setBounds(10,300,300,40);
button.setBounds(310,300,80,40);
messageBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
list.setBorder(BorderFactory.createLineBorder(Color.BLACK));
messageBox.setLineWrap(true);
messageBox.setFont(new Font("Segoe Script", Font.BOLD, 20));
messageBox.setForeground(Color.BLACK);
messageBox.setEnabled(false);
c.add(messageBox);
c.add(list);
c.add(textField);
c.add(button);
setSize(550,400);
addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent e)
{
GroupChat.logout(GroupChatFrame.user);
System.exit(0);
}
});
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation(d.width/2-275,d.height/2-200);
setVisible(true);
timer2.setInitialDelay(0);
timer1.start();
timer2.start();
}catch(Exception e)
{
System.out.println(e.getMessage());
System.exit(0);
}
}
}

