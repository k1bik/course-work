import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class MenuIS {
    JMenuItem newFile;
    JMenuItem openFile;
    JMenuItem saveFile;
    JMenuItem saveAsFile;
    JMenuItem closeFile;
    JMenuItem startEdit;
    JMenuItem stopEdit;
    JMenuItem help1;
    JMenuItem help2;

    JMenu m1, m2, m3;
    JMenuBar mb1;

    public MenuIS(){
        m1=new JMenu("����");
        newFile=new JMenuItem("�����");
        m1.add(newFile);

        openFile=new JMenuItem("�������");
        m1.add(openFile);

        saveFile=new JMenuItem("���������");
        m1.add(saveFile);

        saveAsFile=new JMenuItem("��������� ���");
        m1.add(saveAsFile);

        closeFile=new JMenuItem("�������");
        m1.add(closeFile);

        m2=new JMenu("��������������");

        startEdit=new JMenuItem("������ ��������������");
        m2.add(startEdit);

        stopEdit=new JMenuItem("��������� ��������������");
        m2.add(stopEdit);

        m3=new JMenu("�������");

        help1=new JMenuItem("� ���������");
        m3.add(help1);

        help2=new JMenuItem("�������� ��");
        m3.add(help2);

        mb1=new JMenuBar();

        mb1.add(m1);
        mb1.add(m2);
        mb1.add(m3);
    }
}
