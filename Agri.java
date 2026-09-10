import java.util.ArrayList;
import java.util.Scanner;

class User {

    String name;
    String email;

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

class Equipment {

    int equipmentId;
    String name;
    double rentPerDay;
    String ownerName;
    boolean available;

    Equipment(int equipmentId, String name,
              double rentPerDay, String ownerName) {

        this.equipmentId = equipmentId;
        this.name = name;
        this.rentPerDay = rentPerDay;
        this.ownerName = ownerName;
        this.available = true;
    }

    void displayEquipment() {

        System.out.println("\n-------------------------");
        System.out.println("Equipment ID : " + equipmentId);
        System.out.println("Equipment    : " + name);
        System.out.println("Rent/Day     : Rs." + rentPerDay);
        System.out.println("Owner        : " + ownerName);

        if (available) {
            System.out.println("Status       : Available");
        } else {
            System.out.println("Status       : Rented");
        }

        System.out.println("-------------------------");
    }
}

class Rental {

    String equipmentName;
    String farmerName;
    int days;
    double totalAmount;

    Rental(String equipmentName, String farmerName,
           int days, double totalAmount) {

        this.equipmentName = equipmentName;
        this.farmerName = farmerName;
        this.days = days;
        this.totalAmount = totalAmount;
    }

    void displayRental() {

        System.out.println("\n-------------------------");
        System.out.println("Equipment : " + equipmentName);
        System.out.println("Farmer    : " + farmerName);
        System.out.println("Days      : " + days);
        System.out.println("Total     : Rs." + totalAmount);
        System.out.println("Status    : Rented");
        System.out.println("-------------------------");
    }
}

class EquipmentOwner extends User {

    ArrayList<Equipment> equipmentList = new ArrayList<>();

    EquipmentOwner(String name) {
        super(name, "");
    }

    void addEquipment(Equipment equipment) {

        equipmentList.add(equipment);

        System.out.println(
                "Equipment added successfully!"
        );
    }

    void viewMyEquipment() {

        if (equipmentList.size() == 0) {

            System.out.println(
                    "No equipment added."
            );

        } else {

            System.out.println(
                    "\n===== MY EQUIPMENT ====="
            );

            for (Equipment equipment : equipmentList) {
                equipment.displayEquipment();
            }
        }
    }
}

class Farmer extends User {

    ArrayList<Rental> rentals = new ArrayList<>();

    Farmer(String name) {
        super(name, "");
    }

    void rentEquipment(Equipment equipment, int days) {

        if (!equipment.available) {

            System.out.println(
                    "This equipment is already rented."
            );

            return;
        }

        if (days <= 0) {

            System.out.println(
                    "Enter a valid number of days."
            );

            return;
        }

        double total = equipment.rentPerDay * days;

        equipment.available = false;

        Rental rental = new Rental(
                equipment.name,
                name,
                days,
                total
        );

        rentals.add(rental);

        System.out.println(
                "\nEquipment rented successfully!"
        );

        System.out.println(
                "Equipment : " + equipment.name
        );

        System.out.println(
                "Farmer    : " + name
        );

        System.out.println(
                "Days      : " + days
        );

        System.out.println(
                "Total     : Rs." + total
        );
    }

    void viewMyRentals() {

        if (rentals.size() == 0) {

            System.out.println(
                    "No rentals yet."
            );

        } else {

            System.out.println(
                    "\n===== MY RENTALS ====="
            );

            for (Rental rental : rentals) {
                rental.displayRental();
            }
        }
    }
}

public class Agri {

    Scanner scanner = new Scanner(System.in);

    ArrayList<Equipment> equipmentList =
            new ArrayList<>();

    int equipmentId = 1;

    void addEquipment(EquipmentOwner owner) {

        scanner.nextLine();

        System.out.print(
                "Enter equipment name: "
        );

        String name = scanner.nextLine();

        System.out.print(
                "Enter rent per day: "
        );

        double rent = scanner.nextDouble();

        Equipment equipment = new Equipment(
                equipmentId,
                name,
                rent,
                owner.name
        );

        equipmentId++;

        owner.addEquipment(equipment);

        equipmentList.add(equipment);
    }

