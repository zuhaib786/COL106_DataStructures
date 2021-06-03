package ProjectManagement;

import PriorityQueue.PriorityQueueDriverCode;

import java.io.*;
import java.net.URL;
import Trie.*;
import PriorityQueue.*;
import RedBlack.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
@SuppressWarnings("all")
public class Scheduler_Driver extends Thread implements SchedulerInterface {
	private int globalTime=0;
	private Trie<Job>jobsTrie;
	private MaxHeap<Job>jobsHeap;
	private RBTree <String,Project> projects;
	private RBTree<String,User>users;
	private ArrayList<Job> compJobs;
	private HashMap<String,ArrayList<ArrayList>> IncompJobs;
	//private ArrayList<Job> incompJobs;
	Scheduler_Driver()
	{
		jobsTrie=new Trie<Job>();
		jobsHeap=new MaxHeap<Job>();
		projects=new RBTree<String,Project>();
		users=new RBTree<String,User>();
		compJobs=new ArrayList<Job>();
		IncompJobs=new HashMap<String,ArrayList<ArrayList>>();
		//incompJobs=new ArrayList<Job>();
	}
    public static void main(String[] args) throws IOException {
        Scheduler_Driver scheduler_driver = new Scheduler_Driver();

        File file;
        if (args.length == 0) {
            URL url = Scheduler_Driver.class.getResource("INP");
            file = new File(url.getPath());
        	//file=new File("C:\\Users\\Zuhaib Ul Zaman\\Desktop\\assignment_4_v3\\src1\\RedBlack\\INP");
        } else {
            file = new File(args[0]);
        }

        scheduler_driver.execute(file);
    }

    public void execute(File file) throws IOException {

        URL url = Scheduler_Driver.class.getResource("INP");
        file = new File(url.getPath());
    	//file= new File("C:\\Users\\Zuhaib Ul Zaman\\Desktop\\assignment_4_v3\\src1\\ProjectManagement\\INP");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found. "+file.getAbsolutePath());
        }
        String st;
        while ((st = br.readLine()) != null) {
            String[] cmd = st.split(" ");
            if (cmd.length == 0) {
                System.err.println("Error parsing: " + st);
                return;
            }

            switch (cmd[0]) {
                case "PROJECT":
                    handle_project(cmd);
                    break;
                case "JOB":
                    handle_job(cmd);
                    break;
                case "USER":
                    handle_user(cmd[1]);
                    break;
                case "QUERY":
                    handle_query(cmd[1]);
                    break;
                case "":
                    handle_empty_line();
                    break;
                case "ADD":
                    handle_add(cmd);
                    break;
                default:
                    System.err.println("Unknown command: " + cmd[0]);
            }
        }


        run_to_completion();

