import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ViewPanel extends JPanel implements ActionListener {
    JTextField tf;
    public ViewPanel(){
        setLayout(new GridLayout(7,2,2,2));
        JButton but4=new JButton ("���� 1:  ����� ����� �� �������");
        JButton but5=new JButton ("���� 2:  ����� ����� ������������");
        JButton but6=new JButton ("��������� ������");
        JButton but7=new JButton ("����������� �� ������ ������ � ����� �����");
        JButton but8=new JButton ("����������� �� ������������ � ���������� �����");
        JButton but9=new JButton ("������� ���");
        tf=new JTextField("");
        JLabel l1 = new JLabel("������� ������ ��� ������������",JLabel.CENTER);
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
        TotalDialog td = new TotalDialog (MainFrame.frame,"��������� ����� ����� ��� ������ ������:",
                Global.table.totalSumCompetitionGoals());
        td.setVisible(true); 
    }

    private void showTotal2(){
        MainFrame.MSG.setText("�������� ������ �� �������");
        JOptionPane.showMessageDialog(MainFrame.frame,
            String.format("����� ����� ������������: %5d", Global.table.competitionNumber()));
    }

    private void showFilter(){
        String filter = tf.getText();
        if (filter.equals("")) {
            MainFrame.MSG.setText("������� ������");
            return;
        }  
        MainFrame.MSG.setText(String.format(
                "   ������ �� �������: ������ ������ � ��������, ������������ � \"%s\"", filter));
        Global.updateJTable(Global.table.filterCompetition(filter).getResults());
        tf.setText("");
    }

    private void showSort1(){
        MainFrame.MSG.setText("������ �� �������: ������ ��� ������ ������� � ����������� �� ������������� � ���������� �����");
        Global.updateJTable(Global.table.sort(new CompCommandAscBallDesc ()).getResults());    
    }

    private void showSort2(){
        MainFrame.MSG.setText("������ �� �������: ������ ��� ������ ������� � ����������� �� ������ ������ � ����� �����");
        Global.updateJTable(Global.table.sort(new CompChampAscBallDesc ()).getResults());
    }

    private void showAll(){
        MainFrame.MSG.setText("������ �� �������: ������ ��� ������ ������� ��� ����������");
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