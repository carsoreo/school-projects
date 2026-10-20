package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

class FacultyRecordIOTest {

	/** The location of a valid test file */
    private static final String VALID_TEST_FILE = "test-files/faculty_records.txt";
    /** The location of an invalid test file */
    private static final String INVALID_TEST_FILE = "test-files/invalid_faculty_records.txt";

    /** Valid faculty record 0 */
    private static final String VALID_FACULTY_1 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";
	/** Valid faculty record 1 */
    private static final String VALID_FACULTY_2 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";
	/** Valid faculty record 2 */
    private static final String VALID_FACULTY_3 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";
	/** Valid faculty record 3 */
    private static final String VALID_FACULTY_4 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,pw,3";
	/** Valid faculty record 4 */
    private static final String VALID_FACULTY_5 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,pw,1";
	/** Valid faculty record 5 */
    private static final String VALID_FACULTY_6 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,pw,3";
	/** Valid faculty record 6 */
    private static final String VALID_FACULTY_7 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,pw,1";
	/** Valid faculty record 7 */
    private static final String VALID_FACULTY_8 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,pw,2";

    /** An array of all valid facultys */
    private final String[] validFacultys = {VALID_FACULTY_1, VALID_FACULTY_2, VALID_FACULTY_3,
            VALID_FACULTY_4, VALID_FACULTY_5, VALID_FACULTY_6, VALID_FACULTY_7, VALID_FACULTY_8};

    /** The hashing algorithm to use */
    private static final String HASH_ALGORITHM = "SHA-256";

    /**
     * Changes each faculty's password with its SHA-256 hash.
     * @author Sarah Heckman
     */
    @BeforeEach
    void setUp() {
        try {
            String password = "pw";
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.update(password.getBytes());
            String hashPW = new String(digest.digest());

            for (int i = 0; i < validFacultys.length; i++) {
                validFacultys[i] = validFacultys[i].replace(",pw,", "," + hashPW + ",");
            }
        } catch (NoSuchAlgorithmException e) {
            fail("Unable to create hash during setup");
        }
    }

    /**
     * Tests that facultys can be read in from file and imported in the correct order and with the correct state.
     */
    @Test
    void testReadValidfacultyRecords() {
        try {
            LinkedList<Faculty> facultys = FacultyRecordIO.readFacultyRecords(VALID_TEST_FILE);
            assertEquals(8, facultys.size());
        } catch (FileNotFoundException e) {
            fail("Error reading test file", e);
        }
    }

    /**
     * Tests that invalid faculty records are skipped without throwing an exception.
     */
    @Test
    void testReadInvalidfacultyRecords() {
        try {
            LinkedList<Faculty> facultys = FacultyRecordIO.readFacultyRecords(INVALID_TEST_FILE);
            assertEquals(0, facultys.size(),
                    "Expected invalid facultys to be empty but had size " + facultys.size());
        } catch (FileNotFoundException e) {
            fail("Error reading test file", e);
        }
    }

    /**
     * Tests that a FileNotFoundException is thrown when trying to read a file that does not exist.
     */
    @Test
    void testReadNonExistentfacultyRecords() {
        assertThrows(FileNotFoundException.class,
                () -> FacultyRecordIO.readFacultyRecords("does-not-exist.txt"),
                "Expected reading a missing file to throw FileNotFoundException but did not");
    }

}
