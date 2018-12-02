package CW_1_Computers;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by okten29 on 11/28/18.
 */
public class Main {

    static  String[] hdModels={"Seagate", "WD III", "Samsung", "Kingston", "Toshiba", "WD Red", "WD SATA", "HGST"};
    static String[] compModel={"Acer", "Apple", "HP", "Lenovo", "Dell", "Asus", "MSI", "Fujitsu", "Sony", "Samsung"};
    static Country[] countries = {Country.CHINA, Country.COREA, Country.INDIA, Country.USA};
    static Type[] types ={Type.HDD, Type.SSD};

    static Random random = new Random();

    public static ArrayList<Computer> addComputers(int quantity){
        ArrayList<Computer> computers = new ArrayList<>();


        for (int i = 0; i < quantity; i++) {

            Computer computer = new Computer(compModel[random.nextInt(10)],
                    random.nextInt(18)+2000, random.nextInt(4)*2+2, (random.nextInt(10)+10)*1000,
                    new HardDrive(hdModels[random.nextInt(8)], types[random.nextInt(2)],
                            random.nextInt(16)*100+100, countries[random.nextInt(4)]),
                    new Processor((random.nextInt(3)+1)*2, random.nextInt(4)+1, countries[random.nextInt(4)]));
            computers.add(computer);
        }

        return computers;
    }

    public static void main(String[] args) {

        ArrayList<Computer> computers = addComputers(20);

        computers.stream().forEach(computer -> System.out.println(computer));

        // Note - after each print data varies - each new random numbers are called!
        System.out.println("-----------made after 2010------------");
        List<Computer> madeAfter2010 = computers.stream()
                .filter(computer -> computer.getYear()>2010)
               // .filter(computer -> computer.getHardDrive().getCountry()==Country.CHINA)
                .collect(Collectors.toList());

        madeAfter2010.stream().forEach(computer -> System.out.println(computer));

        System.out.println("-----------made after 2010 in CHINA------------");
        List<Computer> madeAfter2010InChina = computers.stream()
                .filter(computer -> computer.getYear()>2010)
                 .filter(computer -> computer.getHardDrive().getCountry()==Country.CHINA) // equals also works
                .collect(Collectors.toList());

        madeAfter2010InChina.stream().forEach(computer -> System.out.println(computer));

        System.out.println("-----------made after 2010 NOT in CHINA------------");

        List<Computer> madeAfter2010NotInChina = computers.stream()
                .filter(computer -> computer.getYear()>2010)
                .filter(computer -> computer.getHardDrive().getCountry()!=Country.CHINA) // equals also works
                .collect(Collectors.toList());

        madeAfter2010NotInChina.stream().forEach(computer -> System.out.println(computer));

        System.out.println("-----------Hard Drive more than 500------------");

        List<Computer> hardDriveMoreThan500 = computers.stream()
                .filter(computer -> computer.getHardDrive().getVolume()>500)
                .collect(Collectors.toList());

        hardDriveMoreThan500.stream().forEach(computer -> System.out.println(computer));

        System.out.println("------------Hard Drive more than 500 sorted by price---------------");

        List<Computer> hardDriveMoreThan500Sorted = computers.stream()
                .filter(computer -> computer.getHardDrive().getVolume()>500)
                .sorted(Comparator.comparing(Computer::getPrice))
                .collect(Collectors.toList());

        hardDriveMoreThan500Sorted.stream().forEach(computer -> System.out.println(computer));

        System.out.println("--Type HDD, frequency more than 2, Country COREA, sorted--");

        List<Computer> list1 = computers.stream()
                .filter(computer -> computer.getHardDrive().getCountry().equals(Country.COREA))
                .filter(computer -> computer.getProcessor().getFrequency()>2)
                .filter(computer -> computer.getHardDrive().getType().equals(Type.HDD))
                .sorted(Comparator.comparing(Computer::getYear))
                .collect(Collectors.toList());

        list1.stream().forEach(computer -> System.out.println(computer));

        System.out.println("---------------first 4 elements-------------");

        List<Computer> first4 = computers.stream()
                .limit(4)
                .collect(Collectors.toList());

        first4.stream().forEach(computer -> System.out.println(computer));

        System.out.println("---------------Max price---------------");

        Optional<Computer> maxPrice = computers.stream().max(Comparator.comparing(Computer::getPrice));
        System.out.println(maxPrice.get());

        System.out.println("---------------LAST of names---------------");

        Optional<Computer> lastOfnames = computers.stream().max(Comparator.comparing(Computer::getModel));
        System.out.println(lastOfnames.get());

        System.out.println("------------- Count ----------------");

        long length = computers.stream().count();
        System.out.println("length - "+length);

        System.out.println("-----------------Match---------------------");

        boolean evenYear = computers.stream().anyMatch(computer -> computer.getYear()%2==0);
        System.out.println("evenYear - "+evenYear);

        boolean evenYearAll = computers.stream().allMatch(computer -> computer.getYear()%2==0);
        System.out.println("evenYearAll - "+evenYearAll);

        boolean longName = computers.stream().anyMatch(computer -> computer.getModel().length()>7);
        System.out.println("longName - "+longName);

        boolean highFrequency = computers.stream().noneMatch(computer -> computer.getProcessor().getFrequency()>7);
        System.out.println("highFrequency/ none / - "+highFrequency);

        System.out.println("------------------ find ----------------");

        System.out.println("findFirst() - "+computers.stream().findFirst().get());
        System.out.println("findAny() - "+computers.stream().findAny().get()); // at least 1

        System.out.println("---------------- Contains ---------------");

        List<Computer> namesWithD = computers.stream()
                .filter(computer -> computer.getHardDrive().getModel().contains("D"))
                .collect(Collectors.toList());

        namesWithD.stream().forEach(computer -> System.out.println(computer));

        System.out.println("---------------to Array elements of class or complete object----------------");

        System.out.println(Arrays.toString(computers.stream()
                .map(computer -> computer.getModel()).toArray(String[]::new)));

        System.out.println(Arrays.toString(computers.stream()
                .map(computer -> computer.getHardDrive()).toArray(HardDrive[]::new)));

        System.out.println(Arrays.toString(computers.stream()
                .map(computer -> computer.getProcessor().getCountry()).toArray(Country[]::new)));

        System.out.println(Arrays.toString(computers.stream()
                .map(computer -> computer.getProcessor().getFrequency()).toArray(Integer[]::new)));

    }
}
