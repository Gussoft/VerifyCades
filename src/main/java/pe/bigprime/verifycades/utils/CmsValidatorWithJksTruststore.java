package pe.bigprime.verifycades.utils;

import eu.europa.esig.dss.DSSDocument;
import eu.europa.esig.dss.FileDocument;
import eu.europa.esig.dss.cades.validation.CMSDocumentValidator;
import eu.europa.esig.dss.client.ocsp.OnlineOCSPSource;
import eu.europa.esig.dss.validation.AdvancedSignature;
import eu.europa.esig.dss.validation.CommonCertificateVerifier;
import eu.europa.esig.dss.validation.SignedDocumentValidator;
import eu.europa.esig.dss.validation.executor.ProcessExecutor;
import eu.europa.esig.dss.validation.executor.ValidationLevel;
import eu.europa.esig.dss.validation.reports.Reports;
import java.io.File;
import java.util.Date;
import java.util.List;

public class CmsValidatorWithJksTruststore {

    public static Reports validate(File signedDocument, String jksTruststorePath, String jksTruststorePassword) {
        DSSDocument dssDocument = new FileDocument(signedDocument);
        CMSDocumentValidator validator = (CMSDocumentValidator) SignedDocumentValidator.fromDocument(dssDocument);
        CommonCertificateVerifier certificateVerifier = new CommonCertificateVerifier();
        certificateVerifier.setTrustedCertSource(new JksBackedTrustedListsCertificateSource(jksTruststorePath, jksTruststorePassword));
        certificateVerifier.setOcspSource(new OnlineOCSPSource());
        certificateVerifier.setIncludeCertificateTokenValues(true);
        validator.setCertificateVerifier(certificateVerifier);
        validator.setValidationLevel(ValidationLevel.BASIC_SIGNATURES);
        ProcessExecutor<Reports> reportsProcessExecutor = validator.provideProcessExecutorInstance();

        List<AdvancedSignature> signatures = validator.getSignatures();

        AdvancedSignature advancedSignature = signatures.get(0);
        Date signingTime = advancedSignature.getSigningTime();
        reportsProcessExecutor.setCurrentTime(signingTime);

        return validator.validateDocument();
    }

}
