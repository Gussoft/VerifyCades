package pe.bigprime.verifycades.utils;

import eu.europa.esig.dss.tsl.*;
import eu.europa.esig.dss.util.TimeDependentValues;
import eu.europa.esig.dss.validation.process.qualification.trust.TrustedServiceStatus;
import eu.europa.esig.dss.x509.CertificateToken;
import eu.europa.esig.dss.x509.KeyStoreCertificateSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

// TODO instead of this maybe just create manually or automatically a TSL from the provided JKS and use the standard TrustedListsCertificateSource implementation.
public class JksBackedTrustedListsCertificateSource extends TrustedListsCertificateSource {

    public static final String DUMMY_COUNTRY_CODE = "XX";
    public static final String DUMMY_STR = "dummy";

    public JksBackedTrustedListsCertificateSource(String jksTruststorePath, String jksTruststorePassword) {
        TLInfo info = new TLInfo();
        info.setCountryCode(DUMMY_COUNTRY_CODE);
        info.setLotl(true);
        info.setVersion(5);
        info.setWellSigned(true);
        updateTlInfo(DUMMY_COUNTRY_CODE, info);
        try {
            importAsTrusted(new KeyStoreCertificateSource(jksTruststorePath, "JKS", jksTruststorePassword));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CertificateToken addCertificate(CertificateToken certificate) {
        List<ServiceInfo> serviceInfos = new ArrayList<ServiceInfo>();
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setTlCountryCode(DUMMY_COUNTRY_CODE);
        serviceInfo.setTspName(DUMMY_STR);
        serviceInfo.setTspRegistrationIdentifier(DUMMY_STR);

        Calendar startDate = Calendar.getInstance();
        startDate.set(1980, Calendar.JANUARY, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2030, Calendar.JANUARY, 0);
        serviceInfo.setStatus(new TimeDependentValues<ServiceInfoStatus>(Collections.singleton(new ServiceInfoStatus(DUMMY_STR, "http://uri.etsi.org/TrstSvc/Svctype/CA/QC", TrustedServiceStatus.UNDER_SUPERVISION, Collections.<String, List<Condition>>emptyMap(), Collections.singletonList("http://uri.etsi.org/TrstSvc/TrustedList/SvcInfoExt/ForeSignatures"), null, null, startDate.getTime(), endDate.getTime()))));
        serviceInfos.add(serviceInfo);
        addCertificate(certificate, serviceInfos);
        return certificate;
    }
}
