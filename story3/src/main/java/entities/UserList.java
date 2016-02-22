package entities;	

import java.io.Serializable;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;

	@XmlRootElement // this is required to enable JSON serialization
	public class UserList implements Serializable {

	    public Collection<User> getUserCollection() {
	        return userCollection;
	    }

	    public void setDiscCollection(Collection<User> userCollection) {
	        this.userCollection = userCollection;
	    }


	    private Collection<User> userCollection;	


}
