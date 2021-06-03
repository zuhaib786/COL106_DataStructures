package ProjectManagement;

public class Job implements Comparable<Job> {
	public Project project;
	public User user;
	public int RunningTime;
	public String status="NOT FINISHED";
	public int startingtime;
	public int endingtime;
	public String name;
    Job(String name,Project project,User User,int RunningTime)
    {
    	this.name=name;
    	this.project=project;
    	this.user=User;
    	this.RunningTime=RunningTime;
    }
	@Override
    public int compareTo(Job job) {
        return this.project.getPriority()-job.project.getPriority();
    }
}