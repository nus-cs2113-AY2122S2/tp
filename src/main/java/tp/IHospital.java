package tp;

public class IHospital {
    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();

    private static DoctorList doctors = new DoctorList();
    private static PatientList patients = new PatientList();
    private static AppointmentList appointments = new AppointmentList();

    public IHospital() {
    }

    public void run() {
        ui.sayHello();
        String fullCommand = Parser.getCommand();
        Storage storage;

        while (!fullCommand.equals("bye")) {
            try {
                Command c = parser.parse(fullCommand);
                System.out.println(c.execute(doctors, patients, appointments, ui, storage));
                fullCommand = Parser.getCommand();
            } catch (IHospitalException e) {
                System.out.println(e.getMessage());
                fullCommand = Parser.getCommand();
            }
        }
        ui.sayGoodbye();
    }

    public static void main(String[] args) {
        new IHospital().run();
    }

}
