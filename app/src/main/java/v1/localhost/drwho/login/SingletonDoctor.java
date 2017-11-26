package v1.localhost.drwho.login;

import v1.localhost.drwho.models.Doctor;

/**
 * Created by felipe on 17/11/17.
 */

public class SingletonDoctor {

    private static SingletonDoctor instance = null;
    private static Doctor doctor = null;
    private static boolean isDoctor = false;

    public static SingletonDoctor getInstance() {
        if (instance == null) {
            doctor = new Doctor();
            return instance = new SingletonDoctor();
        } else {
            return instance;
        }
    }

    public void setDoctor(Doctor _doctor) {
        SingletonDoctor.doctor = _doctor;
    }

    public Doctor getDoctor() {
        return SingletonDoctor.doctor;
    }

    public static boolean IsDoctor() {
        return isDoctor;
    }

    public static void setIsDoctor(boolean idDoctor) {
        SingletonDoctor.isDoctor = idDoctor;
    }
}
