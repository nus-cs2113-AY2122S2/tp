package tp;

import tp.person.Nurse;

import java.util.ArrayList;

public class NurseList {
        public static String boundary = "____________________________________________________________"
                + System.lineSeparator();
        protected ArrayList<Nurse> nurses = new ArrayList<>();
        protected int size = 0;

        public NurseList() {
            size = 0;
        }

        public Nurse getNurse(int index) {
            return nurses.get(index - 1);
        }

        /**
         * Add a nurse to the list.
         *
         * @param nurse Nurse to be added.
         * @throws IHospitalException If ID of the nurse is the same as other nurses.
         */
        public void addNurse(Nurse nurse) throws IHospitalException {
            boolean isDuplicate = false;
            for (int i = 0; i < nurses.size(); i++) {
                if (nurses.get(i).getId().trim().equals(nurse.getId())) {
                    isDuplicate = true;
                }
            }

            if (isDuplicate) {
                throw new IHospitalException("Nurse with this ID already exists.\n");
            } else {
                nurses.add(nurse);
                size++;
            }
        }

        public int getSize() {
            return size;
        }

        public Nurse deleteNurse(int index) {
            Nurse cur = nurses.get(index - 1);
            size -= 1;
            return cur;
        }

        /**
         * Search for the nurse with a given ID.
         *
         * @param id ID of the nurse to find.
         * @return Nurse with the ID given.
         */
        public Nurse searchNurse(String id) {
            for (int i = 0; i < nurses.size(); i++) {
                if (nurses.get(i).getId().trim().equals(id)) {
                    return nurses.get(i);
                }
            }
            return null;
        }

        public Nurse searchNurseByName(String name) {
        for (int i = 0; i < nurses.size(); i++) {
            if (nurses.get(i).getName().trim().equals(name)) {
                return nurses.get(i);
                }
            }
            return null;
        }

        @Override
        public String toString() {
            String toPrint = boundary + "Here are the nurses in this hospital:" + System.lineSeparator();
            for (int i = 1; i <= size; i++) {
                toPrint += (i + ". " + getNurse(i) + System.lineSeparator());

            }
            toPrint += ("You have " + size + " nurses recorded in the system."
                    + System.lineSeparator() + boundary);
            return toPrint;
        }

}
