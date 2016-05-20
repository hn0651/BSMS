package hansung.bsms.menutest;

/**
 * Created by user on 2016-05-15.
 */
public class Univ {
    private String engName;
    private String korName;
    private String schedule;
    private String website;
    private String tel;
    private String campusMap;
    private double latitude;
    private double longitude;
    private String address;
    private double competitionRate_2015;
    private double scholarshipPerPerson_2015;
    private double tuitionPerYear_2015;

    public Univ() {

    }

    public Univ(String engName, String korName, String schedule, String website, String tel, String campusMap, double latitude, double longitude, String address, double competitionRate_2015, double scholarshipPerPerson_2015, double tuitionPerYear_2015) {
        this.engName = engName;
        this.korName = korName;
        this.schedule = schedule;
        this.website = website;
        this.tel = tel;
        this.campusMap = campusMap;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.competitionRate_2015 = competitionRate_2015;
        this.scholarshipPerPerson_2015 = scholarshipPerPerson_2015;
        this.tuitionPerYear_2015 = tuitionPerYear_2015;
    }

    public String getEngName() {
        return engName;
    }

    public String getKorName() {
        return korName;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getWebsite() {
        return website;
    }

    public String getTel() {
        return tel;
    }

    public String getCampusMap() {
        return campusMap;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public double getCompetitionRate_2015() {
        return competitionRate_2015;
    }

    public double getScholarshipPerPerson_2015() {
        return scholarshipPerPerson_2015;
    }

    public double getTuitionPerYear_2015() {
        return tuitionPerYear_2015;
    }
}
