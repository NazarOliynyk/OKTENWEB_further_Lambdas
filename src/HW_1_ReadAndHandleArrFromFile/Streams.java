package HW_1_ReadAndHandleArrFromFile;


import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {
    public static void main(String[] args) {
        HandlePersonCollection hPC = new HandlePersonCollection();

        ArrayList<Person> persons = hPC.getPersons(hPC.getLines("dataOfPersons.txt"));
        persons.forEach(person -> System.out.println(person));

        System.out.println("--------Streams CONVEYOR methods one after one with objects of type strem--------");
        System.out.println("--------Sort anonymous classes-----");

        Stream<Person> stream = persons.stream();

       Stream<Person> soorted= stream.sorted(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getId()-o2.getId();
            }
        });

        List<Person> sortedWithoutLambdas = soorted.collect(Collectors.toList());
        System.out.println(sortedWithoutLambdas);

        System.out.println("------Sort with Lambdas------");

        // call a new stream for each operation!!!
        Stream<Person> stream1 = persons.stream();
                                                //press Shift+Ctrl+Space
        List<Person> sortedWithLambdas = stream1.sorted((o1, o2) ->
                o1.getId()-o2.getId() ).collect(Collectors.toList());
        Stream<Person> stream2 = persons.stream();
        List<Person> sortedWithLambdas1 = stream2.sorted(Comparator.comparing(Person::getSalary))
                .collect(Collectors.toList());
        System.out.println(sortedWithLambdas);
        System.out.println(sortedWithLambdas1);

        System.out.println("---------- Adding similar objects and deleting it-----");
        persons.add(new Person(33, "Morris", 3321.89));
        persons.add(new Person(19, "Oswald", 5008.0));
       // System.out.println(persons);

        List<Person> sortedWithoutNonUnique= persons.stream()
                .distinct()
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());
        System.out.println(sortedWithoutNonUnique);

        System.out.println("-------------filter------------");
        List<Person> filtwredWithoutOddId = persons.stream()
                .filter(person -> person.getId()%2==0)
                .collect(Collectors.toList());
        System.out.println(filtwredWithoutOddId);

        System.out.println("------skip------------------");

        List<Person> skip = persons.stream()
                .skip(3)                     // skips 3 first elements
                .collect(Collectors.toList());
        System.out.println(persons);
        System.out.println(skip);

//        System.out.println("------Change all elements with MAP------------------");
//
//        List<Person> mappedPersons = persons.stream()
//                .map(person -> person.getId()+10000)
//                //.sorted(Comparator.comparing(Person::getName))
//                .collect(Collectors.toList());

//        System.out.println("-----------------Peek----------------");
//
//        List<Person> list = persons.stream()
//                .peek(person -> person.getId()+1000)
//                .collect(Collectors.toList());

        System.out.println("---------------Limit-------------");

        List<Person> limit = persons.stream()
                .limit(4)
                .collect(Collectors.toList());
        System.out.println(limit);

        System.out.println("----------------Terminal Methods--------------------");
        System.out.println("-----------max-------------------------");

        Optional<Person> maxSalary = persons.stream().max(Comparator.comparing(Person::getSalary));
        Optional<Person> lastOfName = persons.stream().max(Comparator.comparing(Person::getName));
        // Optional protect from NullPointerException
        System.out.println(maxSalary.get());
        System.out.println(lastOfName.get());
        // or return a new object IF it returns null !! - default way of return
        // method finds out if there is null and only then changes it for a default object
        System.out.println(lastOfName.orElse(new Person(33, "Morris", 3321.89)));

        System.out.println("-------------count objects---------");
        long count = persons.stream().count();
        System.out.println("count - "+count);

        System.out.println("-----------------matching-----------");

        boolean evenId = persons.stream().anyMatch(person  -> person.getId()%2==0);
        System.out.println("evenId - "+evenId);
        boolean lengthOfName = persons.stream().anyMatch(person  -> person.getName().length()>100);
        System.out.println("lengthOfName - "+lengthOfName);

        boolean allSalary = persons.stream().allMatch(person -> person.getSalary()>1000);
        System.out.println("allSalary - "+allSalary);

        System.out.println("-------------------find first-----------------");

        System.out.println(persons.stream().findFirst().orElse(new Person(00, "FIRST", 0000)));

        System.out.println("--------------------------- find Any--------------");

        System.out.println(persons.stream().findAny().orElse(new Person(00, "FIRST", 0000)));

        System.out.println("-----------------contains-------------------");

        System.out.println(persons.stream().filter(person -> person.getName().contains("o")).collect(Collectors.toList()));

        System.out.println("----------------none match-------------------");

        System.out.println("person.getId()>500) - "+persons.stream().noneMatch(person -> person.getId()>500));

        System.out.println("---------------to Array elements of class or complete object----------------");

        System.out.println(Arrays.toString(persons.stream().map(person -> person.getName()).toArray(String[]::new)));
    }
}
