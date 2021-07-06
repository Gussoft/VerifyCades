package pe.bigprime.verifycades.utils;

import eu.europa.esig.dss.DSSDocument;
import eu.europa.esig.dss.FileDocument;
import eu.europa.esig.dss.validation.CommonCertificateVerifier;
import eu.europa.esig.dss.validation.SignedDocumentValidator;
import eu.europa.esig.dss.validation.reports.Reports;
import eu.europa.esig.dss.validation.reports.SimpleReport;
import eu.europa.esig.dss.validation.reports.wrapper.DiagnosticData;
import eu.europa.esig.dss.validation.reports.wrapper.SignatureWrapper;
import pe.bigprime.verifycades.models.InfoCertificate;
import pe.bigprime.verifycades.models.Logger;

import java.io.File;

public class ValidateCAdES {

    public static Boolean VerifyDocSigned(String pathDocument) {
        File file = new File(pathDocument);
        DSSDocument dssDocument = new FileDocument(file);
        SignedDocumentValidator validator = SignedDocumentValidator.fromDocument(dssDocument);
        validator.setCertificateVerifier(new CommonCertificateVerifier());
        return !validator.getSignatures().isEmpty();
    }

    public static void VerifySignature(File file) {

        try {
            DSSDocument dssDocument = new FileDocument(file);
            SignedDocumentValidator validator = SignedDocumentValidator.fromDocument(dssDocument);
            validator.setCertificateVerifier(new CommonCertificateVerifier());
            Reports reports = validator.validateDocument();

//            reports.print();
            setData(reports);

            Logger.setErr(false);
            Logger.setMessage(null);

        } catch (Exception e) {
            Logger.setErr(true);
            Logger.setMessage(e.getMessage());
        }
    }

    private static void setData(Reports reports) {

        SimpleReport simpleReport = reports.getSimpleReport();

        DiagnosticData diagnosticData = reports.getDiagnosticData();
        SignatureWrapper signature = diagnosticData.getSignatureById(diagnosticData.getFirstSignatureId());

        String signatureId = simpleReport.getFirstSignatureId();

        InfoCertificate.setSignatureFormat(simpleReport.getSignatureFormat(signatureId));
        InfoCertificate.setSignedBy(simpleReport.getSignedBy(signatureId));
        InfoCertificate.setErrors(simpleReport.getErrors(signatureId).toString());
        InfoCertificate.setIndication(simpleReport.getIndication(signatureId).toString());
        InfoCertificate.setInfo(simpleReport.getInfo(signatureId).toString());
        InfoCertificate.setSignatureIdList(simpleReport.getSignatureIdList().toString());
        InfoCertificate.setSignatureQualification(simpleReport.getSignatureQualification(signatureId).getLabel());
        InfoCertificate.setSignaturesCount(String.valueOf(simpleReport.getSignaturesCount()));
        InfoCertificate.setSigningTime(simpleReport.getSigningTime(signatureId).toString());
        InfoCertificate.setSubIndication(simpleReport.getSubIndication(signatureId).toString());
        InfoCertificate.setValidSignaturesCount(String.valueOf(simpleReport.getValidSignaturesCount()));
        InfoCertificate.setValidationTime(simpleReport.getValidationTime().toString());
        InfoCertificate.setWarnings(simpleReport.getWarnings(signatureId).toString());

        InfoCertificate.setId(signature.getId());
        InfoCertificate.setContentIdentifier(signature.getContentIdentifier());
        InfoCertificate.setDateTime(signature.getDateTime());
//            InfoCertificate.setAddress (signature.getAddress());
//            InfoCertificate.setCity (signature.getCity());
        InfoCertificate.setContentHints(signature.getContentHints());
        InfoCertificate.setContentIdentifier(signature.getContentIdentifier());
        InfoCertificate.setContentType(signature.getContentType());
//            InfoCertificate.setCountryName (signature.getCountryName());
        InfoCertificate.setDigestAlgoUsedToSignThisToken(signature.getDigestAlgoUsedToSignThisToken());
        InfoCertificate.setEncryptionAlgoUsedToSignThisToken(signature.getEncryptionAlgoUsedToSignThisToken());
        InfoCertificate.setErrorMessage(signature.getErrorMessage());
        InfoCertificate.setFirstChainCertificateId(signature.getFirstChainCertificateId());
        InfoCertificate.setFormat(signature.getFormat());
        InfoCertificate.setKeyLengthUsedToSignThisToken(signature.getKeyLengthUsedToSignThisToken());
        InfoCertificate.setLastChainCertificateId(signature.getLastChainCertificateId());
        InfoCertificate.setLastChainCertificateSource(signature.getLastChainCertificateSource());
        InfoCertificate.setMaskGenerationFunctionUsedToSignThisToken(signature.getMaskGenerationFunctionUsedToSignThisToken());
        InfoCertificate.setParentId(signature.getParentId());
        InfoCertificate.setPolicyId(signature.getPolicyId());
        InfoCertificate.setPolicyNotice(signature.getPolicyNotice());
        InfoCertificate.setPolicyProcessingError(signature.getPolicyProcessingError());
        InfoCertificate.setPolicyUrl(signature.getPolicyUrl());
//            InfoCertificate.setPostalCode (signature.getPostalCode());
        InfoCertificate.setSignatureFilename(signature.getSignatureFilename());
        InfoCertificate.setSignatureFormat(signature.getSignatureFormat());
        InfoCertificate.setSigningCertificateId(signature.getSigningCertificateId());
//            InfoCertificate.setStateOrProvince (signature.getStateOrProvince());
        InfoCertificate.setStructuralValidationMessage(signature.getStructuralValidationMessage());

        InfoCertificate.setTimestampIdsList(signature.getTimestampList().toString());
    }

}
