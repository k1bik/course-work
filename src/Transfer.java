import java.util.*;

public class Transfer {
    public static List <Result> StringsToResults(List <String> lines){
        if (lines == null || lines.isEmpty ()) return null;
        List <Result> results = new ArrayList <Result> ();
        for (int i = 0; i < lines.size(); i++){
            String [ ] words = lines.get(i).split(","); 
            if (words.length != 4) return null;
            Result r = new Result();
            r.setIdentificationNumber(words[0].trim());
            r.setCompetitionCode(words[1].trim());
            r.setDate(words[2].trim());
            try{ 
                r.setGoals(Integer.parseInt(words[3].trim()));
            }catch (NumberFormatException  e){
                return null;
            }
            results.add(r); 
        }    
        return results;  
    } 

    public static List <String> ResultsToString (List <Result> results) {
        if (results==null || results.isEmpty ()) return null;
        List <String> lines = new ArrayList <String> ();
        for (Result res: results)      
            lines.add(String.format("%10s, %10s, %10s, %5d", res.getIdentificationNumber(), res.getCompetitionCode(),
            res.getDate(),res.getGoals()));
        return lines;
    }     
}
