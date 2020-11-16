import java.util.*;

public class ResultGroup {
	
    private final static String GROUP_FORMAT_STRING ="Результаты соревнований: %-s, %-5d строк";
    
    private String name; 
    private List <Result> results; 
    
    public ResultGroup() {
        name=""; 
        results = new ArrayList <Result>();
    }

    public ResultGroup(String name){
        this.name=name; 
        results = new ArrayList <Result>();
    }

    public ResultGroup(String name, List list){
        this.name=name; 
        results = new ArrayList <Result>(list); 
    }

    public void setName(String name)  {this.name = name;}
    public void setResults (List <Result> results) {this.results = results;}

    public String getName(){return name;}
    
    public List <Result> getResults(){
        return results;
    }
    
    public String toString(){
        return String.format(GROUP_FORMAT_STRING,name,getResultNum());  
    }
    
    public boolean addResult(Result res){
       
        if (getResult(res)!=null) 
            return false; 
        if (results.add(res)) return true;
        else return false;
    }
   
    public boolean delResult(Result res){
        if (results.remove(res)) return true;
        else return false;
    }
    
    public boolean deleteBellowNumber(int n){ 
        return results.removeAll(bellowNumber(n).results);
    } 
   
    public boolean updateResultWins(Result res){
        Result r = getResult(res);
        if (r!=null) {
            r.setGoals(res.getGoals()); return true;
        }
        return false;
    }
    
    public Result getResult (Result res){
        for (Result r : results)
            if (r.equals(res)) return r; 
        return null; 
    } 

    public int getResultNum(){   
        return results.size();
    }    

    public ResultGroup bellowNumber(int n){ 
        ResultGroup group = new ResultGroup (name+": результаты, в которых число голов выше среднего - ");
        for (Result res : results)
            if (res.getGoals()<n)group.addResult(res);
        return group;
    }

    public ResultGroup filterCompetition(String filter){ 
        ResultGroup group = new ResultGroup (name+": результаты для команд на \"" + filter +"\"");
        for (Result res : results)
            if (res.getCompetitionCode().startsWith(filter))group.addResult(res);
        return group;
    }
   
    public int competitionNumber(){
        int n=results.size();
        if (n==0) return 0;
        Set <String> competition = new TreeSet <String>();
        for (Result res:results)
            competition.add(res.getCompetitionCode()); 
        return competition.size();
    }   	           

    public List <TotalRecord> totalSumCompetitionGoals(){
        int n = results.size();
        if (n == 0) return null;
        List <Result> resultsTemp = new ArrayList <Result> ();
        resultsTemp.addAll(results);
        Set <String> commandsS  = new TreeSet();
        for (Result res:results)
            commandsS.add(res.getIdentificationNumber());
        List <String> commandsL= new ArrayList(commandsS); 
        int m = commandsL.size();
        String com; int sum; 
        int temp = 0;
        List <TotalRecord> totRecList = new ArrayList <TotalRecord>();
        for (int i=0; i<m; i++){
            com = commandsL.get(i);
            sum=0;
            Iterator <Result> iter = resultsTemp.iterator();
            while (iter.hasNext()){
                temp = temp+1; 
                Result res = iter.next(); 
                if (com.equals(res.getIdentificationNumber())) {
                    sum = sum + res.getGoals(); 
                    iter.remove(); 
                }    
            }   
            totRecList.add(new TotalRecord(com, sum));
        }
        return totRecList;     
    }

    public ResultGroup sort(){ 
        ResultGroup group = new ResultGroup (name, results);
        Collections.sort(group.results);
        return group;
    } 

    public ResultGroup sort(Comparator comp){ 
        ResultGroup group = new ResultGroup (name, results);
        Collections.sort(group.results, comp);
        return group;
    }
} 
