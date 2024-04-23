/*
 * Created by Bawb
 *
 *
 */
package webserver.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import webserver.WebServer;
import webserver.posts.*;

/**
 *
 * @author MeanC
 */
public final class DataManager {

    private static void putPatients() {
        WebServer.dbHandler.securePost("TRUNCATE TABLE `Patients`", null);

        SubmitPatientPost sdp = new SubmitPatientPost();
        sdp.enteredDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        for (String name : RandomData.MALE_NAMES) {
            sdp.firstName = name.split(" ")[0];
            sdp.lastName = name.split(" ")[1];
            sdp.primaryLanguage = RandomData.PRIMARY_LANGUAGES[randomInt(0, RandomData.PRIMARY_LANGUAGES.length-1)];
            int areaCode = randomInt(100, 999);
            sdp.phoneNumber = String.format("%d %d %d", areaCode, randomInt(100, 999), randomInt(1000, 9999));
            sdp.emergencyPhone = String.format("%d %d %d", areaCode, randomInt(100, 999), randomInt(1000, 9999));
            sdp.dob = randomDate(1950, 1990);
            sdp.streetAddress = String.format("%d %s", randomInt(100, 9999), RandomData.STREETS[randomInt(0, RandomData.STREETS.length-1)]);
            sdp.city = RandomData.CITIES[randomInt(0, RandomData.CITIES.length-1)];
            sdp.state = RandomData.STATES[randomInt(0, RandomData.STATES.length-1)];
            sdp.zip = randomInt(10000, 99999);
            sdp.sex = RandomData.SEXES[0];
            sdp.ethnicity = RandomData.ETHNICITIES[randomInt(0, RandomData.ETHNICITIES.length-1)];
            sdp.race = RandomData.RACES[randomInt(0, RandomData.RACES.length-1)];
            sdp.pharmacy = RandomData.PHARMACY_NAMES[randomInt(0, RandomData.PHARMACY_NAMES.length-1)];
            sdp.insurance = RandomData.HEALTH_INSURANCE_NAMES[randomInt(0, RandomData.HEALTH_INSURANCE_NAMES.length-1)];
            sdp.insuranceId = Integer.toString(randomInt(0, Integer.MAX_VALUE-1));
            sdp.ssn = Integer.toString(randomInt(100000000, 999999999));
            sdp.enteredBy = randomInt(1, RandomData.STAFF_NAMES.length);
            sdp.maritalStatus = RandomData.MARITAL_STATUSES[randomInt(0, RandomData.MARITAL_STATUSES.length-1)];
            // enteredDate done.
            sdp.publish();
        }

        for (String name : RandomData.FEMALE_NAMES) {
            sdp.firstName = name.split(" ")[0];
            sdp.lastName = name.split(" ")[1];
            sdp.primaryLanguage = RandomData.PRIMARY_LANGUAGES[randomInt(0, RandomData.PRIMARY_LANGUAGES.length-1)];
            int areaCode = randomInt(100, 999);
            sdp.phoneNumber = String.format("%d %d %d", areaCode, randomInt(100, 999), randomInt(1000, 9999));
            sdp.emergencyPhone = String.format("%d %d %d", areaCode, randomInt(100, 999), randomInt(1000, 9999));
            sdp.dob = randomDate(1950, 1990);
            sdp.streetAddress = String.format("%d %s", randomInt(100, 9999), RandomData.STREETS[randomInt(0, RandomData.STREETS.length-1)]);
            sdp.city = RandomData.CITIES[randomInt(0, RandomData.CITIES.length-1)];
            sdp.state = RandomData.STATES[randomInt(0, RandomData.STATES.length-1)];
            sdp.zip = randomInt(10000, 99999);
            sdp.sex = RandomData.SEXES[1];
            sdp.ethnicity = RandomData.ETHNICITIES[randomInt(0, RandomData.ETHNICITIES.length-1)];
            sdp.race = RandomData.RACES[randomInt(0, RandomData.RACES.length-1)];
            sdp.pharmacy = RandomData.PHARMACY_NAMES[randomInt(0, RandomData.PHARMACY_NAMES.length-1)];
            sdp.insurance = RandomData.HEALTH_INSURANCE_NAMES[randomInt(0, RandomData.HEALTH_INSURANCE_NAMES.length-1)];
            sdp.insuranceId = Integer.toString(randomInt(0, Integer.MAX_VALUE-1));
            sdp.ssn = Integer.toString(randomInt(100000000, 999999999));
            sdp.enteredBy = randomInt(1, RandomData.STAFF_NAMES.length);
            sdp.maritalStatus = RandomData.MARITAL_STATUSES[randomInt(0, RandomData.MARITAL_STATUSES.length-1)];
            sdp.pregnancyStatus = RandomData.PREGNANCY_STATUS[randomInt(0, RandomData.PREGNANCY_STATUS.length-1)];
            // enteredDate done.
            sdp.publish();
        }

        System.out.println("Done making patients.");
    }