    void viewEquipment() {

        if (equipmentList.size() == 0) {

            System.out.println(
                    "\nNo equipment available."
            );

            return;
        }

        System.out.println(
                "\n===== AVAILABLE EQUIPMENT ====="
        );

        for (Equipment equipment : equipmentList) {
            equipment.displayEquipment();
        }
    }

    void rentEquipment(Farmer farmer) {

        viewEquipment();

        if (equipmentList.size() == 0) {
            return;
        }

        System.out.print(
                "\nEnter equipment ID: "
        );

        int id = scanner.nextInt();

        Equipment selected = null;

        for (Equipment equipment : equipmentList) {

            if (equipment.equipmentId == id) {

                selected = equipment;

                break;
            }
        }

        if (selected == null) {

            System.out.println(
                    "Equipment not found."
            );

            return;
        }

        System.out.print(
                "Enter number of days: "
        );

        int days = scanner.nextInt();

        farmer.rentEquipment(
                selected,
                days
        );
    }

    void ownerMenu() {

        scanner.nextLine();

        System.out.print(
                "\nEnter equipment owner's name: "
        );

        String ownerName = scanner.nextLine();

        EquipmentOwner owner =
                new EquipmentOwner(ownerName);

        int choice;

        do {

            System.out.println(
                    "\n===== EQUIPMENT OWNER MENU ====="
            );

            System.out.println(
                    "Welcome, " + owner.name
            );

            System.out.println(
                    "1. Add Equipment"
            );

            System.out.println(
                    "2. View My Equipment"
            );

            System.out.println(
                    "3. View All Equipment"
            );

            System.out.println(
                    "4. Back"
            );

            System.out.print(
                    "Enter choice: "
            );

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    addEquipment(owner);
                    break;

                case 2:
                    owner.viewMyEquipment();
                    break;

                case 3:
                    viewEquipment();
                    break;

                case 4:
                    System.out.println(
                            "Going back..."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }

        } while (choice != 4);
    }

    void farmerMenu() {

        scanner.nextLine();

        System.out.print(
                "\nEnter farmer's name: "
        );

        String farmerName = scanner.nextLine();

        Farmer farmer =
                new Farmer(farmerName);

        int choice;

        do {

            System.out.println(
                    "\n===== FARMER MENU ====="
            );

            System.out.println(
                    "Welcome, " + farmer.name
            );

            System.out.println(
                    "1. View Equipment"
            );

            System.out.println(
                    "2. Rent Equipment"
            );

            System.out.println(
                    "3. View My Rentals"
            );

            System.out.println(
                    "4. Back"
            );

            System.out.print(
                    "Enter choice: "
            );

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    viewEquipment();
                    break;

                case 2:
                    rentEquipment(farmer);
                    break;

                case 3:
                    farmer.viewMyRentals();
                    break;

                case 4:
                    System.out.println(
                            "Going back..."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }

        } while (choice != 4);
    }

    public static void main(String[] args) {

        Agri app = new Agri();

        int choice;

        do {

            System.out.println(
                    "\n=============================="
            );

            System.out.println(
                    "    FARM EQUIPMENT RENTAL"
            );

            System.out.println(
                    "=============================="
            );

            System.out.println(
                    "1. Equipment Owner"
            );

            System.out.println(
                    "2. Farmer"
            );

            System.out.println(
                    "3. Exit"
            );

            System.out.print(
                    "Choose your role: "
            );

            choice =
                    app.scanner.nextInt();

            switch (choice) {

                case 1:
                    app.ownerMenu();
                    break;

                case 2:
                    app.farmerMenu();
                    break;

                case 3:
                    System.out.println(
                            "\nThank you for using the system!"
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }

        } while (choice != 3);

        app.scanner.close();
    }
}