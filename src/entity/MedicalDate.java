package entity;

public class MedicalDate {

    private int id;
    private int idSpecialty;
    private int idPatient;
    private String dayDate;
    private String timeDate;
    private String reason;

    public MedicalDate() {
    }

    public MedicalDate(int id, int idSpecialty, int idPatient, String dayDate, String timeDate, String reason) {
        this.id = id;
        this.idSpecialty = idSpecialty;
        this.idPatient = idPatient;
        this.dayDate = dayDate;
        this.timeDate = timeDate;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSpecialty() {
        return idSpecialty;
    }

    public void setIdSpecialty(int idSpecialty) {
        this.idSpecialty = idSpecialty;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public String getDayDate() {
        return dayDate;
    }

    public void setDayDate(String dayDate) {
        this.dayDate = dayDate;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Date{" +
                "id=" + id +
                ", idSpecialty=" + idSpecialty +
                ", idPatient=" + idPatient +
                ", dayDate='" + dayDate + '\'' +
                ", timeDate='" + timeDate + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
