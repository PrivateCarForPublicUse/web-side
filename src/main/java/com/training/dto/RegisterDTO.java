package com.training.dto;

public class RegisterDTO {
    private String password;
    private String phoneNumber;
    private String companyAddress;
    private Long companyId;
    private Long workNumber;
    private IdCardDTO idCard;
    private DriverLicenseDTO driverLicense;

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(Long workNumber) {
        this.workNumber = workNumber;
    }

    public IdCardDTO getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCardDTO idCard) {
        this.idCard = idCard;
    }

    public DriverLicenseDTO getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(DriverLicenseDTO driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
