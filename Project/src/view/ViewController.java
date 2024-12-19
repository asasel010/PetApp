package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import model.Customer;
import model.KennelBooking;
import model.Pet;
import model.Sale;
import utils.FileHandler;

import java.io.IOException;
import java.time.LocalDate;
import static java.util.concurrent.TimeUnit.DAYS;

/**
 * The controller class handling mainView.fxml
 * @author Grzegorz Franciszek Frankowicz
 * @author Damian Michal Choina
 * @version 1.0
 * */
public class ViewController extends AnchorPane {
    @FXML
    private Tab KennelTab;
    @FXML
    private Tab SalesTab;
    @FXML
    private Tab PetsTab;
    @FXML
    private TextField phoneNoInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField surnameInput;
    @FXML
    private TextField nameCustomerInput;
    @FXML
    private TableColumn<Customer, Integer> phoneNoCol;
    @FXML
    private TableColumn<Customer, String> emailCol;
    @FXML
    private TableColumn<Customer, String> surnameCol;
    @FXML
    private TableColumn<Customer, String> nameCustomerCol;
    @FXML
    private TableView<Customer> tableC;
    @FXML
    private Tab CustomersTab;
    @FXML
    private TextField commentsInput;
    @FXML
    private TableView<KennelBooking> tableB;
    @FXML
    private TableColumn<Pet, String>  namePetBCol;
    @FXML
    private TableColumn<KennelBooking, String>  speciesBCol;
    @FXML
    private TableColumn<KennelBooking, String>  commentsBCol;
    @FXML
    private TableColumn<KennelBooking, String>  nameCusBCol;
    @FXML
    private TableColumn<KennelBooking, String>  surnameBCol;
    @FXML
    private TableColumn<KennelBooking, String> emailBCol;
    @FXML
    private TableColumn<KennelBooking, Integer>  phoneNoBCol;
    @FXML
    private TableColumn<KennelBooking, LocalDate> dateOfRCol;
    @FXML
    private TableColumn<KennelBooking, LocalDate>  endDateCol;
    @FXML
    private TableColumn<KennelBooking, Float>  pricePDCol;
    @FXML
    private TableColumn<KennelBooking, Float>  finalPriceCol;
    @FXML
    private ComboBox<Customer> customerBComboBox;
    @FXML
    private TextField priceBInput;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TableView<Sale> tableS;
    @FXML
    private TableColumn<Sale, String> petNameSCol;
    @FXML
    private TableColumn<Sale, String> speciesSCol;
    @FXML
    private TableColumn<Sale, String> customerNameSCol;
    @FXML
    private TableColumn<Sale, String>customerSurnameSCol;
    @FXML
    private TableColumn<Sale, LocalDate> saleDateSCol;
    @FXML
    private TableColumn<Sale, Float> priceSCol;
    @FXML
    private ComboBox<Pet> petSComboBox;
    @FXML
    private ComboBox <Customer> customerSComboBox;
    @FXML
    private TextField priceSInput1;
    @FXML
    private DatePicker DatePickerS;
    @FXML
    private TableColumn<Sale, String> customerEmailSCol;
    @FXML
    private TableColumn<Sale, String> customerPhoneSCol;
    @FXML
    private ComboBox<Pet> petBComboBox;
    @FXML
    private TableView<Pet> table;
    @FXML
    private TableColumn<Pet, String> namePetCol;
    @FXML
    private TableColumn<Pet, Integer> ageCol;
    @FXML
    private TableColumn<Pet, String> speciesCol;
    @FXML
    private TableColumn<Pet, String> genderCol;
    @FXML
    private TableColumn<Pet, String> colorCol;
    @FXML
    private TableColumn<Pet, Float> priceCol;
    @FXML
    private TableColumn<Pet, String> detailsCol;
    @FXML
    private TableColumn<Pet, String> commentsCol;
    @FXML
    private TableColumn<Pet, Boolean> isForSaleCol;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField ageInput;
    @FXML
    private TextField speciesInput;
    @FXML
    private TextField genderInput;
    @FXML
    private TextField colorInput;
    @FXML
    private TextField priceInput;
    @FXML
    private TextField detailsInput;
    @FXML
    private CheckBox forSaleInput;
    @FXML
    private TabPane tabPane;

    private ObservableList<Pet> petList;
    private ObservableList<Customer> customerList;
    private ObservableList<KennelBooking> bookingList;
    private ObservableList<Sale> saleList;
    private Tab currentTab;
    private final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private final String phoneRegex = "^\\+?\\d{1,4}?\\d{7,}$";

