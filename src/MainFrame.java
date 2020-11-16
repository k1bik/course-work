import java.io.*;
import java.awt.*;  
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

public class MainFrame implements ActionListener{
    String directoryName ="C:/";
    String fileName="";
    File curFile; 
    static JFrame frame;
    JPanel pMain; 
    JTable VIS_TABLE;
    EditPanel editPanel; 
    ViewPanel viewPanel; 
    JLabel jFileName; 
    static JLabel MSG;
    java.util.List <String> LINES;
    static String  helpArr1="\n     Систему разработал студент группы ИВТ/б-19-2-о\n "+
        "    Биллер Кирилл Александрович:\n"+               
        "     СевГУ - 2020.\n";

    static String  helpArr2="\n     Информационная система осуществляет хранение и\n"+
        "     обработку данных о результативности команд\n"+
        "     в соревнованиях.\n" ;             

    public   MainFrame(){
        Global.table = new ResultGroup("Список записей о соревнованиях");
        Global.results = new ArrayList <Result>();
        Global.tableModel = new ResultTableModel(Global.results);
        VIS_TABLE = new JTable(Global.tableModel);
        JScrollPane scrtable = new JScrollPane(VIS_TABLE);
        VIS_TABLE.setPreferredScrollableViewportSize(
            new Dimension(250, 100));
        viewPanel = new ViewPanel();
        editPanel = new EditPanel();
        int WinSizeG=600;
        int WinSizeV=500;
        frame = new JFrame("Система хранения и обработки данных");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container myC = frame.getContentPane();
        myC.setLayout(new BorderLayout(5,5));
        //полосы прокрутки
        MenuIS s = new MenuIS();
        frame.setJMenuBar(s.mb1);
        s.newFile.addActionListener(this);
        s.openFile.addActionListener(this);
        s.saveFile.addActionListener(this);
        s.saveAsFile.addActionListener(this);
        s.closeFile.addActionListener(this);
        s.startEdit.addActionListener(this);
        s.stopEdit.addActionListener(this);
        s.help1.addActionListener(this);
        s.help2.addActionListener(this);
        pMain = new JPanel(); 
        pMain.setLayout(new BorderLayout());
        pMain.add(scrtable,BorderLayout.CENTER); 
        pMain.add(editPanel,BorderLayout.SOUTH);
        jFileName = new JLabel("Без имени", JLabel.CENTER);
        pMain.add(jFileName,BorderLayout.NORTH);
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED; 
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        JScrollPane spMain=new JScrollPane(pMain,v,h);
        myC.add(spMain,BorderLayout.CENTER);
        myC.add(new JLabel("Результаты соревнований", JLabel.CENTER), BorderLayout.NORTH);
        MSG = new JLabel("Курсовой проект по дисциплине \"Программирование\". СевГУ - 2020");
        MSG.setFont(new Font("Serif", Font.BOLD,14));
        myC.add(MSG,BorderLayout.SOUTH);
        frame.setSize(WinSizeG,WinSizeV);
        frame.setLocation(10,10);
        frame.setVisible(true);
    }

    public void NewFile(){
        Global.table.getResults().clear();
        Global.results.clear();
        Global.tableModel.fireTableDataChanged();
        pMain.remove(viewPanel);
        pMain.add(editPanel,BorderLayout.SOUTH);
        MSG.setText("Создание базы данных");
    }

    public void setFileFilter(JFileChooser fch){
        TextFilter text_filter=new TextFilter();
        fch.setFileFilter(text_filter);
    }

