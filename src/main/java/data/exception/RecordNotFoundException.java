package data.exception;

public class RecordNotFoundException extends Exception{
        /**
         * @param message should contain relevant information on the failed constraint(s)
         */
        public RecordNotFoundException(String message) {
            super(message);
        }

}
