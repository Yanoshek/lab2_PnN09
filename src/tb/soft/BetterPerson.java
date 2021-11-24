package tb.soft;

import java.util.Objects;

public class BetterPerson extends Person {

	public BetterPerson(String first_name, String last_name) throws PersonException {
		super(first_name, last_name);
	}
	@Override
	public int hashCode() {
		return Objects.hash(birthYear, firstName, lastName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return birthYear == other.birthYear && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName);
	}
	
}
