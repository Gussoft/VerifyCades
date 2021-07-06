package pe.bigprime.verifycades.controllers;

public class ResponseVefify {
    private String commonName;
    private String signatureDate;
    private String issuerDN;
    private String validationStatus;
    private String trustStatus;
    private String revocationStatus;
    private String validityStatus;
    private String signatureFormat;

    public ResponseVefify(){

    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getSignatureDate() {
        return signatureDate;
    }

    public void setSignatureDate(String signatureDate) {
        this.signatureDate = signatureDate;
    }

    public String getIssuerDN() {
        return issuerDN;
    }

    public void setIssuerDN(String issuerDN) {
        this.issuerDN = issuerDN;
    }

    public String getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

    public String getTrustStatus() {
        return trustStatus;
    }

    public void setTrustStatus(String trustStatus) {
        this.trustStatus = trustStatus;
    }

    public String getRevocationStatus() {
        return revocationStatus;
    }

    public void setRevocationStatus(String revocationStatus) {
        this.revocationStatus = revocationStatus;
    }

    public String getValidityStatus() {
        return validityStatus;
    }

    public void setValidityStatus(String validityStatus) {
        this.validityStatus = validityStatus;
    }

    public String getSignatureFormat() {
        return signatureFormat;
    }

    public void setSignatureFormat(String signatureFormat) {
        this.signatureFormat = signatureFormat;
    }
}

/*
        "commonName": "John Doe",
        "signatureDate": "Sat Feb 20 10:02:01 COT 2021",
        "issuerDN": "NO ISSUER DN",
        "validationStatus": null,
        "trustStatus": null,
        "revocationStatus": null,
        "validityStatus": null,
        "signatureFormat": "CAdES-BASELINE-B"
*  */