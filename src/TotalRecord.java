public class TotalRecord{
	
    String str;
    int num;
    
    public TotalRecord(String str, int num){
        this.num=num;
        this.str=str;
    }

    public String toString(){
        return String.format("%s : %5d ", str, num);
    }    
}