    private static void putStaff() {
        WebServer.dbHandler.securePost("TRUNCATE TABLE `Staff`", null);

        SubmitStaffPost sns = new SubmitStaffPost();

        for (String name : RandomData.STAFF_NAMES) {
            sns.firstName = name.split(" ")[0];
            sns.lastName = name.split(" ")[1];
            sns.password = "";
            sns.phoneNumber = String.format("(%d) %d-%d", randomInt(100, 999), randomInt(100, 999), randomInt(1000, 9999));
            sns.dob = randomDate(1950, 2015);
            sns.streetAddress = String.format("%d %s", randomInt(100, 9999), RandomData.STREETS[randomInt(0, RandomData.STREETS.length-1)]);
            sns.city = RandomData.CITIES[randomInt(0, RandomData.CITIES.length-1)];
            sns.state = RandomData.STATES[randomInt(0, RandomData.STATES.length-1)];
            sns.zip = randomInt(10000, 99999);
            sns.position = "Doctor";
            sns.department = "";
            sns.employmentDate = randomDate(2010, 2023);
            sns.publish();
        }
    }

    private static void putPhysicalEvaluations() {
        WebServer.dbHandler.securePost("TRUNCATE TABLE `PhysicalEvaluations`", null);
    }

    private static void putMentalEvaluations() {
        WebServer.dbHandler.securePost("TRUNCATE TABLE `MentalHealthEvaluations`", null);
    }

    private static void putConsentData() {
        WebServer.dbHandler.securePost("TRUNCATE TABLE `ConsentFormData`", null);
    }

    private static void putBloodDrawData() {
        WebServer.dbHandler.securePost("TRUNCATE TABLE `BloodDraws`", null);
    }

    private static void putAppointmentData() {
        WebServer.dbHandler.securePost("TRUNCATE TABLE `Appointments`", null);
    }

    private static void putImmunizationData() {
        WebServer.dbHandler.securePost("TRUNCATE TABLE `ImmunizationRecords`", null);
    }

    private static void putSurgicalHistories() {
        WebServer.dbHandler.securePost("TRUNCATE TABLE `SurgicalHistories`", null);
    }

    private static void putMedicationData() {
        WebServer.dbHandler.securePost("TRUNCATE TABLE `Prescriptions`", null);
    }

    private static String randomDate(int minYear, int maxYear) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        int year  = randomInt(minYear, maxYear);
        int month = randomInt(1,12);
        int day   = randomInt(1,30);
        boolean notParsed = true;
        LocalDate date = null;

        while(notParsed) {
            try {
                date = LocalDate.parse(String.format("%d-%02d-%02d", year, month, day), formatter);
                notParsed = false;
            }
            catch(Exception ex) { day--; } // 02/29 non-leap year issue
        }

        //LocalDate date = LocalDate.parse("1994-05-09", formatter);
        return date.toString();
    }

    private static int randomInt(int min, int max) {
        Random r = new Random();
        int randomNum = r.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static void populate() {
        WebServer.dbHandler.securePost("SET FOREIGN_KEY_CHECKS = 0", null);

        putPatients();

        WebServer.dbHandler.securePost("SET FOREIGN_KEY_CHECKS = 1", null);
    }
}
