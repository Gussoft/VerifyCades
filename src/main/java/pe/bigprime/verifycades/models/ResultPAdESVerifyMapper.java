package pe.bigprime.verifycades.models;

public class ResultPAdESVerifyMapper {
    private String commonName;
    private String signatureDate;
    private String issuerDN;
    private String validationStatus;
    private String trustStatus;
    private String revocationStatus;
    private String validityStatus;
    private String signatureFormat;

    public ResultPAdESVerifyMapper() {
    }

    public ResultPAdESVerifyMapper(String commonName, String signatureDate, String issuerDN, String validationStatus, String trustStatus, String revocationStatus, String validityStatus, String signatureFormat) {
        this.commonName = commonName;
        this.signatureDate = signatureDate;
        this.issuerDN = issuerDN;
        this.validationStatus = validationStatus;
        this.trustStatus = trustStatus;
        this.revocationStatus = revocationStatus;
        this.validityStatus = validityStatus;
        this.signatureFormat = signatureFormat;
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
