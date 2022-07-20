/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guios_project;

/**
 *
 * @author Zyad
 */
public class FCFS {
    
    private int NumberOfProcesses;
    private String process[];
    private int BurstTime[];
    private int ArrivalTime[];
    private int FinishingTime[];
    private float TurnAroundTime;
    private float AverageWaitingTime;
    private int Count;
    private int TotalCount;
    private String ss[];
    
    public FCFS(){
        
    }
    public FCFS(int n,String p[], int at[],int bt[]){
        NumberOfProcesses = n;
        FinishingTime = new int[NumberOfProcesses];
        process = new String[NumberOfProcesses];
        ArrivalTime = new int[NumberOfProcesses];
        BurstTime = new int[NumberOfProcesses];

        for(int i = 0 ; i< NumberOfProcesses ;i++){
            process[i] = p[i];
            ArrivalTime[i] = at[i];
            BurstTime[i] = bt[i];
        }
        SortArrivalTime(process,ArrivalTime,BurstTime);
        ss = new String[2];
        ss = ServeProcesses();
    }
    public int getTotalCount(){
        TotalCount =0;
        for(int i = 0; i< NumberOfProcesses;i++){
            TotalCount += BurstTime[i];
        }
        return TotalCount;
    }

    public float CalcAverageWaitingTime(){
        
        AverageWaitingTime = 0;
        for(int i = 0 ; i< NumberOfProcesses;i++){
            AverageWaitingTime+=(FinishingTime[i]-ArrivalTime[i]-BurstTime[i]);
        }
        AverageWaitingTime = AverageWaitingTime / NumberOfProcesses;
        //System.out.println(AverageWaitingTime);
        return AverageWaitingTime;
        
    }
    
    public void CalcTurnArounTime(){
        TurnAroundTime = 0;
        for(int i = 0 ; i< NumberOfProcesses;i++){
            TurnAroundTime += (FinishingTime[i] - ArrivalTime[i]);
            
        }
        TurnAroundTime = TurnAroundTime / NumberOfProcesses;
        System.out.println(TurnAroundTime);
      
    }
    
    public String[] ServeProcesses(){
        
        String[] s = new String[2];
        int TC = getTotalCount();
        String ChartProcess;
        String Charttime;
        ChartProcess = "";
        Count = ArrivalTime[0];
        Charttime = String.valueOf(Count);
        while(TC>0){
            for(int i = 0 ; i<NumberOfProcesses ; i++){
                
               if(ArrivalTime[i]>Count){
                    Count = ArrivalTime[i];
                   // ChartProcess = ChartProcess + "---|" ;
                    if(Count/10<1){
                        Charttime = Charttime + "   " + String.valueOf(Count);}
                    else{
                        Charttime = Charttime + "   " + String.valueOf(Count);
                
                    }
                } 

                Count += BurstTime[i];
                FinishingTime[i]= Count;
                TC = TC - BurstTime[i];
                
                for(int j = 0; j<BurstTime[i]; j++){
                
                ChartProcess += "P" + process[i] + " ";
                }
                
                if(Count/10<1){
                Charttime = Charttime + "      " + String.valueOf(Count);
                }
                else{
                    Charttime = Charttime + "     " + String.valueOf(Count);
                }            
            }
            
        }
            s[0] = ChartProcess;
            s[1] = Charttime;
        
        return s;
        
    }
    
    public void SortArrivalTime(String p[], int at[],int bt[]){
        int temp;
        int temp1;
        String temp2;
        
        for(int i = 0 ; i<NumberOfProcesses-1;i++){
            int smallest = i;
            for(int j = i+1; j<NumberOfProcesses;j++){
                if(ArrivalTime[j]<ArrivalTime[smallest]){
                    smallest = j;
                }
            }
                temp = ArrivalTime[i];
                    ArrivalTime[i] = ArrivalTime[smallest];
                    ArrivalTime[smallest] = temp;
                    temp1 = BurstTime[i];
                    BurstTime[i] = BurstTime[smallest];
                    BurstTime[smallest] = temp1;
                    temp2 = process[i];
                    process[i] = process[smallest];
                    process[smallest] = temp2;
                   
        }
    }
    
    public String[] GanttChart(){
        return ss;
    }

}
