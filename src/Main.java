import java.util.*;

public class Main {
    public static List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
    public static List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
    public static Collection<Person> persons = new ArrayList<>();

    public static void main(String[] args) {
        persons = createCollection(names, families);

        long countOfUnderage = persons.stream()
                .filter(persons -> persons.getAge() < 18)
                .count();

        List<String> listOfRecruit = persons.stream()
                .filter(persons -> persons.getSex().equals(Sex.MAN))
                .filter(persons -> persons.getAge() >= 18)
                .filter(persons -> persons.getAge() < 27)
                .map(String::valueOf)
                .toList();

        List<Person> listOfWorkablePeople = persons.stream()
                .filter(persons -> persons.getAge() >= 18)
                .filter(persons -> persons.getAge() < 65)
                .filter(persons -> persons.getAge() < 60 || persons.getSex().equals(Sex.WOMAN))
                .filter(persons -> persons.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .toList();

        System.out.println("Количество несовершеннолетних: " + countOfUnderage);
        System.out.println("Список призывников (мужчины от 18 до 27 лет): " + listOfRecruit);
        System.out.println("Список потенциально работоспособных людей с высшим образованием (женщины 18-60 лет, мужчины 18-65 лет): "
                + listOfWorkablePeople);
    }

    public static Collection<Person> createCollection(List<String> names, List<String> families) {
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        return persons;
    }
}