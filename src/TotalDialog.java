import java.awt.*; 
import javax.swing.*;
import java.awt.event.*;

class TotalDialog extends JDialog implements ActionListener{
    public TotalDialog (JFrame parent, String name, java.util.List <TotalRecord> list){ 
        super (parent, "Подведение итогов", true);
        Container cp=getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add (new JLabel(name, JLabel.CENTER),BorderLayout.NORTH);
        JButton ok= new JButton ("OK");
        ok.addActionListener(this); 
        cp.add(ok, BorderLayout.SOUTH); 
        TotalTableModel tableModel = new TotalTableModel(list,"Секция","Сумма побед");
        JTable jtable = new JTable(tableModel);
        JScrollPane scrtable = new JScrollPane(jtable);
        jtable.setPreferredScrollableViewportSize(
            new Dimension(150, 150));
        add(scrtable,BorderLayout.CENTER);
        MainFrame.MSG.setText("Итоговый запрос на выборку");
        setSize(320,250);
        setLocation(50,100);
    } 

    public void actionPerformed (ActionEvent e){ 
        dispose();
    }    
} 
