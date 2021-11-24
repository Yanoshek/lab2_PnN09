package tb.soft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class CollectionConsoleApp {
	private static final String MENU = 
			"    M E N U   G £ Ó W N E  		\n" +
			"1 - Podaj dane nowej osoby 		\n" + 
			"2 - Usuñ dane wybranej osoby   	\n" + 
			"3 - Modyfikuj dane osoby   		\n" + 
			"4 - Zapisz osobê do list			\n" + 
			"5 - Usuñ dane z list       		\n" + 
			"6 - Wypisz dane z list     		\n" +
			"7 - Comparable vs Comparator		\n" +
			"8 - Dodaj dane do betterPerson 	\n" +
			"0 - Zakoñcz program        		\n";
	
	private static final String DELETE_DATA_MENU = 
			"    U S U Ñ   D A N E		\n" +
			"1 - Usuñ dane z HashSet 	\n" + 
			"2 - Usuñ dane z TreeSet 	\n" + 
			"3 - Usuñ dane z ArrayList 	\n" + 
			"4 - Usuñ dane z LinkedList \n" +
			"5 - Usuñ dane z HashMap 	\n" + 
			"6 - Usuñ dane z TreeMap 	\n" + 
			"0 - Powrót do menu g³ównego\n";
	
	private static final String COMPARABLE_VS_COMPARATOR =
			"    C O M P A R A B L E 		\n" + 
			"            V S                \n" +
			"    C O M P A R A T O R        \n\n" +
			"1 - Posortuj z comparable 		\n" + 
			"2 - Posortuj z comparator		\n" + 
			"0 - Powrót do menu g³ównego	\n";
	
	private static final String CHANGE_MENU = 
			"   Co zmieniæ?     \n" + 
			"1 - Imiê           \n" + 
			"2 - Nazwisko       \n" + 
			"3 - Rok urodzenia  \n" + 
			"4 - Stanowisko     \n" + 
			"0 - Powrót do menu g³ównego\n";

	private static final ConsoleUserDialog UI = new ConsoleUserDialog();

	public static void main(String[] args) {
		CollectionConsoleApp application = new CollectionConsoleApp();
		application.runMainLoop();
	}
	
	private Person currentPerson = null;
	private BetterPerson currentBetterPerson = null;
	
	//Tworzenie zbiorów z klasy better person
	private HashSet<Person> betterPeopleHashSet = new HashSet<>();
	private TreeSet<Person> betterPeopleTreeSet = new TreeSet<>();
	
	//Tworzenie list z klasy person
	private HashSet<Person> peopleHashSet = new HashSet<>();
	private TreeSet<Person> peopleTreeSet = new TreeSet<>();
	private ArrayList<Person> peopleArrayList = new ArrayList<>();
	private LinkedList<Person> peopleLinkedList = new LinkedList<>();
	private Map<Integer, Person> peopleHashMap = new HashMap<>();
	private TreeMap<Integer, Person> peopleTreeMap = new TreeMap<>();
	private int key = 0;

	public void runMainLoop() {

		while (true) {
			UI.clearConsole();
			showCurrentPerson();

			try {
				switch (UI.enterInt(MENU + "==>> ")) {
				case 1:
					currentPerson = createNewPerson();
					
					break;
				case 2:
					currentPerson = null;
					UI.printInfoMessage("Dane aktualnej osoby zosta³y usuniête");
					break;
				case 3:
					if (currentPerson == null)
						throw new PersonException("¯adna osoba nie zosta³a utworzona.");
					changePersonData(currentPerson);
					break;
				case 4: {
					if (currentPerson == null)
						throw new PersonException("¯adna osoba nie zosta³a utworzona.");
					peopleHashSet.add(currentPerson);
					peopleTreeSet.add(currentPerson);
					peopleArrayList.add(currentPerson);
					peopleLinkedList.add(currentPerson);
					peopleHashMap.put(++key, currentPerson);
					peopleTreeMap.put(key, currentPerson);
					break;
				}
				case 5: {
					deleteDataFromList();
					break;
				}
				case 6: {
					showLists();
					break;
				}
				case 7: {
					comparableVsComparator();
					break;
				}
				case 8: {
					currentBetterPerson = createNewBetterPerson();
					betterPeopleHashSet.add(currentBetterPerson);
					betterPeopleTreeSet.add(currentBetterPerson);
					break;
				}
				case 0:
					UI.printInfoMessage("\nProgram zakoñczy³ dzia³anie!");
					System.exit(0);
				}
			} catch (PersonException e) {

				UI.printErrorMessage(e.getMessage());
			}
		}
	}

	void showCurrentPerson() {
		showPerson(currentPerson);
	}
	
	static void showPerson(Person person) {
		StringBuilder sb = new StringBuilder();

		if (person != null) {
			sb.append("Aktualna osoba: \n").append("      Imiê: ").append(person.getFirstName()).append("\n")
					.append("  Nazwisko: ").append(person.getLastName()).append("\n").append("   Rok ur.: ")
					.append(person.getBirthYear()).append("\n").append("Stanowisko: ").append(person.getJob())
					.append("\n");
		} else
			sb.append("Brak danych osoby\n");
		UI.printMessage(sb.toString());
	}
	//Porównuje dzia³anie comarable i comaratora na podstawie sortowania array list
	void comparableVsComparator() {
		while(true) {
			UI.clearConsole();
			switch (UI.enterInt(COMPARABLE_VS_COMPARATOR + "==>> ")) {
			case 1:
				Collections.sort(peopleArrayList);
				return;
			case 2:
				FNComparator fncomparator = new FNComparator();
				Collections.sort(peopleArrayList, fncomparator);
				return;
			case 0:
				return;
			}
		}
	}
	
	//Wypisuje listy na podstawie iteracji i ³¹czenia stringów
	void showLists() {
		StringBuilder sb = new StringBuilder();
		
		
		sb.append("HashSet:  	");
		for (Person person : peopleHashSet) {
			sb.append(person + "; ");
		}
		sb.append('\n');
		sb.append("TreeSet: 	");
		System.out.println();
		for (Person person : peopleTreeSet) {
			sb.append(person + "; ");
		}
		sb.append('\n');
		sb.append("ArrayList:  	");
		System.out.println();
		for (Person person : peopleArrayList) {
			sb.append(person + "; ");
		}
		sb.append('\n');
		sb.append("LinkedList:	");
		for (Person person : peopleLinkedList) {
			sb.append(person + "; ");
		}
		sb.append('\n');
		sb.append("HashHashMap:	");
		for(Map.Entry<Integer, Person> people: peopleHashMap.entrySet()) {
			sb.append(people.getKey() + "=" + people.getValue()+ ";");
		}
		sb.append('\n');
		sb.append("TreeMap:   	");
		for(Map.Entry<Integer, Person> people: peopleTreeMap.entrySet()) {
			sb.append(people.getKey() + "=" + people.getValue()+ ";");
		}
		sb.append('\n');
		sb.append("Better HashSet:  	");
		for (Person person : betterPeopleHashSet) {
			sb.append(person + "; ");
		}
		sb.append('\n');
		sb.append("Better TreeSet: 	");
		System.out.println();
		for (Person person : betterPeopleTreeSet) {
			sb.append(person + "; ");
		}
		sb.append('\n');
		
		UI.printInfoMessage(sb.toString());
	}
	
	//Tworzenie nowego BetterPerson
	static BetterPerson createNewBetterPerson() {
		String first_name = UI.enterString("Podaj imiê: ");
		String last_name = UI.enterString("Podaj nazwisko: ");
		String birth_year = UI.enterString("Podaj rok ur.: ");
		UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
		String job_name = UI.enterString("Podaj stanowisko: ");
		BetterPerson person;
		try {
			person = new BetterPerson(first_name, last_name);
			person.setBirthYear(birth_year);
			person.setJob(job_name);
		} catch (PersonException e) {
			UI.printErrorMessage(e.getMessage());
			return null;
		}
		return person;
	}
	
	static Person createNewPerson() {
		String first_name = UI.enterString("Podaj imiê: ");
		String last_name = UI.enterString("Podaj nazwisko: ");
		String birth_year = UI.enterString("Podaj rok ur.: ");
		UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
		String job_name = UI.enterString("Podaj stanowisko: ");
		Person person;
		try {
			person = new Person(first_name, last_name);
			person.setBirthYear(birth_year);
			person.setJob(job_name);
		} catch (PersonException e) {
			UI.printErrorMessage(e.getMessage());
			return null;
		}
		return person;
	}
	
	//Usuwanie osób z list z odpowienimi sposobami dla danych list
	void deleteDataFromList() {
		while(true) {
			UI.clearConsole();
			switch (UI.enterInt(DELETE_DATA_MENU + "==>> ")) {
			case 1:
				peopleHashSet.remove(createNewPerson());
				return;
			case 2:
				peopleTreeSet.remove(createNewPerson());
				return;
			case 3:
				peopleArrayList.remove(UI.enterInt("Podaj index: "));
				return;
			case 4:
				peopleLinkedList.remove(UI.enterInt("Podaj index: "));
				return;
			case 5:
				peopleHashMap.remove(UI.enterInt("Podaj index: "));
				return;
			case 6:
				peopleTreeMap.remove(UI.enterInt("Podaj index: "));
				return;
			case 7:
				betterPeopleHashSet.remove(createNewBetterPerson());;
				return;
			case 8:
				betterPeopleTreeSet.remove(createNewBetterPerson());
				return;
			case 0:
				return;
			}
		}
	}
	
	static void changePersonData(Person person) {
		while (true) {
			UI.clearConsole();
			showPerson(person);

			try {
				switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
				case 1:
					person.setFirstName(UI.enterString("Podaj imiê: "));
					break;
				case 2:
					person.setLastName(UI.enterString("Podaj nazwisko: "));
					break;
				case 3:
					person.setBirthYear(UI.enterString("Podaj rok ur.: "));
					break;
				case 4:
					UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
					person.setJob(UI.enterString("Podaj stanowisko: "));
					break;
				case 0:
					return;
				}
			} catch (PersonException e) {
				UI.printErrorMessage(e.getMessage());
			}
		}
	}

}