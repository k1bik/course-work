import javax.swing.table.AbstractTableModel;
import java.util.*;

public class ResultTableModel extends AbstractTableModel{
    List <Result> results;
    public ResultTableModel(List <Result> results) {
        super();
        this.results = results;
    }

    @Override
    public int getRowCount() {
        return results.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    
    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
            return results.get(r).getIdentificationNumber();
            case 1:
            return results.get(r).getCompetitionCode();
            case 2:
            return results.get(r).getDate();
            case 3:
            return results.get(r).getGoals();
            default:
            return "";
        }
    }

    @Override
    public String getColumnName(int c) {
        String name = "";
        switch (c) {
            case 0:
            name = "Номер секции";
            break;
            case 1:
            name = "Шифр соревнования";
            break;
            case 2:
            name = "Дата";
            break;
            case 3:
            name = "Число побед";
            break;
        }
        return name;
    }
}