        print_stats();

    }




    @Override
    public void run() {
        // till there are JOBS
        schedule();
    }
   

    @Override
    public void run_to_completion() {
    	System.out.println("Running code");
    	System.out.println("Remaining jobs: "+jobsHeap.size);
    	while(jobsHeap.size>0)
    	{
    		ArrayList MP=jobsHeap.NewExtractMax();
    		Job j=(Job)MP.get(0);
        	//RedBlackNode<String,Project> P=projects.search(j.project.getName());
        	//Project x=P.getValue();
    		Project x=j.project;
        	System.out.println("Executing: "+j.name+" from: "+x.getName());
        	if(x.getBudget()>j.RunningTime)
        	{
        		/*for(int i=0;i<arr.size();i++)
        		{
        			jobsHeap.insert(arr.get(i));
        		}*/
        		x.setBudget(-1*j.RunningTime);
        		j.status="COMPLETED";
        		j.startingtime=globalTime;
        		j.endingtime=j.startingtime+j.RunningTime;
        		globalTime+=j.RunningTime;
        		System.out.println("Project: "+x.getName()+" budget remaining: "+x.getBudget());
        		System.out.println("System execution completed");
        		compJobs.add(j);
        		if(jobsHeap.size>0) {
        		System.out.println("Running code");
            	System.out.println("Remaining jobs: "+jobsHeap.size);}
        	}
        	else
        	{
        		System.out.println("Un-sufficient budget.");
        		//arr.add(j);
        		ArrayList z=new ArrayList();
        		z.add(j);
        		z.add((int)MP.get(1));
        		ArrayList ad=new ArrayList();
        		ad.add(z);
        		if(IncompJobs.containsKey(x.getName()))
        		{
        			ArrayList<ArrayList>A=IncompJobs.get(x.getName());
        			A.add(z);
        			IncompJobs.put(x.getName(),A);
        		}
        		else
        		IncompJobs.put(x.getName(),ad);
        	}
    	}
    	//System.out.println("Running code");
    	//System.out.println("Remaining jobs: "+jobsHeap.size);
    }

    @Override
    public void handle_project(String[] cmd) {
    	Project x=new Project(cmd[1],Integer.parseInt(cmd[3]),Integer.parseInt(cmd[2]));
    	projects.insert(cmd[1], x);
    	System.out.println("Creating project");
    	return;
    }

    @Override
    public void handle_job(String[] cmd) {
    	RedBlackNode<String,Project> P=projects.search(cmd[2]);
    	if(P==null||P.getValues()==null)
    	{
    		System.out.println("Creating job");
    		System.out.println("No such project exists. "+cmd[2]);
    		return;
    	}
    	RedBlackNode<String,User> u=users.search(cmd[3]);
    	if(u==null||u.getValues()==null)
    	{
    		System.out.println("Creating job");
    		System.out.println("No such user exists: "+cmd[3]);
    		return;
    	}
    	//System.out.println(u);
    	Job j=new Job(cmd[1],P.getValue(),u.getValue(),Integer.parseInt(cmd[4]));
    	jobsTrie.insert(cmd[1],j);
    	jobsHeap.insert(j);
    	System.out.println("Creating job");
    }

    @Override
    public void handle_user(String name) {
    	User u=new User(name);
    	users.insert(name, u);
    	System.out.println("Creating user");
    }

    @Override
    public void handle_query(String key) {
    	System.out.println("Querying");
    	TrieNode<Job> j=jobsTrie.search(key);
    	if(j==null)
    	{
    		System.out.println(key+": NO SUCH JOB");
    	}
    	else
    	{
    		System.out.println(key+": "+j.getValue().status);
    	}
    }

    @Override
    public void handle_empty_line() {
    	System.out.println("Running code");
    	System.out.println("Remaining jobs: "+jobsHeap.size);
    	//ArrayList<Job>arr=new ArrayList<Job>();
    	if(jobsHeap.size==0)
    	{
    		System.out.println("Execution cycle completed");
    		return;
    	}
    	while(jobsHeap.size>0)
    	{
    		ArrayList j=jobsHeap.NewExtractMax();
        	//RedBlackNode<String,Project> P=projects.search(j.project.getName());
        	//Project x=P.getValue();
    		Job J=(Job)j.get(0);
    		Project x=J.project;
    		//System.out.println("	"+x.getName());
        	System.out.println("Executing: "+J.name+" from: "+x.getName());
        	if(x.getBudget()>J.RunningTime)
        	{
        		/*for(int i=0;i<arr.size();i++)
        		{
        			jobsHeap.insert(arr.get(i));
        		}*/
        		x.setBudget(-1*J.RunningTime);
        		J.status="COMPLETED";
        		J.startingtime=globalTime;
        		J.endingtime=J.startingtime+J.RunningTime;
        		globalTime+=J.RunningTime;
        		System.out.println("Project: "+x.getName()+" budget remaining: "+x.getBudget());
        		System.out.println("Execution cycle completed");
        		compJobs.add(J);
        		return;
        	}
        	else
        	{
        		System.out.println("Un-sufficient budget.");
        		//arr.add(j);
        		if(IncompJobs.containsKey(x.getName()))
        		{
        			//System.out.println("	x.getName()= "+x.getName());
        			ArrayList<ArrayList>A=IncompJobs.get(x.getName());
        			A.add(j);
        			IncompJobs.put(x.getName(), A);
        		}
        		else
        		{
        			ArrayList<ArrayList>p=new ArrayList<ArrayList>();
        			p.add(j);
        			IncompJobs.put(x.getName(),p);
        		}
        	}
    	}
    	/*for(int i=0;i<arr.size();i++)
		{
			jobsHeap.insert(arr.get(i));
		}*/
    	return;
    }

    @Override
    public void handle_add(String[] cmd) {
    	System.out.println("ADDING Budget");
    	RedBlackNode<String,Project>P=projects.search(cmd[1]);
    	if(P==null||P.getValues()==null)
    	{
    		System.out.println("No such project exists. "+cmd[1]);
    	}
    	Project x=P.getValue();
    	x.setBudget(Integer.parseInt(cmd[2]));
    	ArrayList<ArrayList> r=IncompJobs.get(x.getName());
    	if(r!=null)
    	IncompJobs.remove(x.getName());
    	if(r==null) return;
    	else
    	{
    		for(int i=0;i<r.size();i++)
    		{
    			ArrayList A=r.get(i);
    			Job y=(Job)A.get(0);
    			int idx=(int)A.get(1);
    			jobsHeap.NewInsert(y,idx);
    			//jobsHeap.insert(y);
    		}
    	}
    }

    @Override
    public void print_stats() {
    	System.out.println("--------------STATS---------------");
    	System.out.println("Total jobs done: "+compJobs.size());
    	for(int i=0;i<compJobs.size();i++)
    	{
    		Job x=compJobs.get(i);
    		System.out.println("Job{user='"+x.user.getName()+"', project='"+x.project.getName()+"', jobstatus="+x.status+", execution_time="+x.RunningTime+", end_time="+x.endingtime+", name='"+x.name+"'}");
    	}
    	System.out.println("------------------------");
    	System.out.println("Unfinished jobs: ");
    	int s=0;
    	MaxHeap<Job> m=new MaxHeap<Job>();
    	for(ArrayList<ArrayList>A:IncompJobs.values())
    	{
    		for(int j=0;j<A.size();j++)
    		{
    			s+=1;
    			ArrayList r=A.get(j);
    			Job x=(Job)r.get(0);
    			m.NewInsert(x,(int)r.get(1));
    			//System.out.println(x.project.getName()+" "+r.get(1));
    			//System.out.println("Job{user='"+x.user.getName()+"', project='"+x.project.getName()+"', jobstatus="+"REQUESTED"+", execution_time="+x.RunningTime+", end_time=null"+", name='"+x.name+"'}");
    		}
    	}
    	while(m.size>0)
    	{
    		Job x=m.extractMax();
    		System.out.println("Job{user='"+x.user.getName()+"', project='"+x.project.getName()+"', jobstatus="+"REQUESTED"+", execution_time="+x.RunningTime+", end_time=null"+", name='"+x.name+"'}");
    	}
    	System.out.println("Total unfinished jobs: "+s);
    	System.out.println("--------------STATS DONE---------------");
    	return;
    }

    @Override
    public void schedule() {
    }
}
