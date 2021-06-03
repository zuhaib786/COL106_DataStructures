package Trie;

public class Person implements Comparable {
	private String name;
	private String phone_number;

    public Person(String name, String phone_number) {
    	this.name=name;
    	this.phone_number=phone_number;
    }

    public String getName() {
        return name;
    }
    public String getPhone_Number() {
    	return this.phone_number;
    }
    public String toString()
    {
    	return "[Name: "+name+", Phone="+phone_number+"]";
    }
	@Override
	public int compareTo(Object o) {
		Person x=(Person) o;
		return name.compareTo(x.getName());
	}
}
