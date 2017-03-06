package task_manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.time.LocalDate;
import task_manager.Task.TaskType;

public class TaskManager {
	
	private HashMap<TaskType, TreeSet<Task>> allTasks;
	private HashMap<TaskType, Integer> numOfFinishedTasks;
	private HashMap<TaskType, Integer> numOfUnfinishedTasks;
	private HashMap<TaskType, Integer> missedTasks;
	private int count;
	private int countFinish;
	
	public TaskManager() {
		this.allTasks = new HashMap<TaskType, TreeSet<Task>>();
		this.numOfFinishedTasks = new HashMap<TaskType, Integer>();
		this.numOfUnfinishedTasks = new HashMap<TaskType, Integer>();
		this.count=0;
		this.countFinish=0;
	}
	
	
	public void addTask(Task task){
		if(!this.allTasks.containsKey(task.getTaskType())){
			this.allTasks.put(task.getTaskType(), new TreeSet());
			 this.numOfUnfinishedTasks.put(task.getTaskType(),count++);
			}
		    this.allTasks.get(task.getTaskType()).add(task);
		  
		
	}
	
	public void removeTask(Task task){
		allTasks.get(task.getTaskType()).remove(task);
		
	}
	
	public void editTask(Task oldTask, Task newTask){
		removeTask(oldTask);
		addTask(newTask);
		
	} 
	
	public void printHelpInfo(){
		System.out.println(""
				+ "Hello to work with Task manager must make zadacha- added task"
				+ "You must add that for every day or for a day"
				+ "and add an hour if you want to see tasks press see tasks");
		
	}
	
	public void printTodayTasks(){
		for(Iterator<Entry<TaskType, TreeSet<Task>>>it = allTasks.entrySet().iterator();it.hasNext();){
			Entry<TaskType, TreeSet<Task>> e=it.next();
			for(Iterator<Task> et=e.getValue().iterator();et.hasNext();){
				Task t=et.next();
			
				if(t.getDate()==LocalDate.now()){
					System.out.print(t.getName()+" / ");
					System.out.print(t.getTaskType()+" / ");
					System.out.print(t.getDate()+" / ");
					System.out.print(t.getTime());
					System.out.println();
					System.out.println(t.getDescription());
				}
			  }
		    }
		   
	        }
	
	public void printChartReminder(){
		int numReminder=getTypeList(TaskType.Reminder);
		int numReminderFinish=getTypeListFinish(TaskType.Reminder);
		System.out.println("from  -"+numReminder+"-has completed-"+numReminderFinish);
		
	}
	
	public void printChartRoutine(){
		int numRoutine=getTypeList(TaskType.Routine);
		int numRoutineFinish=getTypeListFinish(TaskType.Routine);
		System.out.println("from  -"+numRoutine+"-has completed-"+numRoutineFinish);
		
	}
	
	public void printMissedRoutine(){
		// TODO print missedRoutine-
	}
	public void printMissedReminder(){
		// TODO print missedReminder-
	}
	
	public void setCompleteness(boolean isCompleted, Task task){
		if(isCompleted){
			if(allTasks.containsKey(task.getTaskType())){
				allTasks.get(task.getTaskType()).remove(task);
				if(this.numOfUnfinishedTasks.containsKey(task.getTaskType())){
				this.numOfUnfinishedTasks.remove(task.getTaskType());
				count--;
				}
				countFinish++;
				this.numOfFinishedTasks.put(task.getTaskType(),countFinish);
			}
		}
		
	}
	private int getTypeList(TaskType t){
		int c1=0;
		for(Iterator<Entry<TaskType, Integer>> it=numOfUnfinishedTasks.entrySet().iterator();it.hasNext();){
			
			Entry<TaskType, Integer> et=it.next();
			if(et.getKey()==t){
			c1+=et.getValue();
			}
	     }return c1;
      }
	
	
	
	private int getTypeListFinish(TaskType t){
		int a=0;
		for(Iterator<Entry<TaskType, Integer>> it=numOfFinishedTasks.entrySet().iterator();it.hasNext();){
			
			Entry<TaskType, Integer> et=it.next();
			if(et.getKey()==t){
			a+=et.getValue();
			}
	     }return a;
      }
	
	
	
}


