public class Result implements Comparable <Result>{

    private final static String REZ_FORMAT_STRING = "%10s | %10s | %10s | %8d |";

    private String identificationNumber; 
    private String competitionCode; 
    private int goals;
    private String date;

    public Result(){
        identificationNumber="";
        competitionCode="";
        date="";
        goals=0;
    }

    public Result(String identificationNumber,String competitionCode, String date, int goals){
        this.identificationNumber=identificationNumber;
        this.competitionCode=competitionCode;
        this.date=date;
        this.goals=goals;
    }

    public String getIdentificationNumber(){return identificationNumber;}
    public String getCompetitionCode(){return competitionCode;}
    public String getDate(){return date;}
    public int getGoals(){return goals;}
 
    public void setIdentificationNumber(String identificationNumber){this.identificationNumber = identificationNumber;}
    public void setCompetitionCode(String competitionCode){this.competitionCode = competitionCode;}
    public void setDate(String date){this.date = date;}
    public void setGoals(int goals){this.goals = goals;}

    @Override
    public String toString(){
        return String.format(REZ_FORMAT_STRING,identificationNumber,competitionCode,date,goals);
    } 
   
    @Override
    public boolean equals (Object ob){ 
        if (ob==this) return true; 
        if (ob==null) return false;
        if (getClass()!=ob.getClass())return false;
        Result rez=(Result)ob;
        return (identificationNumber.equals(rez.identificationNumber) &&
            competitionCode.equals(rez.competitionCode) && date.equals(rez.date));                 
    }

    public int hashCode(){
        return 7*identificationNumber.hashCode()+
        13*competitionCode.hashCode()+
        17*date.hashCode()+
        19*(new Integer(goals)).hashCode();
    }

    public int compareTo(Result rez){
        if (identificationNumber.compareTo(rez.identificationNumber) < 0) return -1;
        if ((identificationNumber.compareTo(rez.identificationNumber) == 0) && (competitionCode.compareTo(rez.competitionCode) < 0)) return -1;
        if ((identificationNumber.compareTo(rez.identificationNumber) == 0) && (competitionCode.compareTo(rez.competitionCode) == 0)&& (date.compareTo(rez.date) < 0)) return -1;
        else return 1;     
    }
}


