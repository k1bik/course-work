import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class EditPanel extends JPanel {
	
    JTextField tf1;
    JTextField tf2;
    JTextField tf3;
    JTextField tf4;
    JTextField tf5;
    
    public EditPanel(){
        setLayout(new GridLayout(3,3,2,2));
        JButton but1=new JButton ("Добавить");
        JButton but2=new JButton ("Обновить");
        JButton but3=new JButton ("Удалить");
        JButton but4=new JButton ("Удалить группу записей");
        tf1=new JTextField("");
        tf2=new JTextField("");
        tf3=new JTextField("");
        tf4=new JTextField("");
        tf5=new JTextField("");
        JLabel l1=new JLabel("");
        add(tf1); add(tf2); add (tf3); add (tf4); 
        add(but1); add(but2); add(l1);
        add(but3); add(but4); add (tf5);
        
        but1.addActionListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    insert();
                }
            }    
        );
        
        but2.addActionListener(new ActionListener() { 
                public void actionPerformed (ActionEvent e) {
                    update();
                }
            }    
        );
        
        but3.addActionListener(new ActionListener() { 
                public void actionPerformed (ActionEvent e) {
                    delete();
                }
            }    
        );
        
        but4.addActionListener(new ActionListener() { 
                public void actionPerformed (ActionEvent e) {
                    deleteGroup();
                }
            }    
        );
    } 

    private void insert(){
        int m;
        String str1, str2, str3, str4;
        str1=tf1.getText();
        str2=tf2.getText();
        str3=tf3.getText();
        str4=tf4.getText();
        if (str1.equals("")||str2.equals("")||str3.equals("")||str4.equals("")){
            MainFrame.MSG.setText("Задайте значения полей");
            return;
        }
        try{
            m=Integer.parseInt(str4);
        } 
        catch (NumberFormatException  e){
            MainFrame.MSG.setText("Задайте правильно число побед или дату");
            return;
        }
        MainFrame.MSG.setText("Запрос на добавление записи в таблицу");
        if (!Global.table.addResult(new Result(str1,str2,str3,m)))
            MainFrame.MSG.setText("Запись не добавлена, возможно нарушена уникальность ключа");  
        Global.updateJTable(Global.table.getResults());
        tf1.setText("");
        tf2.setText("");
        tf3.setText("");
        tf4.setText("");
    }

    private void update(){
        int m;  
        String str1, str2, str3, str4;
        str1=tf1.getText();
        str2=tf2.getText();
        str3=tf3.getText();  
        str4=tf4.getText();
        if (str1.equals("")||str2.equals("")||str3.equals("")||str4.equals("")){
            MainFrame.MSG.setText("Задайте значения полей");
            return;
        }
        try{
            m=Integer.parseInt(str4);
        }  
        catch (NumberFormatException  e){
            MainFrame.MSG.setText("Задайте правильно число побед или дату");
            return;
        }
        MainFrame.MSG.setText("Запрос на обновление записи в таблице");
        if (!Global.table.updateResultWins(new Result(str1,str2,str3,m)))
            MainFrame.MSG.setText("Запись не обновлена, возможно записи с таким ключом нет");  
        Global.updateJTable(Global.table.getResults());
        tf1.setText("");
        tf2.setText("");
        tf3.setText("");
        tf4.setText("");
    }

    private void  delete(){
        int m;  
        String str1, str2, str3, str4;
        str1=tf1.getText();
        str2=tf2.getText();  
        str3=tf3.getText();   
        str4=tf4.getText(); 
        if (str1.equals("")||str2.equals("")||str3.equals("")||str4.equals("")){
            MainFrame.MSG.setText("Задайте значения полей ключа");
            return;
        }
        try{
            m=Integer.parseInt(str4);
        }
        catch (NumberFormatException  e){
            MainFrame.MSG.setText("Задайте правильно число побед или дату");
            return;
        }
        MainFrame.MSG.setText("Запрос на удаление записи по ключу");
        if (!Global.table.delResult(new Result(str1,str2,str3,m)))
            MainFrame.MSG.setText("Запись не удалена, возможно записи с таким ключом нет");  
        Global.updateJTable(Global.table.getResults());
        tf1.setText("");
        tf2.setText("");
        tf3.setText("");
        tf4.setText("");
    }

    private void deleteGroup(){
        int n;  
        try{
            n=Integer.parseInt(tf5.getText());
        }
        catch (NumberFormatException  e){
            MainFrame.MSG.setText("Задайте правильно число");
            return;
        } 
        if (!Global.table.deleteBellowNumber(n))
            MainFrame.MSG.setText("Записи не удалены, возможно таких записей нет");  
        Global.updateJTable(Global.table.getResults());
        tf5.setText("");
    }
} 