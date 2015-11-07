package edu.neu.qmjz.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/10/25.
 */
public class Person {
    private String servantID;
    private String idCardNo;
    private String servantName;
    private String loginPassword;
    private String phoneNo;
    private String servantMobil;
    private String servantNationality;
    private char isMarried;
    private String educationLevel;
    private String trainingIntro;
    private String servantProvince;
    private String servantCity;
    private String servantCounty;
    private String contactAddress;
    private String serviceArea;
    private double registerLongitude;
    private double registerLatitude;
    private String qqNumber;
    private String emailAddress;
    private String servantGender;
    private String headPicture;
    private float workYears;
    private String servantHonors;
    private String servantIntro;
    private String isStayHome;
    private int holidayInMonth;
    private String  serviceItems;
    private String  careerType;
    public  Person(){}
    public  Person(JSONObject jsonObject) throws JSONException {
        this.servantID=jsonObject.getString("servantID");
        this.idCardNo=jsonObject.getString("idCardNo");
        this.servantName=jsonObject.getString("servantName");
        this.loginPassword=jsonObject.getString("loginPassword");
        this.phoneNo=jsonObject.getString("phoneNo");
        this.servantMobil=jsonObject.getString("servantMobil");
        this.servantNationality=jsonObject.getString("servantNationality");
        this.isMarried=jsonObject.getString("isMarried").charAt(0);
        this.educationLevel=jsonObject.getString("educationLevel");
        this.trainingIntro=jsonObject.getString("trainingIntro");
        this.servantProvince=jsonObject.getString("servantProvince");
        this.servantCity=jsonObject.getString("servantCity");
        this.servantCounty=jsonObject.getString("servantCounty");
        this.contactAddress=jsonObject.getString("contactAddress");
        this.serviceArea=jsonObject.getString("serviceArea");
        this.registerLongitude=jsonObject.getDouble("registerLongitude");
        this.registerLatitude=jsonObject.getDouble("registerLatitude");
        this.qqNumber=jsonObject.getString("qqNumber");
        this.emailAddress=jsonObject.getString("emailAddress");
        this.servantGender=jsonObject.getString("servantGender");
        this.headPicture=jsonObject.getString("headPicture");
        this.workYears=Float.parseFloat(jsonObject.getString("workYears"));
        this.servantHonors=jsonObject.getString("servantHonors");
        this.servantIntro=jsonObject.getString("servantIntro");
        this.isStayHome=jsonObject.getString("isStayHome");
        this.holidayInMonth=jsonObject.getInt("holidayInMonth");
        this.serviceItems=jsonObject.getString("serviceItems");
        this.careerType=jsonObject.getString("careerType");
    }
    public String getServantID() {
        return servantID;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public String getServantName() {
        return servantName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getServantMobil() {
        return servantMobil;
    }

    public String getServantNationality() {
        return servantNationality;
    }

    public char getIsMarried() {
        return isMarried;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public String getTrainingIntro() {
        return trainingIntro;
    }

    public String getServantProvince() {
        return servantProvince;
    }

    public String getServantCity() {
        return servantCity;
    }

    public String getServantCounty() {
        return servantCounty;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public double getRegisterLongitude() {
        return registerLongitude;
    }

    public double getRegisterLatitude() {
        return registerLatitude;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getServantGender() {
        return servantGender;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public float getWorkYears() {
        return workYears;
    }

    public String getServantHonors() {
        return servantHonors;
    }

    public String getServantIntro() {
        return servantIntro;
    }

    public String getIsStayHome() {
        return isStayHome;
    }

    public int getHolidayInMonth() {
        return holidayInMonth;
    }

    public String getServiceItems() {
        return serviceItems;
    }

    public String getCareerType() {
        return careerType;
    }

    public void setServantID(String servantID) {
        this.servantID = servantID;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public void setServantName(String servantName) {
        this.servantName = servantName;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setServantMobil(String servantMobil) {
        this.servantMobil = servantMobil;
    }

    public void setServantNationality(String servantNationality) {
        this.servantNationality = servantNationality;
    }

    public void setIsMarried(char isMarried) {
        this.isMarried = isMarried;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public void setTrainingIntro(String trainingIntro) {
        this.trainingIntro = trainingIntro;
    }

    public void setServantProvince(String servantProvince) {
        this.servantProvince = servantProvince;
    }

    public void setServantCity(String servantCity) {
        this.servantCity = servantCity;
    }

    public void setServantCounty(String servantCounty) {
        this.servantCounty = servantCounty;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public void setRegisterLongitude(double registerLongitude) {
        this.registerLongitude = registerLongitude;
    }

    public void setRegisterLatitude(double registerLatitude) {
        this.registerLatitude = registerLatitude;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setServantGender(String servantGender) {
        this.servantGender = servantGender;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public void setWorkYears(float workYears) {
        this.workYears = workYears;
    }

    public void setServantHonors(String servantHonors) {
        this.servantHonors = servantHonors;
    }

    public void setServantIntro(String servantIntro) {
        this.servantIntro = servantIntro;
    }

    public void setIsStayHome(String isStayHome) {
        this.isStayHome = isStayHome;
    }

    public void setHolidayInMonth(int holidayInMonth) {
        this.holidayInMonth = holidayInMonth;
    }

    public void setServiceItems(String serviceItems) {
        this.serviceItems = serviceItems;
    }

    public void setCareerType(String careerType) {
        this.careerType = careerType;
    }
}
