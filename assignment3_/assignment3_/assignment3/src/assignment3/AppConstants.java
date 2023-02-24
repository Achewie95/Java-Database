package assignment3;

public class AppConstants {

	public static final String FILE_NAME = "src/person.bin";
	public static final int MAX_FIRST_NAME_SIZE = 20; // maximum size of first name string
	public static final int MAX_LAST_NAME_SIZE = 25; // maximum size of last name string
	public static final int MAX_PHONE_SIZE = 10; // maximum size of phone number string
	public static final int INT_RECORD_SIZE = 4;// 4 byte for integer #
	public static final int INT_AGE_SIZE = 4;// 4 byte for integer #

	// For every set of data, we have 63 bytes
	public static final int RECORD_SIZE = INT_RECORD_SIZE + MAX_FIRST_NAME_SIZE + MAX_LAST_NAME_SIZE + MAX_PHONE_SIZE
			+ INT_AGE_SIZE;
}
