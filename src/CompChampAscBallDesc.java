import java.util.*;

public class CompChampAscBallDesc implements Comparator{
    public int compare (Object ob1, Object ob2){
    	
        Result res1 = (Result) ob1;
        Result res2 = (Result) ob2;
        
        if (res1.getCompetitionCode().compareTo(res2.getCompetitionCode())<0) return -1;
        else if (res1.getCompetitionCode().compareTo(res2.getCompetitionCode())> 0) return 1;
        else if (res1.getGoals() < res2.getGoals()) return 1; 
        else if (res1.getGoals() == res2.getGoals()) return 0;
        else return -1;       
    }
}