    public void OpenFile(){
        int rez; int n;
        MSG.setText("Открытие файла"); 
        JFileChooser fch=new JFileChooser(directoryName);
        fch.setDialogTitle("Открытие файла");
        setFileFilter(fch);  
        rez=fch.showDialog(frame,"Open");
        if (rez==fch.APPROVE_OPTION){
            curFile=fch.getSelectedFile();
            fileName=curFile.getAbsolutePath();
            n=fileName.lastIndexOf('\\');
            directoryName=fileName.substring(0,n+1);
            try{
                LINES = IO.inpLines(fileName); 
                if (LINES!=null) MSG.setText("Успешный ввод данных");
                else MSG.setText("Ошибка ввода данных");
            }catch (Exception e) {MSG.setText("Ошибка ввода данных");}
            finally{}
            java.util.List <Result> res = Transfer.StringsToResults(LINES);
            Global.table.getResults().clear();
            for (Result r: res) Global.table.addResult(r);
            Global.updateJTable(Global.table.getResults());
            pMain.remove(editPanel);
            pMain.add(viewPanel,BorderLayout.SOUTH);
            jFileName.setText(fileName);
        }
    }

    private void SaveDialog(){
        int rez; int n;
        JFileChooser fch=new JFileChooser(directoryName);
        fch.setDialogTitle("Сохранение файла");
        setFileFilter(fch); 
        rez=fch.showDialog((Component)frame,"Save");
        if (rez==fch.APPROVE_OPTION){
            curFile=fch.getSelectedFile();
            fileName=curFile.getAbsolutePath();
            n=fileName.lastIndexOf('\\');
            directoryName=fileName.substring(0,n+1);
        }
    } 

    public void SaveFile(boolean fs){
        String old_file_name=fileName;
        MSG.setText("Сохранение файла"); 
        if (fs) SaveDialog(); 
        else  if (fileName=="") SaveDialog();
        if  (curFile==null){MSG.setText("Файл для сохранения не выбран"); return;}
        if ((!curFile.exists())|| fileName.equals(old_file_name)){
            LINES = Transfer.ResultsToString(Global.table.getResults());   
            try{
                boolean f=IO.outpLines(fileName, LINES); 
                if (f)  {MSG.setText("Данные успешно сохранены");
                    jFileName.setText(fileName);
                }
                else   MSG.setText("Ошибка сохранения данных");
            }
            catch (Exception e) {
                MSG.setText("Ошибка сохранения данных");
            }
            finally{}
        }
        else {JOptionPane.showMessageDialog(frame,"Ошибка: файл с заданным именем "+fileName+" существует");
            fileName=old_file_name;
        }
        MSG.setText("Курсовой проект по дисциплине \"Программирование\". СевГУ - 2020"); 
    }

    public void CloseWindow(){
        frame.dispose();
    }

    public void StartEdit(){
        pMain.remove(viewPanel);
        pMain.add(editPanel,BorderLayout.SOUTH); 
        MSG.setText("Редактирование базы данных"); 
    }

    public void StopEdit(){
        pMain.remove(editPanel);
        pMain.add(viewPanel,BorderLayout.SOUTH);
        MSG.setText("Просмотр базы данных");
    }

    public void actionPerformed (ActionEvent e){
        if("Новый".equals(e.getActionCommand()))NewFile(); 
        else if("Открыть".equals(e.getActionCommand()))OpenFile();
        else if ("Сохранить".equals(e.getActionCommand()))SaveFile(false); 
        else  if ("Сохранить как".equals(e.getActionCommand()))SaveFile(true); 
        else  if ("Закрыть".equals(e.getActionCommand()))CloseWindow();
        else  if ("Начать редактирование".equals(e.getActionCommand()))StartEdit();
        else  if ("Закончить редактирование".equals(e.getActionCommand())) StopEdit();
        else  if ("О программе".equals(e.getActionCommand())){ 
            HelpDialog helpMSG = new HelpDialog(MainFrame.frame,"О программе",helpArr1, "динозавр.gif");
            helpMSG.setVisible(true);
        }
        else  if ("Описание ИС".equals(e.getActionCommand())){ 
            HelpDialog helpMSG1=new HelpDialog(MainFrame.frame,"Описание информационной системы",
                    helpArr2, "лошадь.gif");
            helpMSG1.setVisible(true);
        }
    }
}