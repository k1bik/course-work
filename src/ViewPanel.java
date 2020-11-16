import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ViewPanel extends JPanel implements ActionListener {
    JTextField tf;
    public ViewPanel(){
        setLayout(new GridLayout(7,2,2,2));
        JButton but4=new JButton ("Итог 1:  сумма побед по секциям");
        JButton but5=new JButton ("Итог 2:  общее число соревнований");
        JButton but6=new JButton ("Применить фильтр");
        JButton but7=new JButton ("Сортировать по номеру секции и числу побед");
        JButton but8=new JButton ("Сортировать по соревнованию и количеству побед");
        JButton but9=new JButton ("Вывести все");
        tf=new JTextField("");
        JLabel l1 = new JLabel("Введите фильтр для соревнований",JLabel.CENTER);
        JLabel l2 = new JLabel("");
        but4.setActionCommand("Total1");
        but5.setActionCommand("Total2");
        but6.setActionCommand("Filter");
        but7.setActionCommand("Sort1");
        but8.setActionCommand("Sort2");
        but9.setActionCommand("All");
        JPanel p1 = new JPanel();
        add(l1); add(l2); 
        add(tf); add(but6); 
        add(but9);
        add(p1);
        add(but7); add(but8); 
        add(but4); add(but5);

        but4.addActionListener(this);
        but5.addActionListener(this);
        but6.addActionListener(this);
        but7.addActionListener(this);
        but8.addActionListener(this);
        but9.addActionListener(this);
    }

    private void showTotal1(){
        TotalDialog td = new TotalDialog (MainFrame.frame,"Суммарное число побед для каждой секции:",
                Global.table.totalSumCompetitionGoals());
        td.setVisible(true); 
    }

    private void showTotal2(){
        MainFrame.MSG.setText("Итоговый запрос на выборку");
        JOptionPane.showMessageDialog(MainFrame.frame,
            String.format("Общее число соревнований: %5d", Global.table.competitionNumber()));
    }

    private void showFilter(){
        String filter = tf.getText();
        if (filter.equals("")) {
            MainFrame.MSG.setText("Введите фильтр");
            return;
        }  
        MainFrame.MSG.setText(String.format(
                "   Запрос на выборку: выдать записи с секциями, начинающейся с \"%s\"", filter));
        Global.updateJTable(Global.table.filterCompetition(filter).getResults());
        tf.setText("");
    }

    private void showSort1(){
        MainFrame.MSG.setText("Запрос на выборку: выдать все записи таблицы с сортировкой по соревнованиям и количеству побед");
        Global.updateJTable(Global.table.sort(new CompCommandAscBallDesc ()).getResults());    
    }

    private void showSort2(){
        MainFrame.MSG.setText("Запрос на выборку: выдать все записи таблицы с сортировкой по номеру секции и числу побед");
        Global.updateJTable(Global.table.sort(new CompChampAscBallDesc ()).getResults());
    }

    private void showAll(){
        MainFrame.MSG.setText("Запрос на выборку: выдать все записи таблицы без сортировки");
        Global.updateJTable(Global.table.getResults());
    }

    public void actionPerformed (ActionEvent e){
        if("Total1".equals(e.getActionCommand())) showTotal1();
        else if("Total2".equals(e.getActionCommand())) showTotal2();
        else if("Filter".equals(e.getActionCommand())) showFilter();
        else if("Sort1".equals(e.getActionCommand())) showSort1();
        else if("Sort2".equals(e.getActionCommand())) showSort2();
        else showAll();
    }     
} 