    /**
     * Initializes the tab pane with the pet tab being the default one
     * */
    @FXML
    public void initialize() {
        tabPane.getSelectionModel().select(PetsTab);
        currentTab = PetsTab;
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab != null) {
                currentTab = newTab;
                initializeTabContent(newTab);
            }
        });
        initializeTabContent(PetsTab);
        initializeTabContent(CustomersTab);
        initializeTabContent(KennelTab);
        initializeTabContent(SalesTab);
    }

    /**
     * Initialize based on which tab is chosen
     * */
    private void initializeTabContent(Tab selectedTab) {
        if (selectedTab.equals(PetsTab)) {
            initializePetsTab();
        } else if (selectedTab.equals(CustomersTab)) {
            initializeCustomersTab();
        } else if (selectedTab.equals(KennelTab)) {
            initializeKennelTab();
        } else if (selectedTab.equals(SalesTab)) {
            initializeSalesTab();
        }
    }

    /**
     * Pets view initialization logic
     * responsible for handling inserting pets into the table and handling inputs
     * */
    private void initializePetsTab() {
        petList = FXCollections.observableArrayList(); //This takes 1
        try {
            petList.addAll(FileHandler.loadPetsFromFile("pets.bin")); //Reads n records
        } catch (IOException e) {
            e.printStackTrace();
        }

        table.setItems(petList);  //All of these take 1
        namePetCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        speciesCol.setCellValueFactory(new PropertyValueFactory<>("species"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        detailsCol.setCellValueFactory(new PropertyValueFactory<>("details"));
        commentsCol.setCellValueFactory(new PropertyValueFactory<>("comments"));
        isForSaleCol.setCellValueFactory(new PropertyValueFactory<>("isForSale"));

        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> { //this takes 1
            if (newValue != null) {
                nameInput.setText(newValue.getName());
                ageInput.setText(String.valueOf(newValue.getAge()));
                speciesInput.setText(newValue.getSpecies());
                genderInput.setText(newValue.getGender());
                colorInput.setText(newValue.getColor());
                priceInput.setText(String.valueOf(newValue.getPrice()));
                detailsInput.setText(newValue.getDetails());
                commentsInput.setText(newValue.getComments());
                forSaleInput.setSelected(newValue.getIsForSale());
            }
        });
        table.setRowFactory(tv -> { //this takes 1
            TableRow<Pet> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (row.isEmpty()) {
                    clearInputs();
                }
            });
            return row;
        });

        priceCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Float price, boolean empty) { //this takes 1
                super.updateItem(price, empty); //this takes 1
                if (empty || price == null) {
                    setText(null); //this takes 1
                } else {
                    Pet currentPet = getTableView().getItems().get(getIndex()); //this takes 1
                    if (currentPet != null && !currentPet.getIsForSale()) { //this takes 1
                        setText("Not for Sale");
                    } else {
                        setText(String.format("%.2f dkk", price));
                    }
                }
            }
        });

        speciesInput.textProperty().addListener((observable, oldValue, newValue) -> { //this takes 1 + k for each speciesInput change
            String placeholderText = switch (newValue.toLowerCase()) {
                case "dog", "cat" -> "Breed: ,Breeder's name:";
                case "fish" -> "Water type: ,Predator: (Yes/No)";
                case "bird" -> "preferred food details";
                default -> "pet's details";
            };
            detailsInput.setPromptText(placeholderText);

        });

        clearInputs(); //this takes 1

        //There is no base case
        //T(n) = n + k + 20
        //Dominating term: O(n)
        //Coefficients: Loading the file takes n, k stands for speciesInput changes and the other steps equal to 20
        //Optimizations: for big files use async loading/other techniques
        //Reason why: First method in the file which made sense to do a Big-O analysis on
    }

    /**
     * Customers view initialization logic
     * responsible for handling inserting customers into the table and handling inputs
     * */
    private void initializeCustomersTab() {
        customerList = FXCollections.observableArrayList();
        try {
            customerList.addAll(FileHandler.loadCustomersFromFile("customers.bin"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        startDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                endDatePicker.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(empty || date.isBefore(newValue.plusDays(1)));
                    }
                });
                if (endDatePicker.getValue() != null && endDatePicker.getValue().isBefore(newValue.plusDays(1))) {
                    endDatePicker.setValue(newValue.plusDays(1));
                }
            }
        });

        tableC.setItems(customerList);
        nameCustomerCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNoCol.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));

        tableC.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nameCustomerInput.setText(newValue.getName());
                surnameInput.setText(String.valueOf(newValue.getSurname()));
                emailInput.setText(newValue.getEmail());
                phoneNoInput.setText(newValue.getPhoneNo());
            }
        });

        tableC.setRowFactory(tv -> {
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (row.isEmpty()) {
                    clearInputs();
                }
            });
            return row;
        });

        clearInputs();
    }

    /**
     * Kennel view initialization logic
     * responsible for handling inserting kennel bookings into the table and handling inputs
     * also loads pets and customers for comboBox input
     * */
    private void initializeKennelTab() {

        petList = FXCollections.observableArrayList();
        try {
            petList.addAll(FileHandler.loadPetsFromFile("pets.bin"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObservableList<Pet> filteredPetList = petList.filtered(pet -> !pet.getIsForSale());
        petBComboBox.setItems(filteredPetList);
        petBComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Pet pet) {
                return pet != null ? pet.getName() + " " + pet.getSpecies(): "";
            }

            @Override
            public Pet fromString(String string) {
                return null;
            }
        });

        customerList = FXCollections.observableArrayList();
        try {
            customerList.addAll(FileHandler.loadCustomersFromFile("customers.bin"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        customerBComboBox.setItems(customerList);
        customerBComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Customer customer) {
                return customer != null ? customer.getName() + " " + customer.getSurname() : "";
            }

            @Override
            public Customer fromString(String string) {
                return null;
            }
        });

        bookingList = FXCollections.observableArrayList();
        try {
            bookingList.addAll(FileHandler.loadKennelFromFile("bookings.bin"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        tableB.setItems(bookingList);
        namePetBCol.setCellValueFactory(new PropertyValueFactory<>("petName"));
        speciesBCol.setCellValueFactory(new PropertyValueFactory<>("species"));
        commentsBCol.setCellValueFactory(new PropertyValueFactory<>("comments"));
        nameCusBCol.setCellValueFactory(new PropertyValueFactory<>("nameC"));
        surnameBCol.setCellValueFactory(new PropertyValueFactory<>("surnameC"));
        emailBCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNoBCol.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        dateOfRCol.setCellValueFactory(new PropertyValueFactory<>("dateOfReservation"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        pricePDCol.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));
        finalPriceCol.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));


        tableB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                petBComboBox.getSelectionModel().select(newValue.getPet());
                customerBComboBox.getSelectionModel().select(newValue.getCustomer());
                startDatePicker.setValue(newValue.getDateOfReservation());
                endDatePicker.setValue(newValue.getEndDate());
                priceBInput.setText(String.valueOf(newValue.getPricePerDay()));
            }
        });
        tableB.setRowFactory(tv -> {
            TableRow<KennelBooking> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (row.isEmpty()) {
                    clearInputs();
                }
            });
            return row;
        });
        pricePDCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Float price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    KennelBooking currentBooking = getTableView().getItems().get(getIndex());
                    if (currentBooking != null && currentBooking.getPricePerDay() == 0) {
                        setText("No charge");
                    } else {
                        setText(String.format("%.2f DKK", price));
                    }
                }
            }
        });

        finalPriceCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Float price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    KennelBooking currentBooking = getTableView().getItems().get(getIndex());
                    if (currentBooking != null && currentBooking.getFinalPrice() == 0) {
                        setText("No Charge");
                    } else {
                        setText(String.format("%.2f DKK", price));
                    }
                }
            }
        });

        clearInputs();
    }

    /**
     * Sales view initialization logic
     * responsible for handling inserting sales into the table and handling inputs
     * also loads pets and customers for comboBox input
     * */
    private void initializeSalesTab() {
        petList = FXCollections.observableArrayList();
        try {
            petList.addAll(FileHandler.loadPetsFromFile("pets.bin"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObservableList<Pet> filteredPetList = petList.filtered(Pet::getIsForSale);
        petSComboBox.setItems(filteredPetList);
        petSComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Pet pet) {
                return pet != null ? pet.getName() + " " + pet.getSpecies() : "";
            }

            @Override
            public Pet fromString(String string) {
                return null;
            }
        });

        customerList = FXCollections.observableArrayList();
        try {
            customerList.addAll(FileHandler.loadCustomersFromFile("customers.bin"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        customerSComboBox.setItems(customerList);
        customerSComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Customer customer) {
                return customer != null ? customer.getName() + " " + customer.getSurname() : "";
            }

            @Override
            public Customer fromString(String string) {
                return null;
            }
        });

        saleList = FXCollections.observableArrayList();
        try {
            saleList.addAll(FileHandler.loadSalesFromFile("sales.bin"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableS.setItems(saleList);
        petNameSCol.setCellValueFactory(new PropertyValueFactory<>("petName"));
        speciesSCol.setCellValueFactory(new PropertyValueFactory<>("species"));
        customerNameSCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerSurnameSCol.setCellValueFactory(new PropertyValueFactory<>("customerSurname"));
        customerEmailSCol.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
        customerPhoneSCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        saleDateSCol.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        priceSCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableS.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                petSComboBox.getSelectionModel().select(newValue.getPet());
                customerSComboBox.getSelectionModel().select(newValue.getCustomer());
                DatePickerS.setValue(newValue.getDateOfSale());
                priceInput.setText(String.valueOf(newValue.getPrice()));
            }
        });
        tableS.setRowFactory(tv -> {
            TableRow<Sale> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (row.isEmpty()) {
                    clearInputs();
                }
            });
            return row;
        });
        priceSCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Float price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    Sale currentSale = getTableView().getItems().get(getIndex());
                    if (currentSale != null && currentSale.getPrice() == 0) {
                        setText("Free");
                    } else {
                        setText(String.format("%.2f DKK", price));
                    }
                }
            }
        });
    }

    /**
     * Addition handler - works after mouseClickEvent on the add button
     * based on the current tab checks for the inputs and creates an according object
     * */
    @FXML
    private void handleAdd() {
        if (currentTab.equals(PetsTab)) {
            if (areInputsValid()) {
                try {
                    String name = nameInput.getText();
                    int age = Integer.parseInt(ageInput.getText());
                    String species = speciesInput.getText();
                    String gender = genderInput.getText();
                    String color = colorInput.getText();
                    float price = Float.parseFloat(priceInput.getText());
                    String details = detailsInput.getText();
                    String comments = commentsInput.getText();
                    boolean isForSale = forSaleInput.isSelected();
                    if (species.equalsIgnoreCase("dog") || species.equalsIgnoreCase("cat")){
                        String[] parts = details.split(",");
                        if (!details.contains(",")){
                            showAlert("Please details format", "Your details field must contain a comma");
                            return;
                        }else {
                            details = "Breed: " + parts[0].trim() + " Breeder's name: " + parts[1].trim();
                        }
                    } else if (species.equalsIgnoreCase("fish")) {
                        String[] parts = details.split(",");
                        if (!details.contains(",")) {
                            showAlert("Please details format", "Your details field must contain a comma");
                            return;
                        }else {
                            details = "Water: " + parts[0].trim() + " Predator: " + parts[1].trim();
                        }
                    } else if (species.equalsIgnoreCase("bird")) {
                        details = "Preferred food: " + details;
                    }
                    Pet newPet = new Pet(name, age, species, gender, color, price, details, comments, isForSale);

                    for (Pet pet : petList){
                        if (pet.equalsWithoutPrice(newPet)){
                            showAlert("Duplicate Pet", "A pet with the same information already exists in the system.");
                            return;
                        }
                    }

                    petList.add(newPet);
                    FileHandler.savePetsToFile("pets.bin", petList);
                    clearInputs();
                    showAlert("Pet added", name + " has been added");
                    table.refresh();

                } catch (NumberFormatException e) {
                    showAlert("Invalid Input", "Please enter valid numeric values for Age and Price.");
                } catch (IOException e) {
                    showAlert("File Error", "Something went wrong when saving to file.");
                    e.printStackTrace();
                }
            } else {
                showAlert("Incomplete Form", "Please fill in all fields before adding a pet.");
            }
        }
        else if (currentTab.equals(CustomersTab)) {
            if (areInputsValid()) {
                try {
                    String name = nameCustomerInput.getText();
                    String surname = surnameInput.getText();
                    String email = emailInput.getText();
                    String phoneNumber = phoneNoInput.getText();
                    if (!email.matches(emailRegex) && !phoneNumber.matches(phoneRegex)){
                        showAlert("Invalid email and phone number format", "Provide the correct email and phone number format");
                        return;
                    } else if (!phoneNumber.matches(phoneRegex)) {
                        showAlert("Invalid phone number format", "Provide the correct phone number format");
                        return;
                    } else if (!email.matches(emailRegex)) {
                        showAlert("Invalid email format", "Provide the correct email format");
                        return;
                    }else {
                        Customer newCustomer = new Customer(name, surname, email, phoneNumber);
                        for (Customer customer : customerList){
                            if (customer.equals(newCustomer)){
                                showAlert("Duplicate Customer", "A customer with the same information already exists in the system.");
                                return;
                            }
                        }
                        customerList.add(newCustomer);
                        FileHandler.saveCustomersToFile("customers.bin", customerList);
                        clearInputs();
                        showAlert("Customer added", name + " has been added");
                        tableC.refresh();
                    }

                } catch (NumberFormatException e) {
                    showAlert("Invalid Input", "Please enter valid numeric value for the number");
                } catch (IOException e) {
                    showAlert("File Error", "Something went wrong when saving to file.");
                    e.printStackTrace();
                }
            } else {
                showAlert("Incomplete Form", "Please fill in all fields before adding a pet.");
            }
        }
        else if (currentTab.equals(KennelTab)) {
            if (areInputsValid()) {
                try {
                    Customer customer = customerBComboBox.getValue();
                    Pet pet = petBComboBox.getValue();
                    LocalDate startDate = startDatePicker.getValue();
                    LocalDate endDate = endDatePicker.getValue();
                    float price = Float.parseFloat(priceBInput.getText());
                    float finalPrice = Float.parseFloat(priceBInput.getText()) * (DAYS.toChronoUnit().between(startDate, endDate)+1);

                    if (bookingList.size() >= 10){
                        showAlert("Too many bookings", "You have too many bookings, you have to remove ");
                    }else {

                        KennelBooking newBooking = new KennelBooking(customer, pet, startDate, endDate, price, finalPrice);

                        for (KennelBooking booking : bookingList) {
                            if (booking.equalsWithoutPrice(newBooking)) {
                                showAlert("Duplicate booking", "A booking with the same information already exists in the system.");
                                return;
                            }
                        }

                        bookingList.add(newBooking);
                        FileHandler.saveBookingsToFile("bookings.bin", bookingList);
                        clearInputs();
                        showAlert("Booking added", pet.getName() + "'s booking has been added");
                        tableB.refresh();
                    }

                } catch (NumberFormatException e) {
                    showAlert("Invalid Input", "Please enter valid numeric value for the number");
                } catch (IOException e) {
                    showAlert("File Error", "Something went wrong when saving to file.");
                    e.printStackTrace();
                }
            } else {
                showAlert("Incomplete Form", "Please fill in all fields before adding a pet.");
            }
        } else if (currentTab.equals(SalesTab)) {
            if (areInputsValid()) {
                try {
                    Pet selectedPet = petSComboBox.getValue();
                    Customer selectedCustomer = customerSComboBox.getValue();
                    LocalDate saleDate = DatePickerS.getValue();
                    float salePrice = Float.parseFloat(priceSInput1.getText());

                    if (selectedPet == null || selectedCustomer == null) {
                        showAlert("Missing Selection", "Please select both a pet and a customer for the sale.");
                        return;
                    }

                    Sale newSale = new Sale(selectedPet, selectedCustomer, saleDate, salePrice);
                    for (Sale sale : saleList){
                        if (sale.equalsWithoutPrice(newSale)){
                            showAlert("Duplicate sale", "A sale with the same information already exists in the system.");
                            return;
                        }
                    }
                    saleList.add(newSale);
                    clearInputs();
                    FileHandler.saveSalesToFile("sales.bin", saleList);
                    showAlert("Sale added", "Sale for " + selectedPet.getName() + " has been added.");
                    tableC.refresh();

                } catch (NumberFormatException e) {
                    showAlert("Invalid Input", "Please enter valid numeric values for Sale Price.");
                } catch (IOException e) {
                    showAlert("File Error", "Something went wrong when saving to file.");
                    e.printStackTrace();
                }
            } else {
                showAlert("Incomplete Form", "Please fill in all fields before adding a sale.");
            }

        }
        clearInputs();

    }
    /**
     * Edition handler - works after mouseClickEvent on the edit button
     * based on the current tab sets the data from the inputs into the selected object
     * */
    @FXML
    private void handleEdit() {
        if (currentTab.equals(PetsTab)) { //this takes 1
            Pet selectedPet = table.getSelectionModel().getSelectedItem(); //this takes 1
            if (selectedPet != null) { //this takes 1

                for (Pet pet : petList) { //Reads n records
                    if (!pet.equals(selectedPet) &&
                            pet.equalsWithoutPrice(new Pet(
                                    nameInput.getText(),
                                    ageInput.getText().matches("\\d+") ? Integer.parseInt(ageInput.getText()) : selectedPet.getAge(),
                                    speciesInput.getText(),
                                    genderInput.getText(),
                                    colorInput.getText(),
                                    priceInput.getText().matches("\\d+(\\.\\d+)?") ? Float.parseFloat(priceInput.getText()) : selectedPet.getPrice(),
                                    detailsInput.getText(),
                                    commentsInput.getText(),
                                    forSaleInput.isSelected()
                            ))) {
                        showAlert("Duplicate Pet", "A pet with the same information already exists."); //this takes 1
                        return;
                    }
                }

                if (!nameInput.getText().isEmpty()) { //this takes 1
                    selectedPet.setName(nameInput.getText());
                }
                if (!ageInput.getText().isEmpty()) { //this takes 1
                    try {
                        selectedPet.setAge(Integer.parseInt(ageInput.getText())); //this takes 1
                    } catch (NumberFormatException e) {
                        showAlert("Invalid Age", "Please enter a valid number for age."); //this takes 1
                        return;
                    }
                }
                if (!speciesInput.getText().isEmpty()) { //this takes 1
                    selectedPet.setSpecies(speciesInput.getText());
                }
                if (!genderInput.getText().isEmpty()) { //this takes 1
                    selectedPet.setGender(genderInput.getText());
                }
                if (!colorInput.getText().isEmpty()) { //this takes 1
                    selectedPet.setColor(colorInput.getText());
                }
                if (!priceInput.getText().isEmpty()) { //this takes 1
                    try {
                        selectedPet.setPrice(Float.parseFloat(priceInput.getText()));  //this takes 1
                    } catch (NumberFormatException e) {
                        showAlert("Invalid Price", "Please enter a valid number for a price."); //this takes 1
                        return;
                    }
                }
                if (!detailsInput.getText().isEmpty()) { //this takes 1
                    selectedPet.setDetails(detailsInput.getText());
                }

                if (!commentsInput.getText().isEmpty()) { //this takes 1
                    selectedPet.setComments(commentsInput.getText());
                }
                selectedPet.setForSale(forSaleInput.isSelected()); //this takes 1

                table.refresh(); //this takes 1
                for (KennelBooking booking : bookingList) { //this takes n - where n is the length of bookingList
                    if (booking.getPet().getID() == selectedPet.getID()) {
                        booking.setPet(selectedPet);
                    }
                }

                for (Sale sale : saleList) { //this takes n - where n is the length of saleList
                    if (sale.getPet().getID() == selectedPet.getID()) {
                        sale.setPet(selectedPet);
                    }
                }

                try {
                    FileHandler.savePetsToFile("pets.bin", petList); //these take n - where n is the length of the respective lists
                    FileHandler.saveBookingsToFile("bookings.bin", bookingList);
                    FileHandler.saveSalesToFile("sales.bin", saleList);
                } catch (IOException e) {
                    showAlert("File Error", "Something went wrong when saving to file."); //this takes 1
                    e.printStackTrace(); //this takes 1
                }
            } else {
                showAlert("No Selection", "Please select a pet to edit."); //this takes 1
            }
        } else if (currentTab.equals(CustomersTab)) { //this takes 1
            Customer selectedCustomer = tableC.getSelectionModel().getSelectedItem(); //this takes 1
            if (selectedCustomer != null) { //this takes 1
                for (Customer customer : customerList){ //reads n records
                    if (!customer.equals(selectedCustomer) && customer.equals(new Customer(
                            nameCustomerInput.getText(),
                            surnameInput.getText(),
                            emailInput.getText(),
                            phoneNoInput.getText()
                    ))){
                        showAlert("Duplicate Customer", "A customer with the same information already exists."); //this takes 1
                        return;
                    };
                }
                if (!nameCustomerInput.getText().isEmpty()) { //this takes 1
                    selectedCustomer.setName(nameCustomerInput.getText());
                }
                if (!surnameInput.getText().isEmpty()) { //this takes 1
                    selectedCustomer.setSurname(surnameInput.getText());
                }
                if (!emailInput.getText().isEmpty()) { //this takes 1
                    if (!emailInput.getText().matches(emailRegex)){ //this takes 1
                        showAlert("Invalid email format", "Provide correct email format"); //this takes 1
                        return;
                    }else {
                        selectedCustomer.setEmail(emailInput.getText()); //this takes 1
                    }
                }
                if (!phoneNoInput.getText().isEmpty()) { //this takes 1
                    try {
                        if (!phoneNoInput.getText().matches(phoneRegex)){ //this takes 1
                            showAlert("Invalid phone number format", "Correct you phone number format"); //this takes 1
                        }
                        else {
                            selectedCustomer.setPhoneNo(phoneNoInput.getText()); //this takes 1
                        }
                    } catch (NumberFormatException e) {
                        showAlert("Invalid phone number", "Please enter a valid phone number."); //this takes 1
                        return;
                    }
                }

                tableC.refresh(); //this takes 1
                for (KennelBooking booking : bookingList) { //this takes n - where n is the length of bookingList
                    if (booking.getCustomer().getID() == selectedCustomer.getID()) {
                        booking.setCustomer(selectedCustomer);
                    }
                }

                for (Sale sale : saleList) { //this takes n - where n is the length of saleList
                    if (sale.getCustomer().getID() == selectedCustomer.getID()) {
                        sale.setCustomer(selectedCustomer);
                    }
                }

                try {
                    FileHandler.saveCustomersToFile("customers.bin", customerList); //this takes n - where n is the length of the respective lists
                    FileHandler.saveBookingsToFile("bookings.bin", bookingList);
                    FileHandler.saveSalesToFile("sales.bin", saleList);
                } catch (IOException e) {
                    showAlert("File Error", "Something went wrong when saving to file."); //this takes 1
                    e.printStackTrace(); //this takes 1
                }
            } else {
                showAlert("No Selection", "Please select a customer to edit."); //this takes 1
            }
        } else if (currentTab.equals(KennelTab)) { //this takes 1
            KennelBooking selectedBooking = tableB.getSelectionModel().getSelectedItem(); //this takes 1
            for (KennelBooking booking : bookingList){ //reads n records
                if (!booking.equals(selectedBooking) && booking.equalsWithoutPrice(new KennelBooking(
                        customerBComboBox.getValue(),
                        petBComboBox.getValue(),
                        startDatePicker.getValue(),
                        endDatePicker.getValue(),
                        priceInput.getText().matches("\\d+(\\.\\d+)?") ? Float.parseFloat(priceInput.getText()) : selectedBooking.getPricePerDay(),
                        Float.parseFloat(priceBInput.getText()) * (DAYS.toChronoUnit().between(startDatePicker.getValue(), endDatePicker.getValue())+1)
                ))){
                    showAlert("Duplicate Booking", "A booking with the same information already exists."); //this takes 1
                    return;
                };
            }
            if (selectedBooking != null) { //this takes 1
                if (petBComboBox.getValue() != null) { //this takes 1
                    selectedBooking.setPet(petBComboBox.getValue());
                }
                if (customerBComboBox.getValue() != null) { //this takes 1
                    selectedBooking.setCustomer(customerBComboBox.getValue());
                }

                if (startDatePicker.getValue() != null) { //this takes 1
                    selectedBooking.setDateOfReservation(startDatePicker.getValue());
                }
                if (endDatePicker.getValue() != null) { //this takes 1
                    selectedBooking.setEndDate(endDatePicker.getValue());
                }
                if (!priceBInput.getText().isEmpty()) { //this takes 1
                    try {
                        selectedBooking.setPricePerDay(Float.parseFloat(priceBInput.getText())); //this takes 1
                        selectedBooking.setFinalPrice(Float.parseFloat(priceBInput.getText()) * (DAYS.toChronoUnit().between(startDatePicker.getValue(), endDatePicker.getValue())+1)); //this takes 1
                    } catch (NumberFormatException e) {
                        showAlert("Invalid Price", "Please enter a valid number for price per day."); //this takes 1
                        return;
                    }
                }

                tableB.refresh(); //this takes 1

                try {
                    FileHandler.saveBookingsToFile("bookings.bin", bookingList); //this takes n - where n is the length of the booking list
                } catch (IOException e) {
                    showAlert("File Error", "Something went wrong when saving to file."); //this takes 1
                    e.printStackTrace(); //this takes 1
                }
            } else {
                showAlert("No Selection", "Please select a booking to edit."); //this takes 1
            }
        } else if (currentTab.equals(SalesTab)) { //this takes 1
            Sale selectedSale = tableS.getSelectionModel().getSelectedItem(); //this takes 1

            if (selectedSale != null) { //this takes 1
                for (Sale sale : saleList){ //reads n records
                    if (!sale.equals(selectedSale) && sale.equalsWithoutPrice(new Sale(
                            petSComboBox.getValue(),
                            customerSComboBox.getValue(),
                            DatePickerS.getValue(),
                            priceSInput1.getText().matches("\\d+(\\.\\d+)?") ? Float.parseFloat(priceSInput1.getText()) : selectedSale.getPrice()

                    ))){
                        showAlert("Duplicate Sale", "A sale with the same information already exists."); //this takes 1
                        return;
                    };
                }

                if (petSComboBox.getValue() != null) { //this takes 1
                    selectedSale.setPet(petSComboBox.getValue());
                }
                if (customerSComboBox.getValue() != null) { //this takes 1
                    selectedSale.setCustomer(customerSComboBox.getValue());
                }

                if (DatePickerS.getValue() != null) { //this takes 1
                    selectedSale.setDateOfSale(DatePickerS.getValue());
                }

                if (!priceSInput1.getText().isEmpty()) { //this takes 1
                    try {
                        selectedSale.setPrice(Float.parseFloat(priceSInput1.getText())); //this takes 1
                    } catch (NumberFormatException e) {
                        showAlert("Invalid Price", "Please enter a valid number for the price."); //this takes 1
                        return;
                    }
                }

                tableS.refresh(); //this takes 1

                try {
                    FileHandler.saveSalesToFile("sales.bin", saleList); //this takes n - where n is the length of saleList
                } catch (IOException e) {
                    showAlert("File Error", "Something went wrong when saving to file.");
                    e.printStackTrace();
                }
            } else {
                showAlert("No Selection", "Please select a sale to edit.");
            }
        }
        //There is no base case
        //T(n) = 10n + 67
        //Dominating term: O(n)
        //Coefficients: 10n stands for each list operation and 67 are mainly input checkers
        //Optimizations: idk
        //Reason why: Most advanced method we have
    }

    /**
     * Removal handler - works after mouseClickEvent on the remove button
     * based on the current tab deletes the selected object
     * */
    @FXML
    private void handleRemove() {
        if (currentTab.equals(PetsTab)) {
            Pet selectedPet = table.getSelectionModel().getSelectedItem();

            if (selectedPet != null) {
                boolean confirmed = showConfirmationAlert("Are you sure you want to remove this pet? This action cannot be undone.");
                if (confirmed) {
                    bookingList.removeIf(booking -> booking.getPet().equals(selectedPet));
                    saleList.removeIf(sale -> sale.getPet().equals(selectedPet));

                    petList.remove(selectedPet);
                    table.refresh();
                    try {
                        FileHandler.savePetsToFile("pets.bin", petList);
                        FileHandler.saveBookingsToFile("bookings.bin", bookingList);
                        FileHandler.saveSalesToFile("sales.bin", saleList);
                    } catch (IOException e) {
                        showAlert("File Error", "Something went wrong when saving to file.");
                        e.printStackTrace();
                    }
                }
            } else {
                showAlert("No Selection", "Please select a pet to remove.");
            }
        }
        else if (currentTab.equals(CustomersTab)) {
            Customer selectedCustomer = tableC.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {
                boolean confirmed = showConfirmationAlert("Are you sure you want to remove this customer? This action cannot be undone.");
                if (confirmed) {
                    bookingList.removeIf(booking -> booking.getCustomer().equals(selectedCustomer));
                    saleList.removeIf(sale -> sale.getCustomer().equals(selectedCustomer));

                    customerList.remove(selectedCustomer);
                    tableC.refresh();
                    try {
                        FileHandler.saveCustomersToFile("customers.bin", customerList);
                        FileHandler.saveBookingsToFile("bookings.bin", bookingList);
                        FileHandler.saveSalesToFile("sales.bin", saleList);
                    } catch (IOException e) {
                        showAlert("File Error", "Something went wrong when saving to file.");
                        e.printStackTrace();
                    }
                }
            } else {
                showAlert("No Selection", "Please select a customer to remove.");
            }
        }
        else if (currentTab.equals(KennelTab)) {
            KennelBooking selectedBooking = tableB.getSelectionModel().getSelectedItem();
            if (selectedBooking != null) {
                boolean confirmed = showConfirmationAlert("Are you sure you want to remove this booking? This action cannot be undone.");
                if (confirmed) {
                    bookingList.remove(selectedBooking);
                    tableB.refresh();
                    try {
                        FileHandler.saveBookingsToFile("bookings.bin", bookingList);
                    } catch (IOException e) {
                        showAlert("File Error", "Something went wrong when saving to file.");
                        e.printStackTrace();
                    }
                }
            } else {
                showAlert("No Selection", "Please select a booking to remove.");
            }

        } else if (currentTab.equals(SalesTab)) {
            Sale selectedSale = tableS.getSelectionModel().getSelectedItem();
            if (selectedSale != null) {
                boolean confirmed = showConfirmationAlert("Are you sure you want to remove this sale? This action cannot be undone.");
                if (confirmed) {
                    saleList.remove(selectedSale);
                    tableS.refresh();
                    try {
                        FileHandler.saveSalesToFile("sales.bin", saleList);
                    } catch (IOException e) {
                        showAlert("File Error", "Something went wrong when saving to file.");
                        e.printStackTrace();
                    }
                }
            } else {
                showAlert("No Selection", "Please select a sale record to remove.");
            }
        }
    }

    /**
     * Checks if the inputs have a proper value
     * function created to simplify if-statements in the addition handler
     * @return boolean value, returning true only for when all the inputs are valid
     * */
    private boolean areInputsValid() {
        if (currentTab.equals(PetsTab)) {
            return  !ageInput.getText().isEmpty() &&
                    !speciesInput.getText().isEmpty() &&
                    !genderInput.getText().isEmpty() &&
                    !colorInput.getText().isEmpty() &&
                    !priceInput.getText().isEmpty() &&
                    !detailsInput.getText().isEmpty() &&
                    !commentsInput.getText().isEmpty();
        } else if (currentTab.equals(CustomersTab)) {
            return !nameCustomerInput.getText().isEmpty() &&
                    !surnameInput.getText().isEmpty() &&
                    !emailInput.getText().isEmpty() &&
                    !phoneNoInput.getText().isEmpty();
        } else if (currentTab.equals(KennelTab)) {
            return petBComboBox.getValue() != null &&
                    customerBComboBox.getValue() != null &&
                    !priceBInput.getText().isEmpty() &&
                    startDatePicker.getValue() != null &&
                    endDatePicker.getValue() != null;
        } else if (currentTab.equals(SalesTab)) {
            return petSComboBox.getValue() != null &&
                    customerSComboBox.getValue() != null &&
                    !priceSInput1.getText().isEmpty() &&
                    DatePickerS.getValue() != null;
        }
        return true;
    }

    /**
     * Clears inputs - works after clicking the clear button
     * */
    @FXML
    private void clearInputs() {
        if (currentTab.equals(PetsTab)) {
            nameInput.clear();
            ageInput.clear();
            speciesInput.clear();
            genderInput.clear();
            colorInput.clear();
            priceInput.clear();
            commentsInput.clear();
            detailsInput.clear();
        }
        else if (currentTab.equals(CustomersTab)) {
            nameCustomerInput.clear();
            surnameInput.clear();
            emailInput.clear();
            phoneNoInput.clear();
        }
        else if (currentTab.equals(KennelTab)) {
            petBComboBox.setValue(null);
            customerBComboBox.setValue(null);
            startDatePicker.setValue(null);
            endDatePicker.setValue(null);
            priceBInput.clear();
        } else if (currentTab.equals(SalesTab)) {
            petSComboBox.setValue(null);
            customerSComboBox.setValue(null);
            DatePickerS.setValue(null);
            priceSInput1.clear();
        }
    }

    /**
     * Alert handler - creates an alert with the inserted information
     * @param title the title of the alert
     * @param message the message of the alert
     * */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Alert handler - creates a confirmation alert with the inserted information
     *
     * @param message the message of the confirmation alert
     * @return boolean value, returning true if the alert was confirmed
     */
    private boolean showConfirmationAlert(String message) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Removal");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText(message);

        ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);
        return result == ButtonType.OK;
    }
}