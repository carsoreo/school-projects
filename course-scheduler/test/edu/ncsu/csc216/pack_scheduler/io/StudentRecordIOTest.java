package edu.ncsu.csc216.pack_scheduler.io;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc217.collections.list.SortedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Holds test cases for StudentRecordIO.
 * @author Zane Taylor, Olivia Nowell
 */
class StudentRecordIOTest {

    /** The location of a valid test file */
    private static final String VALID_TEST_FILE = "test-files/student_records.txt";
    /** The location of an invalid test file */
    private static final String INVALID_TEST_FILE = "test-files/invalid_student_records.txt";

    /** Valid student record 0 */
    private static final String VALID_STUDENT_6 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
    /** Valid student record 1 */
    private static final String VALID_STUDENT_8 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
    /** Valid student record 2 */
    private static final String VALID_STUDENT_4 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
    /** Valid student record 3 */
    private static final String VALID_STUDENT_0 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
    /** Valid student record 4 */
    private static final String VALID_STUDENT_2 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
    /** Valid student record 5 */
    private static final String VALID_STUDENT_3 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
    /** Valid student record 6 */
    private static final String VALID_STUDENT_1 = "Lane,Berg,lberg,sociis@non.org,pw,14";
    /** Valid student record 7 */
    private static final String VALID_STUDENT_9 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
    /** Valid student record 8 */
    private static final String VALID_STUDENT_5 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
    /** Valid student record 9 */
    private static final String VALID_STUDENT_7 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";

    /** An array of all valid students */
    private final String[] validStudents = {VALID_STUDENT_0, VALID_STUDENT_1, VALID_STUDENT_2, VALID_STUDENT_3,
            VALID_STUDENT_4, VALID_STUDENT_5, VALID_STUDENT_6, VALID_STUDENT_7, VALID_STUDENT_8, VALID_STUDENT_9};

    /** The hashing algorithm to use */
    private static final String HASH_ALGORITHM = "SHA-256";

    /**
     * Changes each student's password with its SHA-256 hash.
     * @author Sarah Heckman
     */
    @BeforeEach
    void setUp() {
        try {
            String password = "pw";
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.update(password.getBytes());
            String hashPW = new String(digest.digest());

            for (int i = 0; i < validStudents.length; i++) {
                validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
            }
        } catch (NoSuchAlgorithmException e) {
            fail("Unable to create hash during setup");
        }
    }

    /**
     * Tests that students can be read in from file and imported in the correct order and with the correct state.
     */
    @Test
    void testReadValidStudentRecords() {
        try {
            SortedList<Student> students = StudentRecordIO.readStudentRecords(VALID_TEST_FILE);
            assertEquals(validStudents.length, students.size(),
                    "Expected read students to have length " + validStudents.length + " but was " + students.size());
            for (int i = 0; i < validStudents.length; i++) {
                User expectedStudent = students.get(i);
                assertEquals(validStudents[i], expectedStudent.toString());
            }
        } catch (FileNotFoundException e) {
            fail("Error reading test file", e);
        }
    }

    /**
     * Tests that invalid student records are skipped without throwing an exception.
     */
    @Test
    void testReadInvalidStudentRecords() {
        try {
            SortedList<Student> students = StudentRecordIO.readStudentRecords(INVALID_TEST_FILE);
            assertEquals(0, students.size(),
                    "Expected invalid students to be empty but had size " + students.size());
        } catch (FileNotFoundException e) {
            fail("Error reading test file", e);
        }
    }

    /**
     * Tests that a FileNotFoundException is thrown when trying to read a file that does not exist.
     */
    @Test
    void testReadNonExistentStudentRecords() {
        assertThrows(FileNotFoundException.class,
                () -> StudentRecordIO.readStudentRecords("does-not-exist.txt"),
                "Expected reading a missing file to throw FileNotFoundException but did not");
    }


}