package pe.bigprime.verifycades.controllers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.europa.esig.dss.validation.reports.Reports;
import eu.europa.esig.dss.validation.reports.SimpleReport;
import eu.europa.esig.dss.validation.reports.wrapper.DiagnosticData;
import org.apache.commons.io.FileUtils;
import pe.bigprime.verifycades.models.InfoCertificate;
import pe.bigprime.verifycades.models.ResultPAdESVerifyMapper;
import pe.bigprime.verifycades.utils.CmsValidatorWithJksTruststore;
import pe.bigprime.verifycades.utils.Util;
import pe.bigprime.verifycades.utils.ValidateCAdES;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static pe.bigprime.verifycades.utils.Util.getPublicCert;

public class Verify implements RequestHandler<RequestVerify, String> {
    static Gson gson1 = new Gson();
    public static ResultPAdESVerifyMapper verifyDocumentResourceCAdES(File file) { // static
        try {
            //byte[] bytes = FileUtils.readFileToByteArray(file);
            ArrayList<ResultPAdESVerifyMapper> resultPAdESVerifyMappers = new ArrayList<>();

            File trustStorePath = getPublicCert();
            Reports validationReport = null;
            if (trustStorePath != null) {
                validationReport = CmsValidatorWithJksTruststore.validate(file, trustStorePath.getAbsolutePath(), "changeit");

                SimpleReport simpleReport = validationReport.getSimpleReport();
                DiagnosticData diagnosticData = validationReport.getDiagnosticData();
                List<String> signatureIdList = simpleReport.getSignatureIdList();

                for (String signatureId : signatureIdList) {
                    System.out.println("  - Signature " + signatureId);
                    List<String> validationErrors = simpleReport.getErrors(signatureId);
                    if (validationErrors.size() > 0) {
                        for (String error : validationErrors) {
                            System.out.println("      - " + error);
                        }
                    }
                }
            }
            ValidateCAdES.VerifySignature(file);
            ResultPAdESVerifyMapper resultPAdESVerifyMapper = new ResultPAdESVerifyMapper();
            resultPAdESVerifyMapper.setSignatureFormat(InfoCertificate.getSignatureFormat());
            resultPAdESVerifyMapper.setCommonName(InfoCertificate.getSignedBy());
            resultPAdESVerifyMapper.setIssuerDN("NO ISSUER DN");
            resultPAdESVerifyMapper.setSignatureDate(InfoCertificate.getSigningTime());

            resultPAdESVerifyMappers.add(resultPAdESVerifyMapper);
            return resultPAdESVerifyMapper;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String handleRequest(RequestVerify data, Context context) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //request = gson.fromJson(data, RequestVerify.class);

        File file = Util.getFileUrl(data.getUrl());
        ResultPAdESVerifyMapper stream = verifyDocumentResourceCAdES(file);
        ResponseVefify responseVefify =  new ResponseVefify();

        if (stream != null){
            responseVefify.setCommonName(stream.getCommonName());
            responseVefify.setSignatureDate(stream.getSignatureDate());
            responseVefify.setIssuerDN(stream.getIssuerDN());
            responseVefify.setValidationStatus(stream.getValidationStatus());
            responseVefify.setTrustStatus(stream.getTrustStatus());
            responseVefify.setRevocationStatus(stream.getRevocationStatus());
            responseVefify.setValidityStatus(stream.getValidityStatus());
            responseVefify.setSignatureFormat(stream.getSignatureFormat());
            return gson.toJson(responseVefify);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Jungle!");
        RequestVerify request = new RequestVerify();
        request.setApi("verify");
        request.setUrl("https://filescades.s3.amazonaws.com/enRemolinos.p7m");
        String data = gson1.toJson(request);
        //String ext = Util.getExtension(request.getUrl());
        //File file = Util.getFileUrl(request.getUrl());
        String responseVefify = paralelo(request);

        System.out.println("responseVefify = " + responseVefify);
    }

    public static String paralelo(RequestVerify data){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        File file = Util.getFileUrl(data.getUrl());
        if (data.getApi().equals("verify")) {
            ResultPAdESVerifyMapper stream = verifyDocumentResourceCAdES(file);
            ResponseVefify responseVefify = new ResponseVefify();

            if (stream != null) {
                responseVefify.setCommonName(stream.getCommonName());
                responseVefify.setSignatureDate(stream.getSignatureDate());
                responseVefify.setIssuerDN(stream.getIssuerDN());
                responseVefify.setValidationStatus(stream.getValidationStatus());
                responseVefify.setTrustStatus(stream.getTrustStatus());
                responseVefify.setRevocationStatus(stream.getRevocationStatus());
                responseVefify.setValidityStatus(stream.getValidityStatus());
                responseVefify.setSignatureFormat(stream.getSignatureFormat());
                return gson1.toJson(responseVefify);
            }
        }
        if (data.getApi().equals("convert")){
            System.out.println("convert");
        }
        return null;
    }
}
