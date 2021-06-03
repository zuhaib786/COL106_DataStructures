package ProjectManagement;


public class Project {
	private String name;
	private int Budget;
	private int priority;
	Project(String name,int Budget,int Priority)
	{
		this.priority=Priority;
		this.name=name;
		this.Budget=Budget;
	}
	public int getBudget()
	{
		return this.Budget;
	}
	public int getPriority()
	{
		return this.priority;
	}
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public void setBudget(int Budget)
	{
		this.Budget+=Budget;
	}
	public void setPrioity(int Priority)
	{
		this.priority=Priority;
	}
}
