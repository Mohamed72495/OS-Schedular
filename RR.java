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
public class RR {
    private static int NumberOfProcesses;
    private String process[];
    private float BurstTime[];
    private int TimeSlice;
    private int ArrivalTime[];
    private int FinishingTime[];
    private float TurnAroundTime;
    private float AverageWaitingTime;
    private int Count;
    private float TotalCount;
    private float tempBurst[];
    private String ss[];
    
    public RR(){
        
    }

    public RR(int n,int ts,String p[], int at[],float bt[]){
        NumberOfProcesses = n;
        TimeSlice = ts;
        process = new String[NumberOfProcesses];
        ArrivalTime = new int[NumberOfProcesses];
        BurstTime = new float[NumberOfProcesses];
        FinishingTime = new int[NumberOfProcesses];
        tempBurst = new float[NumberOfProcesses];
        
        for(int i = 0 ; i< NumberOfProcesses ;i++){
            process[i] = p[i];
            ArrivalTime[i] = at[i];
            BurstTime[i] = bt[i];
            tempBurst[i] = bt[i];
        }
        SortArrivalTime(process,ArrivalTime,BurstTime,tempBurst);
        ss = new String[2];
        ss = ServeProcesses();
        
    }

    public float CalcAverageWaitingTime(){
        
        AverageWaitingTime = 0;
        for(int i = 0 ; i< NumberOfProcesses;i++){
            AverageWaitingTime+=(FinishingTime[i]-ArrivalTime[i]-BurstTime[i]);
        }
        AverageWaitingTime = AverageWaitingTime / NumberOfProcesses;
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
        String ChartProcess = "|";
        String Charttime;
        Count = ArrivalTime[0];
        
        Charttime = String.valueOf(Count);

        float mm = 0;
        float TC = getTotalCount();
        while(TC>0){
            for(int i = 0 ; i<NumberOfProcesses ; i++){
                if (tempBurst[i]==0){
                    continue;}
                if(ArrivalTime[i]<=Count){
                    /*Count = ArrivalTime[i];
                    ChartProcess = ChartProcess + "---|" ;
                    if(Count/10<1){
                        Charttime = Charttime + "   " + String.valueOf(Count);}
                    else{
                        Charttime = Charttime + "   " + String.valueOf(Count);
                
                    }*/
                
                    if (tempBurst[i]<TimeSlice){
                        Count += tempBurst[i];
                        mm = tempBurst[i];
                        tempBurst[i] = 0;
                        FinishingTime[i]= Count;
                        ChartProcess = ChartProcess + "  " + "P" +process[i] + "  |";
                        if(Count/10<1){
                            Charttime = Charttime + "      " + String.valueOf(Count);}
                        else{
                            Charttime = Charttime + "     " + String.valueOf(Count);
                
                        }
                    }
                    else if (tempBurst[i]==TimeSlice){
                        Count += tempBurst[i];
                        mm = tempBurst[i];
                        tempBurst[i] = 0;
                        FinishingTime[i]= Count;
                        ChartProcess = ChartProcess + "  " + "P" +process[i] + "  |";
                        if(Count/10<1){
                            Charttime = Charttime + "      " + String.valueOf(Count);}
                        else{
                            Charttime = Charttime + "     " + String.valueOf(Count);
                
                        }
                    
                    }
                    else if (tempBurst[i]>TimeSlice){
                        Count += TimeSlice;//3
                        tempBurst[i] -= TimeSlice;//1
                        mm = TimeSlice;//1
                        
                        ChartProcess = ChartProcess + "  " + "P" +process[i] + "  |";
                        if(Count/10<1){
                            Charttime = Charttime + "      " + String.valueOf(Count);}
                        else{
                            Charttime = Charttime + "     " + String.valueOf(Count);
                
                        }
                        while(tempBurst[i]!=0&&ArrivalTime[(i+1)%NumberOfProcesses]>Count){
                            Count += TimeSlice;//4
                            tempBurst[i] -= TimeSlice;//0
                            mm += TimeSlice;//2
                            ChartProcess = ChartProcess + "  " + "P" +process[i] + "  |";
                            if(Count/10<1){
                                Charttime = Charttime + "      " + String.valueOf(Count);}
                            else{
                                Charttime = Charttime + "     " + String.valueOf(Count);
                
                            }
                        }
                        FinishingTime[i]= Count;
                    }
                }
                else if(ArrivalTime[i]>Count){
                    Count = ArrivalTime[i];//5
                    
                    ChartProcess = ChartProcess + "---|" ;
                    if(Count/10<1){
                        Charttime = Charttime + "    " + String.valueOf(Count);}
                    else{
                        Charttime = Charttime + "   " + String.valueOf(Count);
                
                    }
                    mm=0;
                    while(tempBurst[i]!=0&&ArrivalTime[(i+1)%NumberOfProcesses]>Count){
                            Count += TimeSlice;//4
                            tempBurst[i] -= TimeSlice;//0
                            mm += TimeSlice;//2
                            ChartProcess = ChartProcess + "  " + "P" +process[i] + "  |";
                            if(Count/10<1){
                                Charttime = Charttime + "       " + String.valueOf(Count);}
                            else{
                                Charttime = Charttime + "      " + String.valueOf(Count);
                
                            }
                        }
                    FinishingTime[i]= Count;
                }
                TC = TC - mm;//3

                }

        }
            s[0] = ChartProcess;
            s[1] = Charttime;
        
        return s;
    }

        //
    
    public float getTotalCount(){
        TotalCount =0;
        for(int i = 0; i< NumberOfProcesses;i++){
            TotalCount += BurstTime[i];
        }
        return TotalCount;
    }
    
    public void SortArrivalTime(String p[], int at[],float bt[],float tbt[]){
        int temp;
        float temp1;
        String temp2;
        float temp3;
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
                temp3 = tempBurst[i];
                tempBurst[i]= tempBurst[smallest];
                tempBurst[smallest]=temp3;
        }
    }
    
    public String[] GanttChart(){
        //String[] ss = new String[2];
        //ss = ServeProcesses();
        return ss;
    }

}
