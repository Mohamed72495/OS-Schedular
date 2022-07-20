/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guios_project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GuiOSController implements Initializable {

    @FXML
    private TextField id_field;
    @FXML
    private TextField arrivalTime;
    @FXML
    private Label priorityLabel;
    @FXML
    private Button FCFSbtn;
    @FXML
    private Button SJFPrebtn;
    @FXML
    private Button SJFnonbtn;
    @FXML
    private Button Pri_pre;
    @FXML
    private Button non_Pri_pre;
    @FXML
    private Button RRbtn;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField burstTime;
    @FXML
    private TextField PriorityField;
    @FXML
    private Button EnterData;
    @FXML
    private TextField processNo;
    @FXML
    private Label QuantamLabel;
    @FXML
    private TextField Quantam;
    public static int y=0;
    public static int z=0;
    public static int k=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        PriorityField.setVisible(false);
        priorityLabel.setVisible(false);
        QuantamLabel.setVisible(false);    
        Quantam.setVisible(false);
    }    

    @FXML
    private void ID_Action(ActionEvent event) {
    }

    @FXML
    private void ArrivalAction(ActionEvent event) {
    }

    @FXML
    private void FCFSAction(ActionEvent event) {
        if(event.getSource() == FCFSbtn){
        boolean r = checkValidity();
        if(!r) return;
        if(checkLength()){
            Alert a = new Alert(AlertType.ERROR);  
            a.setContentText("Number of processes and their data should be consistent");
            a.show();
        }
        else{
        String[] pID = format(id_field.getText().split(" "));
        String[] pAT = format(arrivalTime.getText().split(" "));
        String[] pBT = format(burstTime.getText().split(" "));
        int at[] = new int[pID.length];
        int bt[] = new int[pID.length];
        for(int i=0; i< pID.length; i++){
            at[i] = Integer.parseInt(pAT[i]);
            bt[i] = Integer.parseInt(pBT[i]);
        }
        FCFS fs = new FCFS(pID.length,pID,at,bt);
        String[] s = fs.GanttChart();
        String finalChart = drawGantt(s[0]);
        textArea.appendText("FCFS chart:\n");
        textArea.appendText(finalChart);
        textArea.appendText("\n");
        textArea.appendText("Average Waiting time: " + String.valueOf(fs.CalcAverageWaitingTime()));
        textArea.appendText("\n");
        textArea.appendText("\n");
        }
    }
    }

    @FXML
    private void SJFpreAction(ActionEvent event) {
        if(event.getSource() == SJFPrebtn){
        boolean r = checkValidity();
        if(!r) return;
        if(checkLength()){
            Alert a = new Alert(AlertType.ERROR);  
            a.setContentText("Number of processes and their data should be consistent");
            a.show();
        }
        else{
        String[] ppID = format(id_field.getText().split(" "));     
        String[] pAT = format(arrivalTime.getText().split(" "));
        String[] pBT = format(burstTime.getText().split(" "));
        int len=ppID.length;
        //textArea.setText("");
        String[] s = SJFpreemptive(ppID.length,ppID,pBT,pAT); 
        String finalChart = drawGantt(s[0]);
        textArea.appendText("Preemptive SJF chart:\n");
        textArea.appendText(finalChart);
        textArea.appendText("\n");
        textArea.appendText("Average Waiting Time: " + s[1]);
        textArea.appendText("\n");
        textArea.appendText("\n");
        }
        }
    }

    @FXML
    private void SJFnonAction(ActionEvent event) {
        if(event.getSource() == SJFnonbtn){
        boolean r = checkValidity();
        if(!r) return;
        if(checkLength()){
            Alert a = new Alert(AlertType.ERROR);  
            a.setContentText("Number of processes and their data should be consistent");
            a.show();
        }
        else{
        String[] ppID = format(id_field.getText().split(" "));     
        String[] pAT = format(arrivalTime.getText().split(" "));
        String[] pBT = format(burstTime.getText().split(" "));
        int len=ppID.length;
        //textArea.setText("");
        String[] s = SJFnonpreemptive(ppID.length,ppID,pBT,pAT); 
        String finalChart = drawGantt(s[0]);
        textArea.appendText("Non-Preemptive SJF chart:\n");
        textArea.appendText(finalChart);
        textArea.appendText("\n");
        textArea.appendText("Average Waiting Time: " + s[1]);
        textArea.appendText("\n");
        textArea.appendText("\n");        
        }
        }
    }
    
   

    @FXML
    private void pri_pre_schedular(ActionEvent event) {
        if(event.getSource() == Pri_pre && y==0 ){
        PriorityField.setVisible(true);
        priorityLabel.setVisible(true);
        Alert x = new Alert(AlertType.INFORMATION); 
        x.setContentText("Enter Priority ");
        x.show(); 
        y++;
        z++;
        }  
        else if(event.getSource() == Pri_pre && y!=0 ){  
        boolean rr = checkValidity();
        if(!rr) return; 
        String pPR = PriorityField.getText();
        String[] PR = format(pPR.split(" "));
        String[] ID = format(id_field.getText().split(" "));     
        if(inputError(pPR)){
            Alert a = new Alert(AlertType.ERROR);  
            a.setContentText("Priority field cannot be empty or have characters");
            a.show(); }
        else if(checkLength() || ID.length != PR.length){
            Alert a = new Alert(AlertType.ERROR);  
            a.setContentText("Number of processes and their data should be consistent");
            a.show();
        }
        else{
        String[] pID = format(id_field.getText().split(" "));
        String[] pAT = format(arrivalTime.getText().split(" "));
        String[] pBT = format(burstTime.getText().split(" "));
        String[] ppPR = format(PriorityField.getText().split(" "));
        String[] s = Prem_Pir(pID.length,pID,pBT,pAT,ppPR); 
        String finalChart = drawGantt(s[0]);
        textArea.appendText("Preemptive Priority chart:\n");
        textArea.appendText(finalChart);
        textArea.appendText("\n");
        textArea.appendText("Average Waiting Time: " + s[1]);
        textArea.appendText("\n");
        textArea.appendText("\n");
        }
        }
    }
        
    
   

    @FXML
    private void pri_non_pre_schedular(ActionEvent event) {
        if(event.getSource() == non_Pri_pre && z==0 ){
        PriorityField.setVisible(true);
        priorityLabel.setVisible(true);
        Alert x = new Alert(AlertType.INFORMATION); 
        x.setContentText("Enter Priority ");
        x.show(); 
        z++;
        y++;
        }  
        else if(event.getSource() == non_Pri_pre && z!=0 ){  
        boolean rr = checkValidity();
        if(!rr) return;  
        String pPR = PriorityField.getText();
        String[] PR = format(pPR.split(" "));
        String[] ID = format(id_field.getText().split(" "));     
        if(inputError(pPR)){
            Alert a = new Alert(AlertType.ERROR);  
            a.setContentText("Priority field cannot be empty or have characters");
            a.show(); }
        else if(checkLength() || ID.length != PR.length){
            Alert a = new Alert(AlertType.ERROR);  
            a.setContentText("Number of processes and their data should be consistent");
            a.show();
        }
        else{
        String[] pID = format(id_field.getText().split(" "));
        String[] pAT = format(arrivalTime.getText().split(" "));
        String[] pBT = format(burstTime.getText().split(" "));
        String[] ppPR = format(PriorityField.getText().split(" "));
        String[] s = nonPrePri(pID.length,pID,pAT,pBT,ppPR); 
        String finalChart = drawGantt(s[0]);
        textArea.appendText("Non-Preemptive Priority chart:\n");
        textArea.appendText(finalChart);
        textArea.appendText("\n");
        textArea.appendText("Average Waiting Time: " + s[1]);
        textArea.appendText("\n");
        textArea.appendText("\n");
        }
 
        }    
    }

    @FXML
    private void RRAction(ActionEvent event) {
        if(event.getSource() == RRbtn && k == 0){
        Alert x = new Alert(AlertType.INFORMATION); 
        x.setContentText("Enter CPU Quantam ");
        x.show(); 
        QuantamLabel.setVisible(true);    
        Quantam.setVisible(true);
        k++;
        }
        else if(event.getSource() == RRbtn && k != 0){
        boolean rr = checkValidity();
        if(!rr) return;
        if(checkLength()){
            Alert a = new Alert(AlertType.ERROR);  
            a.setContentText("Number of processes and their data should be consistent");
            a.show();
        }
        else if(!inputError(Quantam.getText()) && format(Quantam.getText().split(" ")).length == 1){
        String output = "";
        String[] pID = format(id_field.getText().split(" "));
        String[] pAT = format(arrivalTime.getText().split(" "));
        String[] pBT = format(burstTime.getText().split(" "));
        int at[] = new int[pID.length];
        int ts = Integer.parseInt(Quantam.getText());
        float bt[] = new float[pID.length];
        for(int i=0; i< pID.length; i++){
            at[i] = Integer.parseInt(pAT[i]);
            bt[i] = Float.parseFloat(pBT[i]);
        }
        RR r = new RR(pID.length,ts,pID,at,bt);
        String[] s = r.GanttChart();
        String ss = "";
           for(int y = 0; y < s[0].length(); y++){
               ss += "-";
           }
        output = s[0] + "\r\n" + ss + "\r\n" + s[1];
        textArea.appendText("RR chart:\n");
        textArea.appendText(s[0]);
        textArea.appendText("\n");
        textArea.appendText(ss);
        textArea.appendText("\n");
        textArea.appendText(s[1]);
        textArea.appendText("\n");
        textArea.appendText("Average Waiting Time: " + String.valueOf(r.CalcAverageWaitingTime()));
        textArea.appendText("\n");
        textArea.appendText("\n");
        }
        else{
            Alert a = new Alert(AlertType.ERROR);  
            a.setContentText("Quantum field cannot be empty or have characters");
            a.show();
        }
    }
    }

    @FXML
    private void burstAction(ActionEvent event) {
    }

    @FXML
    private void PriorityFieldAction(ActionEvent event) {
    }

    @FXML
    private void DataEntry(ActionEvent event) {
        String[] pID = format(id_field.getText().split(" "));
        String[] pAT = format(arrivalTime.getText().split(" "));
        String[] pBT = format(burstTime.getText().split(" "));
        String s;
        textArea.setText("");
        for(int i = 0; i < pID.length; i++){            
        s = "Process ID:" + pID[i] + "              Arrival Time:" + pAT[i] + "             Burst Time:" + pBT[i] + "\n";
            textArea.appendText(s);
        }
        textArea.appendText("\n");
    }

    @FXML
    private void processNoAction(ActionEvent event) {
    }

    @FXML
    private void QuantamAction(ActionEvent event) {
    }
    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
         } catch(NumberFormatException e) { 
            return false; 
         }        
         return true;
}
    
    public boolean inputError(String s){
        String empty="";
        String[] Arr =format(s.split(" "));
        
        if(s.replaceAll(" ", "").equals(empty)){
             return true;
        }
        else{
        for(int i=0;i<Arr.length;i++){
              if(!isInteger(Arr[i])){
                return true;
              }
        }
        }
        return false;
    }
     public boolean checkLength(){
         String[] ppID = format(id_field.getText().split(" "));
         String[] AT = format(arrivalTime.getText().split(" "));
         String[] BT = format(burstTime.getText().split(" "));
         String[] QT = format(Quantam.getText().split(" "));
         if(ppID.length !=  AT.length || ppID.length !=  BT.length  ){
             return true;
         }
         return false;
     }
     
     public boolean checkValidity(){
        String pID = id_field.getText();
        String pAT = arrivalTime.getText();
        String pBT = burstTime.getText();
        String[] ppID = format(pID.split(" "));
        if(inputError(pID)){
            Alert a = new Alert(AlertType.ERROR);  
            a.setContentText("ID field cannot be empty or have characters");
            a.show();
            return false;
        }
        if(inputError(pAT)){
            Alert a = new Alert(AlertType.ERROR);  
            a.setContentText("Arrival Time field cannot be empty or have characters");
            a.show();
            return false;
        }
        if(inputError(pBT)){
            Alert a = new Alert(AlertType.ERROR);  
            a.setContentText("Burst Time field cannot be empty or have characters");
            a.show();
            return false;
        }
        for(int i = 0; i < ppID.length - 1; i++){
            for(int j = i+1; j < ppID.length; j++){
                if(ppID[i].equals(ppID[j])){
                    Alert a = new Alert(AlertType.ERROR);  
                    a.setContentText("ID of processes should be unique");
                    a.show();
                    return false;
                }
            }
        }
        return true;
     }
     
    public String[] format(String[] Arr){
        int x = 0;
        int y = 0;
        String arrr[];
        for (int i = 0; i < Arr.length; i++){
         if(!Arr[i].equals(" ") && !Arr[i].equals("")){
             x++;
         }
        }
        arrr = new String[x];
        for (int i = 0; i < Arr.length; i++){
         if(!Arr[i].equals(" ") && !Arr[i].equals("")){
             arrr[y] = Arr[i];
             y++;
         }
        }
        return arrr;
    }
    
    public String[] SJFpreemptive(int n, String[] id, String[] Bt, String[] At){
        String output = "";
        int  pid []= new int[n]; // it takes pid of process
        int  at [] = new int[n]; // at means arrival time
        int  bt[] = new int[n]; // bt means burst time
        int  ct[] = new int[n]; // ct means complete time
        int  ta[] = new int[n];// ta means turn around time
        int  wt[] = new int[n];  // wt means waiting time
        int  f[] = new int[n];  // f means it is flag it checks process is completed or not
        int  k[]= new int[n];   // it is also stores brust time
        int  m[]= new int[1000];
        int  a = 0;  //
        int  i, st=0, tot=0;
        float avgwt=0, avgta=0;

        for (i=0;i<n;i++)
        {
            pid[i]= Integer.parseInt(id[i]);
            at[i]= Integer.parseInt(At[i]);
            bt[i]= Integer.parseInt(Bt[i]);
            k[i]= bt[i];
            f[i]= 0;
        }
        int tot_bt = 0;
        for(int p=0; p<n; p++)
        {
            tot_bt+=bt[p];
        }

        while(true){
            int min=99,c=n;
            if (tot==n)
            break;

            for ( i=0;i<n;i++)
            {
                if ((at[i]<=st) && (f[i]==0) && (bt[i]<min))
                {
                min=bt[i];
                c=i;
                }
            }

            if (c==n)
            st++;
            else
            {
                bt[c]--;
                m[a] = c;
                a++;  
                st++;
                if (bt[c]==0)
                {
                ct[c]= st;
                f[c]=1;
                tot++;
                }
                }
           }

           for(i=0;i<n;i++)
           {
                ta[i] = ct[i] - at[i];
                wt[i] = ta[i] - k[i];
                avgwt+= wt[i];
                avgta+= ta[i];
           }

           for(int b = 0; b<tot_bt; b++)
           {
                output += "P" + id[m[b]] + " ";
           }
            String Arr[] = new String[2];
            Arr[0]= output ;
            Arr[1] = String.valueOf(avgwt/n);
            return Arr;  
        }

        public String[] SJFnonpreemptive(int n, String[] id, String[] Bt, String[] At){
            String output ="";
            int pid[] = new int[n];
            int at[] = new int[n];
            int bt[] = new int[n];
            int ct[] = new int[n];
            int ta[] = new int[n];
            int wt[] = new int[n];
            int f[] = new int[n];
            int burst[] = new int[n]; 
            int k=0 ; 

            int st=0, tot=0;
            float avgwt=0, avgta=0;

            for (int i=0;i<n;i++)
           {
            pid[i]= Integer.parseInt(id[i]);
            at[i]= Integer.parseInt(At[i]);
            bt[i]= Integer.parseInt(Bt[i]);
            f[i]= 0;
           }


            while(true)
            {
                int c=n, min = 999999;

                if (tot == n)
                    break;

                for (int i=0; i<n; i++)
                {

                    if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min))
                    {
                        min=bt[i];
                        c=i;
                    }
                }
                if (c==n)
                    st++;
                else
                {
                    ct[c]=st+bt[c];
                    st+=bt[c];
                    ta[c]=ct[c]-at[c];
                    wt[c]=ta[c]-bt[c];
                    f[c]=1;
                    pid[tot] = c + 1;
                    tot++;
                    burst[k] = bt[c];
                    k++;
                }
            }


            for(int i=0;i<n;i++)
            {
                avgwt+= wt[i];
                avgta+= ta[i];
            }
           // System.out.println ("\naverage tat is "+ (float)(avgta/n));
           // System.out.println ("average wt is "+ (float)(avgwt/n));

            for(int i=0;i<n;i++){
                for(int j=0; j<burst[i]; j++)
                {
                    output +="P" + id[pid[i]-1] + " ";
                }
        }
            String Arr[] = new String[2];
            Arr[0]= output ;
            Arr[1] = String.valueOf(avgwt/n);
            return Arr;
        }
        public String setSpace(int n){
            String s = "";
            for(int i = 0; i < n; i++){
                s += " ";
            }
            return s;
        }
        
       public String drawGantt1(String in){
           String chart = "";
           String[] arr = in.split(" ");
           String[] AT = format(arrivalTime.getText().split(" "));
           String start = AT[0];
           for(int x = 1; x< AT.length; x++){
               if(Integer.parseInt(AT[x]) < Integer.parseInt(start)){
                   start = AT[x];
               }
           }
           int i = 0;
           int j = 0;
           while(i < arr.length - 1){
              int count = 1;
              while(j < arr.length - 1){
                  j++;
                  if(!arr[j].equals(arr[i])){
                      break;
                  }
                  count++;
              }
              chart += "|" + setSpace(count) + arr[i] + setSpace(count);
              /* if(j == arr.length - 1) i++;
              else i = j; */
              i = j;
           }
           if(!arr[arr.length - 1].equals(arr[arr.length - 2])){
               chart += "|" + setSpace(1) + arr[arr.length - 1] + setSpace(1);
           }
           String s = "";
           for(int y = 0; y < chart.length(); y++){
               s += "-";
           }
           s += "-\n";
           chart += "|\n" + s + start;
           i = 0;
           j = 0;
           while(i < arr.length - 1){
              int count = 1;
              while(j < arr.length - 1){
                  j++;
                  if(!arr[j].equals(arr[i])){
                      break;
                  }
                  count++;
              }
              int end = Integer.parseInt(start) + count;
              chart += setSpace(count) + "   " + setSpace(count) + Integer.toString(end) ;
              /* if(j == arr.length - 1) i++;
              else i = j; */
              start = Integer.toString(end);
              i = j;
           }
           if(!arr[arr.length - 1].equals(arr[arr.length - 2])){
               chart += "|" + setSpace(1) + arr[arr.length - 1] + setSpace(1);
           }
           
           return chart;
       }
       public String[] nonPrePri(int n,String[] id, String[] at, String[] bt, String[] pri){
        float avg_turnaround_time;
        float avg_waiting_time;
        float avg_response_time;
        int total_turnaround_time = 0;
        int total_waiting_time = 0;
        int total_response_time = 0;
        int total_idle_time = 0;
        String Arr[] = new String[2];
        Arr[0] = "";
       
        
          
        
        
        int pid[]= new int[n];
        int arrival_time[]= new int[n];
        int burst_time[]= new int[n];
        int priority[]= new int[n];
        int start_time[]= new int[n];
        int completion_time[]= new int[n];
        int turnaround_time[]= new int[n];
        int waiting_time[]= new int[n];
        int response_time[]= new int[n];
        
        
        int is_completed[]= new int[n];
        for(int i = 0; i < n; i++)  {
        pid[i]= i+1;
        arrival_time[i] = Integer.parseInt(id[i]);
 
        burst_time[i] = Integer.parseInt(bt[i]);
       
        priority[i] = Integer.parseInt(pri[i]);
        
        
        }
        
        
        int current_time = 0;
        int completed = 0;
        int prev = 0;
        int arr []=new int[999999];
        int index2=0 ;
        
        
        while (completed != n)
	{
		int idx = -1;
		int min = 9999999;
		for (int i = 0; i < n; i++)
		{
			if (arrival_time[i] <= current_time && is_completed[i] == 0)
			{
				if (priority[i] < min)
				{
					min = priority[i];
					idx = i;
				}
				if (priority[i] == min)
				{
					if (arrival_time[i] < arrival_time[idx])
					{
						min = priority[i];
						idx = i;
					}
				}
			}
		}
		if (idx != -1)
		{
			start_time[idx] = current_time;
			completion_time[idx] = start_time[idx] + burst_time[idx];
			turnaround_time[idx] = completion_time[idx] - arrival_time[idx];
			waiting_time[idx] = turnaround_time[idx] - burst_time[idx];
			response_time[idx] = start_time[idx] - arrival_time[idx];

			total_turnaround_time += turnaround_time[idx];
			total_waiting_time += waiting_time[idx];
			total_response_time += response_time[idx];
			total_idle_time += start_time[idx] - prev;

			is_completed[idx] = 1;
			completed++;
			current_time = completion_time[idx];
			prev = current_time;
                                arr[index2]=idx+1;  
                                 index2++;
		}
                 
		else
		{
			current_time++;
		}
                

	}
      //  avg_turnaround_time = (float) total_turnaround_time / n;
	avg_waiting_time = (float) total_waiting_time / n;
        Arr[1] = String.valueOf(avg_waiting_time);
            
                for(int i = 0; i < index2; i++)
               {
                   for (int j =0; j< burst_time[i]; j++) {
                    Arr[0] += "P" + id[arr[i]-1] + " ";
                 }
                   
               }
                return Arr;
       }
       public static String[] Prem_Pir(int n, String[] id, String[] Bt, String[] At,String[] priorityy){	
        String output = "";
	float avg_turnaround_time;
        float avg_waiting_time;
        float avg_response_time;
        int total_turnaround_time = 0;
        int total_waiting_time = 0;
        int total_response_time = 0;
        int total_idle_time = 0;
       
        int pid[] = new int[n];
        int arrival_time[] = new int[n];
        int burst_time[] = new int[n];
        int priority[] = new int[n];
                
        int start_time[] = new int[n];
        int completion_time[] = new int[n];
        int turnaround_time[] = new int[n];
        int waiting_time[] = new int[n];
        int response_time[] = new int[n];
        int burst_remaining[] =new int[n]; 
        int is_completed[]= new int[n];
           
	 for (int i=0;i<n;i++)
           {     
            pid[i]= Integer.parseInt(id[i]);
            arrival_time[i]= Integer.parseInt(At[i]);
            burst_time[i]= Integer.parseInt(Bt[i]);
            priority[i]= Integer.parseInt(priorityy[i]);
            burst_remaining[i] = burst_time[i];
           }
        
  


    int current_time = 0;
    int completed = 0;
    int prev = 0;
    int arr []=new int[999999];
    int index2=0 ;
    // while the proceeses hasn't finished 
    while(completed != n) {
        int idx = -1; //index of process to be executed 
        int min = 1000000000; //Max pirioty 
        
        for (int i = 0; i < n; i++){
	    if (arrival_time[i] <= current_time && is_completed[i] == 0){
		if (priority[i] < min){
		    min = priority[i];
		    idx = i;
					}
		if (priority[i] == min){
		    if (arrival_time[i] < arrival_time[idx]){
			min = priority[i];
			idx = i;
			}
		}

	}
			}
        
        if (idx != -1)
			{
				if (burst_remaining[idx] == burst_time[idx])
				{
					start_time[idx] = current_time;
					total_idle_time += start_time[idx] - prev;
				}
				burst_remaining[idx] -= 1;
				current_time++;
				prev = current_time;
                                
                                
                                if (burst_remaining[idx] == 0)
				{
					completion_time[idx] = current_time;
					turnaround_time[idx] = completion_time[idx] - arrival_time[idx];
					waiting_time[idx] = turnaround_time[idx] - burst_time[idx];
					response_time[idx] = start_time[idx] - arrival_time[idx];

					total_turnaround_time += turnaround_time[idx];
					total_waiting_time += waiting_time[idx];
					total_response_time += response_time[idx];

					is_completed[idx] = 1;
					completed++;
				}
                                arr[index2]=idx+1;  
                                 index2++;

                        }
        else
			{
				 current_time++;
			}
    }
    
                avg_turnaround_time = (float) total_turnaround_time / n;
		avg_waiting_time = (float) total_waiting_time / n;
      System.out.println("The average waithing time is : "+avg_waiting_time);    
                for (int i = 0; i < index2; i++) {
                    output+="P"+id[arr[i]-1]+" ";
                }
               
              String Arr[]=new String[2];  
              Arr[0]=output;
              Arr[1]=String.valueOf(avg_waiting_time);
              return Arr;          
    }
       
    public String drawGantt(String in){
           String chart = "|";
           String[] arr = in.split(" ");
           String[] AT = format(arrivalTime.getText().split(" "));
           String start = AT[0];
           for(int x = 1; x< AT.length; x++){
               if(Integer.parseInt(AT[x]) < Integer.parseInt(start)){
                   start = AT[x];
               }
           }
           int i = 0;
           int j = 0;
           while(i < arr.length - 1){
              int count = 1;
              while(j < arr.length - 1){
                  j++;
                  if(!arr[j].equals(arr[i])){
                      break;
                  }
                  count++;
              }
              int x = getIdx(arr[i]);
              if(Integer.parseInt(AT[x]) > Integer.parseInt(start)){
                  chart += "---|";
                  start = AT[x];
              }
              int end = Integer.parseInt(start) + count;
              chart += setSpace(count) + arr[i] + setSpace(count) + "|";
              /* if(j == arr.length - 1) i++;
              else i = j; */
              start = Integer.toString(end);
              i = j;
           }
           if(!arr[arr.length - 1].equals(arr[arr.length - 2])){
               chart += setSpace(1) + arr[arr.length - 1] + setSpace(1) + "|";
           }
           String s = "";
           for(int y = 0; y < chart.length(); y++){
               s += "-";
           }
           s += "-\n";
           start = AT[0];
           for(int x = 1; x< AT.length; x++){
               if(Integer.parseInt(AT[x]) < Integer.parseInt(start)){
                   start = AT[x];
               }
           }
           chart += "\n" + s + start;
           i = 0;
           j = 0;
           while(i < arr.length - 1){
              int count = 1;
              while(j < arr.length - 1){
                  j++;
                  if(!arr[j].equals(arr[i])){
                      break;
                  }
                  count++;
              }
              int x = getIdx(arr[i]);
              if(Integer.parseInt(AT[x]) > Integer.parseInt(start)){
                  if(Integer.parseInt(start) >= 10){
                      chart += "  " + AT[x];
                  }
                  else{
                  chart += "   " + AT[x];
                  }
                  start = AT[x];
              }
              int end = Integer.parseInt(start) + count;
              if(end >= 10 && Integer.parseInt(start) < 10){
              chart += setSpace(count) + "  " + setSpace(count) + Integer.toString(end) ;
              }
              else if(end >= 10 && Integer.parseInt(start) >= 10){
                  chart += setSpace(count) + " " + setSpace(count) + Integer.toString(end) ;
              }
              else{
                  chart += setSpace(count) + "   " + setSpace(count) + Integer.toString(end) ;
              }
              
              start = Integer.toString(end);
              i = j;
           }
           
           
           return chart;
       }
    public int getIdx(String s){
        char ss = s.charAt(1);
        int x = -1;
        String[] pID =format(id_field.getText().split(" "));
        for(int i = 0; i< pID.length; i++){
            if(pID[i].charAt(0) == ss){
                x = i;
            }
        }
      return x;
    }
       
    }
