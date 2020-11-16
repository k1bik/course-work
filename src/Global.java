import java.util.*;

public class Global {
	
    public static ResultGroup table;
    
    public static List <Result> results; 
    
    static ResultTableModel tableModel; 

    public static void updateJTable(List <Result> res){
        results.clear();
        results.addAll(res);    
        tableModel.fireTableDataChanged();  
    }
}
