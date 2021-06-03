package ProjectManagement;

public class User implements Comparable<User> {

	private String name;
	User(String name)
	{
		this.name=name;
	}
    @Override
    public int compareTo(User user) {
        return this.name.compareTo(user.getName());
    }
    public String getName()
    {
    	return this.name;
    }
}
